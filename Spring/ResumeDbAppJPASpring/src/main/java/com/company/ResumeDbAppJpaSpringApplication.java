package com.company;

import com.company.dao.impl.UserRepository;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
    }
//    @Autowired
//    @Qualifier("userDaoImpl")
//    private UserRepositoryCustom userDao;
    //    @Bean
//    public CommandLineRunner run() {
//        CommandLineRunner clr = new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                List<User> list = userDao.getAll(null, null, null);
//                System.out.println(list);
//            }
//        };
//        return clr;

    @Autowired
    private UserRepository userRepository;
    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (int i=0;i<10;i++){
                List<User> user = userRepository.getAll(null,null,null);
                        System.out.println(user);
            }}
        };
        return clr;
    }
}

