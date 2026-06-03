package net.SkitCollege.Securedocumentvault.Repository;

import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
//import net.engineeringdigest.journalApp.entity.SecureDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface SecureDocumentRepository
        extends MongoRepository<DocumentsEntity, String> {

    List<DocumentsEntity> findByLastUpdatedDateBefore(LocalDate date);

    List<DocumentsEntity> findByExpiryDateBetween(
            LocalDate from,
            LocalDate to
    );
}

