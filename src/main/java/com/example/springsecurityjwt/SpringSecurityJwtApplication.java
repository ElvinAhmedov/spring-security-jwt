package com.example.springsecurityjwt;

import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtApplication {
    private final UserRepository userRepository;

    public SpringSecurityJwtApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createUsers() {
        List<User> users = Stream.of(
                new User(1, "huseyn", "{noop}password", "huseyn@gmail.com"),
                new User(2, "user1", "{noop}user1", "user1@gmail.com"),
                new User(3, "user2", "{noop}user2", "user2@gmail.com"),
                new User(4, "user3", "{noop}user3", "user3@gmail.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

}
