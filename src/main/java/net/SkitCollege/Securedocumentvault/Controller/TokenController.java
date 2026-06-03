package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.SkitCollege.Securedocumentvault.Service.TokenDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Token API's")
@RequestMapping("/documents")
public class TokenController {
@Autowired
    private TokenDocumentService tokenDocumentService;

    @PostMapping("/{docId}/token")
    public ResponseEntity<?> generateToken(@PathVariable String docId) {

        String token = tokenDocumentService.createToken(docId);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewDocument(@RequestParam String token) {

        Long docId = tokenDocumentService.validateToken(token);
        return ResponseEntity.ok("Document " + docId + " accessed");
    }
}
