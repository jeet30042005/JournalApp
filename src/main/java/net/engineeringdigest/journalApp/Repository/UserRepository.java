package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    public User getByUsername(String username);
    void deleteByUsername(String username);
}
