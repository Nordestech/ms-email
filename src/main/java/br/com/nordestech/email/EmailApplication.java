package br.com.nordestech.email;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory("./") // diretório base
                .ignoreIfMissing()
                .load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(EmailApplication.class, args);

    }

}
