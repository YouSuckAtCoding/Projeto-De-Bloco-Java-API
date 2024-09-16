package infnet.edu.apibloco.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import infnet.edu.apibloco.Domain.Models.Post;

public interface PostRepository extends JpaRepository<Post, String>{

}
