package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import infnet.edu.apibloco.Domain.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
