package net.SkitCollege.Securedocumentvault.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.SkitCollege.Securedocumentvault.Service.DocumentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/documents")
@Tag(name="Document API's")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    // Upload Document
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocuments(
            @RequestParam("files") MultipartFile[] files,
            Authentication authentication) {

        return ResponseEntity.ok(
                documentService.uploadMultiple(files, authentication.getName())
        );
    }
    // Get all documents of logged-in user
    @GetMapping("/alldoc")
    public ResponseEntity<?> getMyDocuments(Authentication authentication) {
        return ResponseEntity.ok(
                documentService.getUserDocuments(authentication.getName())
        );
    }

    // Get single document
    @GetMapping("/{id}")
    public ResponseEntity<?> getDocument(
            @PathVariable String id,
            Authentication authentication) {

        return ResponseEntity.ok(
                documentService.getDocument(id, authentication.getName())
        );
    }

    // Delete document
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(
            @PathVariable String id,
            Authentication authentication) {

        documentService.deleteDocument(id, authentication.getName());
        return ResponseEntity.ok("Document deleted successfully");
    }

    // Rename document
    @PutMapping("/{id}")
    public ResponseEntity<?> renameDocument(
            @PathVariable String id,
            @RequestParam String newName,
            Authentication authentication) {

        return ResponseEntity.ok(
                documentService.renameDocument(id, newName, authentication.getName())
        );
    }
}