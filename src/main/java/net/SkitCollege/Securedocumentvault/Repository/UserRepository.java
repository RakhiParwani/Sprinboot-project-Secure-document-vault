package net.SkitCollege.Securedocumentvault.Repository;

import net.SkitCollege.Securedocumentvault.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {

     Optional<UserEntity> findByEmail(String email);

//    UserEntity findByEmail(String email);



}