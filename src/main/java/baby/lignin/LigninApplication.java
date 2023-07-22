package baby.lignin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LigninApplication {

	public static void main(String[] args) {
		SpringApplication.run(LigninApplication.class, args);
	}

}
