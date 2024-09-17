package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import infnet.edu.apibloco.Domain.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "select * from users where email = ?1 and password = ?2", nativeQuery = true)
    User login(String email, String pass);

}
