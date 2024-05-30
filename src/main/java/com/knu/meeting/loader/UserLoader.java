package com.knu.meeting.loader;

import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.service.UserService;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Order(1)
public class UserLoader implements ApplicationRunner {

    UserService userService;
    @Override
    public void run(ApplicationArguments args){
        CreateUserDTO user1 = new CreateUserDTO();
        user1.setUsername("user1");
        user1.setPassword("password");
        user1.setEmail("user1@example.com");
        user1.setAddress(new Address("서울특별시", "강남구"));
        user1.setAge(25);
        user1.setGender("남");
        user1.setHobbies(Arrays.asList("게임", "술모임"));

        CreateUserDTO user2 = new CreateUserDTO();
        user2.setUsername("user2");
        user2.setPassword("password");
        user2.setEmail("user2@example.com");
        user2.setAddress(new Address("서울특별시", "서초구"));
        user2.setAge(30);
        user2.setGender("여");
        user2.setHobbies(Arrays.asList("공부", "산책"));

        userService.create(user1);
        userService.create(user2);

    }
}
