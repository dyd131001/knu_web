package com.knu.meeting.mapper;

import com.knu.meeting.model.constant.Gender;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


// mapstruct가 구현체를 만들어줌

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    //@Mapping(source = "nick", target = "nickName") 서로 다른 이름 매핑
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    @Mapping(source = "userDTO.gender", target = "gender", qualifiedByName = "toGenderEnum")
    @Mapping(source = "userDTO.hobbies", target = "hobbies", qualifiedByName = "toHobbiesEnum")
    //@Mapping(source = "userDTO.timeStatus", target="timeStatus", ignore=true)
    User userDTOToEntity(Role role, UserDTO userDTO);

    @Mapping(source = "user.gender", target = "gender", qualifiedByName = "toGenderString")
    @Mapping(source = "user.hobbies", target = "hobbies", qualifiedByName = "toHobbiesString")
    UserDTO userEntityToDTO(User user);

    @Named("toGenderEnum")
    static Gender toGender(String gender) {
        switch (gender) {
            case "남":
                return Gender.MALE;
            case "여":
                return Gender.FEMALE;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Named("toHobbiesEnum")
    static List<Hobby> toHobbies(List<String> hobbies){
        return hobbies.stream().map(Hobby::findHobby)
                .collect(Collectors.toList());
    }
    @Named("toHobbiesString")
    static List<String> toHobbiesString(List<Hobby> hobbies){
        return hobbies.stream().map(hobby -> hobby.getValue())
                .collect(Collectors.toList());
    }

    @Named("toGenderString")
    static String toGenderString(Gender gender){
        return gender.getGender();
    }


    /* 두가지 dto를 합친 dto 만들기
    @Mapping(source="pageDto.pageIndex", target="pageIdx"
    @Mapping(source="pageDto.pageCount", target="pageCnt")
    MessageServiceDto toMessageServiceDto(PageDto pageDto, RequestDto requestDto);
    */
}

/* 사용 예시
    // Entity -> DTO
    UserDTO resultDTO = UserMapper.INSTANCE.userEntityToDTO(userEntity);
    // DTO -> Entity, Setter가 없어도 @Builder가 붙어있다면 변환 가능
    UserEntity resultEntity = UserMapper.INSTANCE.userDTOToEntity(resultDTO);
 */
