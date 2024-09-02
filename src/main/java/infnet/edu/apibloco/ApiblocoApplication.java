package infnet.edu.apibloco;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableRabbit
public class ApiblocoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiblocoApplication.class, args);
	}
}
