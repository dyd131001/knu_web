package com.knu.meeting.controller;

import com.knu.meeting.global.common.response.ResponseDTO;
import com.knu.meeting.global.error.exception.ApiErrorExample;
import com.knu.meeting.global.error.exception.MeetingError;
import com.knu.meeting.global.error.exception.UserError;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.EnterMeetingDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.PossibleAge;
import com.knu.meeting.service.MeetingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "meeting", description = "모임 API")
@RestController
@Slf4j
@RequestMapping("/meetings")
@Transactional
@AllArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "미팅 정보 조회", description = "접속시 표시할 미팅 정보 조회")
    @PostMapping("/find/{id}")
    public ResponseEntity<ResponseDTO<MeetingDTO>> findMeeting(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findMeetingById(id), "미팅 정보 조회"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "채팅방 접속시 필요한 미팅 정보 조회", description = "채팅방 접속시 필요한 미팅 정보 조회")
    @GetMapping("/find/enter/{id}")
    public ResponseEntity<ResponseDTO<EnterMeetingDTO>> findEnterMeeting(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findEnterMeetingById(id), "채팅방 접속시 필요한 미팅 정보 조회"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "지역, 취미별 미팅 추가 조회", description = "id 기준으로 새롭게 생성된 데이터 표시")
    @GetMapping("/update/data")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> updateData(
            @RequestParam Long id,
            @RequestParam Address address,
            @RequestParam Hobby hobby) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findMeetingsAfterId(id, address, hobby), "지역, 취미별 미팅 추가 조회"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "미팅 생성", description = "미팅 생성")
    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<Long>> create(@RequestBody CreateMeetingDTO createMeetingDTO) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.create(createMeetingDTO), "미팅 생성"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "위치 업데이트", description = "미팅 위치 업데이트")
    @PostMapping("/update/{id}/location")
    public ResponseEntity<ResponseDTO<Boolean>> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.updateLocation(id, location), "위치 업데이트"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "참석 가능한 나이 업데이트", description = "미팅 참석 가능한 나이 업데이트")
    @PostMapping("/update/{id}/possibleAge")
    public ResponseEntity<ResponseDTO<Boolean>> updatePossibleAge(@PathVariable("id") Long id, @RequestBody PossibleAge possibleAge) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.updatePossibleAge(id, possibleAge), "참석 가능한 나이 업데이트"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "미팅 제목 업데이트", description = "미팅 제목 업데이트")
    @PostMapping("/update/{id}/title")
    public ResponseEntity<ResponseDTO<Boolean>> updateTitle(@PathVariable("id") Long id, @RequestBody String title) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.updateTitle(id, title), "미팅 제목 업데이트"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "모임 취미 업데이트", description = "모임 취미 업데이트")
    @PostMapping("/update/{id}/hobbies")
    public ResponseEntity<ResponseDTO<Boolean>> updateHobbies(@PathVariable("id") Long id, @RequestBody List<String> hobbies) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.updateHobbies(id, hobbies), "모임 취미 업데이트"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "미팅 삭제", description = "미팅 삭제")
    @DeleteMapping("/delete/{id}")
    public void deleteMeeting(@PathVariable("id") Long id) {
        meetingService.delete(id);
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "모든 미팅 조회", description = "모든 미팅 조회")
    @PostMapping("/findAll")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> findAll() {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findAll(), "모든 미팅 조회"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "취미로 미팅 검색", description = "타이틀, 지역, 취미로 미팅 검색")
    @GetMapping("/search/hobby")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> searchMeetingsByHobby(
            @RequestParam Address address,
            @RequestParam Hobby hobby) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findAllByAddressAndHobby(address, hobby), "취미로 미팅 검색"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "위치 이름으로 미팅 검색", description = "위치 이름, 지역, 취미로 미팅 검색")
    @GetMapping("/search/locationName")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> searchMeetingsByLocationName(
            @RequestParam String locationName,
            @RequestParam Address address,
            @RequestParam Hobby hobby) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findAllByLocationAndHobbies(locationName, address, hobby), "위치 이름으로 미팅 검색"));
    }

    @ApiErrorExample(MeetingError.class)
    @Operation(summary = "위치로 미팅 검색", description = "위치로 미팅 검색")
    @GetMapping("/search/location")
    public ResponseEntity<ResponseDTO<List<MeetingDTO>>> searchMeetingsByLocation(
            @RequestParam Long locationId,
            @RequestParam Hobby hobby) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(meetingService.findAllByLocation(locationId, hobby), "위치로 미팅 검색"));
    }
}