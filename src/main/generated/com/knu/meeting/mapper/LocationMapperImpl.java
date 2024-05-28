package com.knu.meeting.mapper;

import com.knu.meeting.model.dto.CreateLocationDTO;
import com.knu.meeting.model.dto.LocationDTO;
import com.knu.meeting.model.entity.Location;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T13:32:53+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public Location createLocationDTOToEntity(CreateLocationDTO createLocationDTO) {
        if ( createLocationDTO == null ) {
            return null;
        }

        Location.LocationBuilder location = Location.builder();

        location.locationName( createLocationDTO.getLocationName() );
        location.position( createLocationDTO.getPosition() );
        location.address( createLocationDTO.getAddress() );

        return location.build();
    }

    @Override
    public LocationDTO toDTO(Location location) {
        if ( location == null ) {
            return null;
        }

        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setMeeting( LocationMapper.toMeetingDTO( location.getMeeting() ) );
        locationDTO.setId( location.getId() );
        locationDTO.setLocationName( location.getLocationName() );
        locationDTO.setPosition( location.getPosition() );
        locationDTO.setAddress( location.getAddress() );

        return locationDTO;
    }
}
