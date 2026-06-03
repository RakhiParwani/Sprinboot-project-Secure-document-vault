package net.SkitCollege.Securedocumentvault.Service;

import lombok.RequiredArgsConstructor;
import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import net.SkitCollege.Securedocumentvault.Repository.DocumentRepository;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public boolean deleteUser(String id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<DocumentsEntity> getAllDocuments() {
        return documentRepository.findAll();
    }

    public boolean deleteDocument(String id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}