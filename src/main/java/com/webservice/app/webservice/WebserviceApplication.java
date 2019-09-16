package com.webservice.app.webservice;

import com.webservice.app.webservice.model.JwtUser;
import com.webservice.app.webservice.model.User;
import com.webservice.app.webservice.repository.AdminRepository;
import com.webservice.app.webservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories(basePackages = "com.webservice.app.webservice.repository")
@SpringBootApplication
public class WebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepo, AdminRepository adminRepository) {
        return args -> {
            PodamFactory factory = new PodamFactoryImpl();
            JwtUser admin = new JwtUser();
            admin.setRole("Administrator");
            admin.setUsername("valeanstefan");
            adminRepository.save(admin);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                users.add(factory.manufacturePojo(User.class));
            }
            users.forEach(user -> userRepo.save(user));
        };
    }

}
