package academy.kata.SpringBoot.repositories;

import academy.kata.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.firstName = :firstName")
//    User getUserByName(String firstName);
}
