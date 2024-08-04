package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infnet.edu.apibloco.Domain.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
