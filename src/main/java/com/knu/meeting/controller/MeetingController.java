package com.knu.meeting.controller;

import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.EnterMeetingDTO;
import com.knu.meeting.model.dto.GroupMessageDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.GroupMessage;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.entity.Meeting;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.service.MeetingService;
import com.knu.meeting.service.ParticipationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/meetings")
@Transactional
@AllArgsConstructor
public class MeetingController {

    private MeetingService meetingService;

    //접속시 표시할 미팅 정보 조회
    @PostMapping("/find/{id}")
    public MeetingDTO findMeeting(@PathVariable("id") Long id){
        return meetingService.findMeetingById(id);
    }

    //채팅방 접속시 필요한 미팅 정보 조회
    @GetMapping("/find/Enter/{id}")
    public EnterMeetingDTO findEnterMeeting(@PathVariable("id") Long id) {
        return meetingService.findEnterMeetingById(id);
    }


    // 지역, 취미별 미팅 추가 조회 (id 기준으로 새롭게 생성된 데이터 표시)
    @GetMapping("/update/data")
    public List<MeetingDTO> updateData(@RequestParam Long id,
                                           @RequestParam Address address,
                                           @RequestParam Hobby hobby) {
        return meetingService.findMeetingsAfterId(id, address, hobby);
    }


    // 미팅 생성
    @PostMapping("/new")
    public Long create(@RequestBody CreateMeetingDTO createMeetingDTO) {
        return meetingService.create(createMeetingDTO);
    }

    //위치 업데이트
    @PostMapping("/update/{id}/location")
    public boolean updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        return meetingService.updateLocation(id, location);
    }

    //참석가능한 나이 업데이트
    @PostMapping("/update/{id}/possibleAge")
    public boolean updatePossibleAge(@PathVariable("id") Long id, @RequestBody PossibleAge possibleAge) {
        return meetingService.updatePossibleAge(id,possibleAge);
    }

    //모임 제목 업데이트
    @PostMapping("/update/{id}/title")
    public boolean updateTitle(@PathVariable("id") Long id, @RequestBody String title) {
        return meetingService.updateTitle(id,title);
    }

    // 모임 취미 업데이트
    @PostMapping("/update/{id}/hobbies")
    public boolean updateHobbies(@PathVariable("id") Long id, @RequestBody List<String> hobbies) {
        return meetingService.updateHobbies(id,hobbies);
    }

    // 모임 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteMeeting(@PathVariable("id") Long id){
        meetingService.delete(id);
    }

    //모든 미팅 불러오기
    @PostMapping("/findAll")
    public List<MeetingDTO> findAll(){
        return meetingService.findAll();
    }


    //타이틀, 지역, 취미로 미팅 검색
    @GetMapping("/search/hobby")
    public List<MeetingDTO> searchMeetingsByHobby(
                                           @RequestParam Address address,
                                           @RequestParam Hobby hobby) {
        return meetingService.findAllByAddressAndHobby(address, hobby);
    }

    @GetMapping("/search/locationName")
    public List<MeetingDTO> searchMeetingsByLocationName(
            @RequestParam String locationName,
            @RequestParam Address address,
            @RequestParam Hobby hobby) {
        return meetingService.findAllByLocationAndHobbie(locationName,address, hobby);
    }

    @GetMapping("/search/location")
    public List<MeetingDTO> searchMeetingsByLocation(
            @RequestParam Long locationId,
            @RequestParam Hobby hobby) {
        return meetingService.findAllByLocation(locationId,hobby);
    }

}

