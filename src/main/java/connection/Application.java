package connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //антоация которая добавл обвесы которые находят контролеры, файлы для подклю. к базе данных
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}