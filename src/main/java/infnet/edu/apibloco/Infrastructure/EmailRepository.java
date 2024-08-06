package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import infnet.edu.apibloco.Domain.Models.EmailInfo;

@Repository
public interface EmailRepository extends JpaRepository<EmailInfo, Long>{

}


