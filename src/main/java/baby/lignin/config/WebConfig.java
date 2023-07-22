package baby.lignin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(final CorsRegistry registry) {

            registry.addMapping("/**").allowedOrigins("http://localhost:3000","http://localhost:8080")
                    .allowCredentials(true)
                    .allowedHeaders("Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
                            "Access-Control-Request-Headers","Token")
                    .allowedMethods("GET","POST","DELETE","PUT")
                    .maxAge(3000);
        }
}

