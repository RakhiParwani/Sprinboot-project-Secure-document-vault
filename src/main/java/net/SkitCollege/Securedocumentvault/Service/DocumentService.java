package net.SkitCollege.Securedocumentvault.Service;

import lombok.RequiredArgsConstructor;
import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import net.SkitCollege.Securedocumentvault.Repository.DocumentRepository;
import net.SkitCollege.Securedocumentvault.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    public List<String> uploadMultiple(MultipartFile[] files, String email) {

        // Check user exists
        userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> uploadedFiles = new ArrayList<>();

        for (MultipartFile file : files) {

            if (file.isEmpty()) continue;

            DocumentsEntity document = new DocumentsEntity();
            document.setFileName(file.getOriginalFilename());
            document.setFileType(file.getContentType());
            document.setOwnerEmail(email);
            document.setUploadDate(LocalDate.now());

            try {
                document.setData(file.getBytes()); // MongoDB store
            } catch (IOException e) {
                throw new RuntimeException("File upload failed: " + file.getOriginalFilename());
            }

            documentRepository.save(document);

            uploadedFiles.add(file.getOriginalFilename());
        }

        return uploadedFiles;
    }
    public List<DocumentsEntity> getUserDocuments(String email) {
        return documentRepository.findByOwnerEmail(email);
    }

    public DocumentsEntity getDocument(String id, String email) {
        return documentRepository.findByIdAndOwnerEmail(id, email)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public void deleteDocument(String id, String email) {
        DocumentsEntity doc = getDocument(id, email);
        documentRepository.delete(doc);
    }

    public DocumentsEntity renameDocument(String id, String newName, String email) {
        DocumentsEntity doc = getDocument(id, email);
        doc.setFileName(newName);
        return documentRepository.save(doc);
    }
}