package com.knu.meeting.controller;

import com.knu.meeting.mapper.UserMapper;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@Transactional
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/new")
    public User create(@RequestBody UserDTO userDTO) {
        log.info("request={}", userDTO);

        User user = UserMapper.INSTANCE.userDTOToEntity(Role.ADMIN,userDTO);
        userService.save(user);
        return user;
    }

    //인증,인가 필요 , 2번 회원이 1번 회원의 정보를 바꾸면 안됨.
    @PostMapping("/update/{id}/address")
    public User updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        User currentUser = userService.findById(id);
        currentUser.updateAddress(address); // 엔티티의 주소 정보를 업데이트
        userService.save(currentUser); // 변경된 엔티티 저장
        return currentUser;
    }

    @PostMapping("/update/{id}/hobbies")
    public User updateHobbies(@PathVariable("id") Long id, @RequestBody List<String> hobbies) {
        User currentUser = userService.findById(id);
        List<Hobby> hobbiesEnum = hobbies.stream().map(Hobby::findHobby)
                .collect(Collectors.toList());
        currentUser.updateHobbies(hobbiesEnum); // 엔티티의 취미 정보를 업데이트
        userService.save(currentUser); // 변경된 엔티티 저장
        return currentUser;
    }

    @PostMapping("/update/{id}/password")
    public User updatePassword(@PathVariable("id") Long id, @RequestBody String password) {
        User currentUser = userService.findById(id);
        currentUser.updatePassword(password); // 엔티티의 비밀번호 정보를 업데이트
        userService.save(currentUser); // 변경된 엔티티 저장
        return currentUser;
    }

    @GetMapping("/find/username")
    public UserDTO getUser(@RequestParam("username") String username) {

        User user = userService.findByUsername(username);
        UserDTO userDTO = UserMapper.INSTANCE.userEntityToDTO(user);

        return userDTO;
    }

    @GetMapping("/find/id")
    public UserDTO getUser(@RequestParam("id") Long id) {

        User user = userService.findById(id);
        UserDTO userDTO = UserMapper.INSTANCE.userEntityToDTO(user);
        return userDTO;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        User user = userService.findById(id);
        userService.delete(user);
    }



}
