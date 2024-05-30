package com.knu.meeting.loader;

import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.model.vo.Position;
import com.knu.meeting.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Order(2)
public class LocationLoader implements ApplicationRunner {

    private final LocationService locationService;
    @Override
    public void run(ApplicationArguments args){
        CreateLocationDTO location1 = new CreateLocationDTO();
        location1.setLocationName("Location 1");
        location1.setPosition(new Position(37.1234f, 127.5678f));
        location1.setAddress(new Address("서울특별시", "강남구"));

        CreateLocationDTO location2 = new CreateLocationDTO();
        location2.setLocationName("Location 2");
        location2.setPosition(new Position(37.9876f, 126.4321f));
        location2.setAddress(new Address("경기도", "성남시"));

        CreateLocationDTO location3 = new CreateLocationDTO();
        location3.setLocationName("Location 3");
        location3.setPosition(new Position(36.7890f, 128.9012f));
        location3.setAddress(new Address("부산광역시", "해운대구"));

        CreateLocationDTO location4 = new CreateLocationDTO();
        location4.setLocationName("Location 4");
        location4.setPosition(new Position(35.4321f, 129.8765f));
        location4.setAddress(new Address("대구광역시", "중구"));

        locationService.create(location1);
        locationService.create(location2);
        locationService.create(location3);
        locationService.create(location4);



    }
}
