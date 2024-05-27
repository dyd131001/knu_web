package com.knu.meeting.controller;

import com.knu.meeting.mapper.UserMapper;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.entity.Participation;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@Transactional
@AllArgsConstructor
public class UserController {

    private UserService userService;


    // 상세화면 조회시 필요한 데이터 반환 (회원 정보 및 참여하고 있는 간단한 회의정보 리스트)
    @PostMapping("/find/detail/{id}")
    public DetailUserDTO findDetailUser(@PathVariable("id") Long id){
        return userService.findDetailUserById(id);
    }

    //누구나 열람 가능한 프로필 조회시 필요한 데이터 반환
    @GetMapping("/find/{id}")
    public UserDTO findUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    // 유저 생기
    @PostMapping("/new")
    public Long create(@RequestBody CreateUserDTO createUserDTO) {
        log.info("request={}", createUserDTO);
        return userService.create(createUserDTO);
    }

    //인증,인가 필요 , 2번 회원이 1번 회원의 정보를 바꾸면 안됨.
    @PostMapping("/update/{id}/address")
    public boolean updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return userService.updateAddress(id, address);
    }

    @PostMapping("/update/{id}/hobbies")
    public boolean updateHobbies(@PathVariable("id") Long id, @RequestBody List<String> hobbies) {
        return userService.updateHobbies(id,hobbies);
    }

    @PostMapping("/update/{id}/password")
    public boolean updatePassword(@PathVariable("id") Long id, @RequestBody String password) {
        return userService.updatePassword(id,password);
    }




    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
    }



}
