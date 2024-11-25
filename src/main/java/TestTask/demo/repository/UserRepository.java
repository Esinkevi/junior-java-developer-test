package TestTask.demo.repository;

import TestTask.demo.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel, String> {

    boolean existsByUsername(String username);

    Optional<UserModel> findByUsername(String username);
}
