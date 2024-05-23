package com.knu.meeting.mapper;

import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T21:50:08+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToEntity(Role role, UserDTO userDTO) {
        if ( role == null && userDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        if ( userDTO != null ) {
            user.gender( UserMapper.toGender( userDTO.getGender() ) );
            user.hobbies( UserMapper.toHobbies( userDTO.getHobbies() ) );
            user.username( userDTO.getUsername() );
            user.password( userDTO.getPassword() );
            user.email( userDTO.getEmail() );
            user.address( userDTO.getAddress() );
            user.age( userDTO.getAge() );
        }
        user.role( role );

        return user.build();
    }

    @Override
    public UserDTO userEntityToDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setGender( UserMapper.toGenderString( user.getGender() ) );
        userDTO.setHobbies( UserMapper.toHobbiesString( user.getHobbies() ) );
        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setTimeStatus( user.getTimeStatus() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setAge( user.getAge() );

        return userDTO;
    }
}
