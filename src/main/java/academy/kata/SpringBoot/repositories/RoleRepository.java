package academy.kata.SpringBoot.repositories;

import academy.kata.SpringBoot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role, Long> {
//    @Query(value = "SELECT role FROM users.role WHERE role.roleName = \n-- #name\n LIMIT = 1 END",
//            nativeQuery = true)
//    Role getRoleByName(String name);
}
