package backend.kagukar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KagukarApplication {
    public static void main(String[] args) {
        SpringApplication.run(KagukarApplication.class, args);

        System.out.println("Starting application with localization");
    }
}
