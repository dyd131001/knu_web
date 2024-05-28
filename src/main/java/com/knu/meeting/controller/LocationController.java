package com.knu.meeting.controller;

import com.knu.meeting.global.common.response.ResponseDTO;
import com.knu.meeting.global.error.exception.ApiErrorExample;
import com.knu.meeting.global.error.exception.LocationError;
import com.knu.meeting.global.error.exception.MeetingError;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.dto.CreateMeetingDTO;
import com.knu.meeting.model.dto.LocationDTO;
import com.knu.meeting.model.dto.MeetingDTO;
import com.knu.meeting.model.entity.Location;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "location", description = "위치 API")
@RestController
@Slf4j
@RequestMapping("/locations")
@Transactional
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 정보 조회", description = "ID로 위치 정보 조회")
    @PostMapping("/find/{id}")
    public ResponseEntity<ResponseDTO<LocationDTO>> findLocation(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.findLocationById(id), "위치 정보 조회"));
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 생성", description = "위치 데이터 생성")
    @PostMapping("/new")
    public ResponseEntity<ResponseDTO<Long>> create(@RequestBody CreateLocationDTO createLocationDTO) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.create(createLocationDTO), "위치 생성"));
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 삭제", description = "ID로 위치 삭제")
    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable("id") Long id) {
        locationService.delete(id);
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "모든 위치 조회", description = "모든 위치 조회")
    @PostMapping("/findAll")
    public ResponseEntity<ResponseDTO<List<LocationDTO>>> findAll() {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.findAll(), "모든 위치 조회"));
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 검색", description = "주소와 취미로 위치 검색")
    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<List<LocationDTO>>> search(
            @RequestParam Address address,
            @RequestParam Hobby hobby) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.findAllByAddressAndHobby(address, hobby), "위치 검색"));
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 이름 업데이트", description = "위치 이름 업데이트")
    @PostMapping("/update/{id}/locationName")
    public ResponseEntity<ResponseDTO<Boolean>> updateLocationName(@PathVariable("id") Long id, @RequestBody String locationName) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.updateLocationName(id, locationName), "위치 이름 업데이트"));
    }

    @ApiErrorExample(LocationError.class)
    @Operation(summary = "위치 좌표 업데이트", description = "위치 좌표 업데이트")
    @PostMapping("/update/{id}/position")
    public ResponseEntity<ResponseDTO<Boolean>> updatePosition(@PathVariable("id") Long id, @RequestBody Position position) {
        return ResponseEntity
                .ok()
                .body(ResponseDTO.of(locationService.updatePosition(id, position), "위치 좌표 업데이트"));
    }
}