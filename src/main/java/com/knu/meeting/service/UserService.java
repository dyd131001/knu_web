package com.knu.meeting.service;
import com.knu.meeting.mapper.UserMapper;
import com.knu.meeting.model.constant.Hobby;
import com.knu.meeting.model.constant.Role;
import com.knu.meeting.model.dto.CreateUserDTO;
import com.knu.meeting.model.dto.DetailUserDTO;
import com.knu.meeting.model.dto.UserDTO;
import com.knu.meeting.model.entity.User;
import com.knu.meeting.model.vo.Address;
import com.knu.meeting.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public DetailUserDTO findDetailUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        Hibernate.initialize(user.getParticipations());
        return UserMapper.INSTANCE.toDetailUserDTO(user);

    }
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    public boolean updateAddress(Long id , Address address){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;
        user.updateAddress(address);
        userRepository.save(user);
        return user.getAddress().equals(address);
    }

    public boolean updateHobbies(Long id , List<String> hobbies){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;
        List<Hobby> hobbiesEnum = hobbies.stream().map(Hobby::findHobby)
                .collect(Collectors.toList());
        user.updateHobbies(hobbiesEnum);
        userRepository.save(user);
        return user.getHobbies().equals(hobbiesEnum);
    }

    public boolean updatePassword(Long id , String  password){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;
        user.updatePassword(password);
        userRepository.save(user);
        return user.getPassword().equals(password);
    }


    public Long create(CreateUserDTO createUserDTO){
        User user = UserMapper.INSTANCE.createUserDTOToEntity(Role.ADMIN,createUserDTO);
        validationDuplicationUser(user);
        userRepository.save(user);
        return user.getId();

    }

    public void validationDuplicationUser(User user) {
         userRepository.findByUsername(user.getUsername())
                 .ifPresent(u -> {throw new EntityNotFoundException("username Duplication" + u.getUsername());});
    }


    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::toUserDTO)
                .collect(Collectors.toList());
    }
}
