package backend.kagukar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KagukarApplication {
    public static void main(String[] args) {
        SpringApplication.run(KagukarApplication.class, args);
        System.out.println("Starting application with localization");
    }

    @Bean
    public void modelMapper() {
        modelMapper = new ModelMapper();
    }
}
