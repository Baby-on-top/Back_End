package baby.lignin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class LigninApplication {

	public static void main(String[] args) {
		SpringApplication.run(LigninApplication.class, args);
	}

}
