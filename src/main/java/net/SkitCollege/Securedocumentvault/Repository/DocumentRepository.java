package net.SkitCollege.Securedocumentvault.Repository;

import net.SkitCollege.Securedocumentvault.Entity.DocumentsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository
        extends MongoRepository<DocumentsEntity, String> {

    List<DocumentsEntity> findByOwnerEmail(String email);

    Optional<DocumentsEntity>findByIdAndOwnerEmail(String id, String email);
}