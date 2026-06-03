package net.SkitCollege.Securedocumentvault.Service;

import net.SkitCollege.Securedocumentvault.Exception.TokenException;
import net.SkitCollege.Securedocumentvault.Exception.invalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenDocumentService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final long TOKEN_TTL_MIN = 5; // Token expires after 5 minutes

    // Create a new token and store in Redis
    public String createToken(String documentId) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(
               "DOC:TOKEN:" + token,
           documentId.toString(),
                TOKEN_TTL_MIN,
             TimeUnit.MINUTES
        );
        return token;
    }

    // Validate token
    public Long validateToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new invalidException("Token is missing");
        }

        if (isTokenExpired(token)) {
            throw new TokenException("Token expired");
        }

        if (!isTokenValid(token)) {
            throw new invalidException("Token invalid");
        }

        return extractDocIdFromToken(token);
    }

    // Check if token exists in Redis (expired if missing)
    private boolean isTokenExpired(String token) {
        String key = "DOC:TOKEN:" + token;
        return redisTemplate.opsForValue().get(key) == null;
    }

    // Check if token value is a valid number (document ID)
    private boolean isTokenValid(String token) {
        String key = "DOC:TOKEN:" + token;
        String docIdStr = redisTemplate.opsForValue().get(key);

        if (docIdStr == null) return false;

        try {
            Long.parseLong(docIdStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Extract document ID from token
    private Long extractDocIdFromToken(String token) {
        String key = "DOC:TOKEN:" + token;
        String docIdStr = redisTemplate.opsForValue().get(key);

        // Refresh TTL on validation (optional sliding window)
        redisTemplate.expire(key, TOKEN_TTL_MIN, TimeUnit.MINUTES);

        return Long.parseLong(docIdStr);
    }

    // Optional: manually invalidate token
    public void invalidateToken(String token) {
        redisTemplate.delete("DOC:TOKEN:" + token);
    }
}