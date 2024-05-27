package com.knu.meeting.mapper;

import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T03:57:21+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User createUserDTOToEntity(Role role, CreateUserDTO createUserDTO) {
        if ( role == null && createUserDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( createUserDTO != null ) {
            user.gender( UserMapper.toGender( createUserDTO.getGender() ) );
            user.hobbies( UserMapper.toHobbies( createUserDTO.getHobbies() ) );
            user.username( createUserDTO.getUsername() );
            user.password( createUserDTO.getPassword() );
            user.email( createUserDTO.getEmail() );
            user.address( createUserDTO.getAddress() );
            user.age( createUserDTO.getAge() );
        }
        user.role( role );

        return user.build();
    }

    @Override
    public DetailUserDTO toDetailUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        DetailUserDTO detailUserDTO = new DetailUserDTO();

        detailUserDTO.setGender( UserMapper.toGenderString( user.getGender() ) );
        detailUserDTO.setHobbies( UserMapper.toHobbiesString( user.getHobbies() ) );
        detailUserDTO.setMeetings( UserMapper.toMeetingDTO( user.getParticipations() ) );
        detailUserDTO.setId( user.getId() );
        detailUserDTO.setUsername( user.getUsername() );
        detailUserDTO.setEmail( user.getEmail() );
        detailUserDTO.setTimeStatus( user.getTimeStatus() );
        detailUserDTO.setAddress( user.getAddress() );
        detailUserDTO.setAge( user.getAge() );

        return detailUserDTO;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setGender( UserMapper.toGenderString( user.getGender() ) );
        userDTO.setHobbies( UserMapper.toHobbiesString( user.getHobbies() ) );
        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setAge( user.getAge() );

        return userDTO;
    }
}
