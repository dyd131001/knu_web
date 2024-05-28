package com.knu.meeting.controller;

import com.knu.meeting.global.common.response.ResponseDTO;
import com.knu.meeting.global.error.exception.ApiErrorExample;
import com.knu.meeting.global.error.exception.UserError;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="user" , description = "유저 API")
@RestController
@Slf4j
@RequestMapping("/users")
@Transactional
@AllArgsConstructor
public class UserController {

    private UserService userService;


    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 상세정보 조회" , description = "상세화면 조회시 필요한 데이터 반환.")
    @PostMapping("/find/detail/{id}")
    public ResponseEntity<ResponseDTO<DetailUserDTO>> findDetailUser(@PathVariable("id") Long id){
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.findDetailUserById(id),"회원 상세정보 조회"));
    }

    //누구나 열람 가능한 프로필 조회시 필요한 데이터 반환
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 기본정보 조회" , description = "타인이 열람 가능한 유저 데이터 반환.")
    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> findUser(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.findUserById(id),"회원 기본정보 조회"));
    }

    // 유저 생기
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 생성" , description = "회원 폼을 이용한 유저 데이터 생성.")
    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<Long>> create(@RequestBody CreateUserDTO createUserDTO) {
        log.info("request={}", createUserDTO);
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.create(createUserDTO),"회원 생성"));
    }

    //인증,인가 필요 , 2번 회원이 1번 회원의 정보를 바꾸면 안됨.
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 지역 분류 정보 변경" , description = "모임 정보를 필터링하는 지역 분류 변경.")
    @PostMapping("/update/{id}/address")
    public ResponseEntity<ResponseDTO<Boolean>> updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.updateAddress(id, address),"회원 지역 분류  변경"));
    }

    @PostMapping("/update/{id}/hobbies")
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 취미 정보 변경" , description = "모임 분류에 필요한 취미 정보 변경.")
    public ResponseEntity<ResponseDTO<Boolean>> updateHobbies(@PathVariable("id") Long id, @RequestBody List<String> hobbies) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.updateHobbies(id,hobbies),"회원 취미 정보 변경"));
    }

    @PostMapping("/update/{id}/password")
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 비밀번호 변경" , description = "회원 비밀번호 변경.")
    public ResponseEntity<ResponseDTO<Boolean>> updatePassword(@PathVariable("id") Long id, @RequestBody String password) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(userService.updatePassword(id,password), "회원 비밀번호 변경"));
    }




    @DeleteMapping("/delete/{id}")
    @ApiErrorExample(UserError.class)
    @Operation(summary = "회원 삭제" , description = "id에 해당하는 회원 삭제")
    public void deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
    }



}
