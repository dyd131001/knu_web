package com.knu.meeting.global.config;

import com.knu.meeting.repository.MeetingRepository;
import com.knu.meeting.repository.ParticipationRepository;
import com.knu.meeting.repository.UserRepository;
import com.knu.meeting.repository.impl.MeetingRepositoryImpl;
import com.knu.meeting.repository.impl.ParticipationRepositoryImpl;
import com.knu.meeting.repository.impl.UserRepositoryImpl;
import com.knu.meeting.service.MeetingService;
import com.knu.meeting.service.ParticipationService;
import com.knu.meeting.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    @PersistenceContext
    private EntityManager entityManager;


    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }
    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl(entityManager);
    }

    @Bean
    public MeetingService meetingService(){
        return new MeetingService(meetingRepository());
    }
    @Bean
    public MeetingRepository meetingRepository(){
        return new MeetingRepositoryImpl(entityManager);
    }


    @Bean
    public ParticipationService participationService(){
        return new ParticipationService(participationRepository());
    }
    @Bean
    public ParticipationRepository participationRepository(){
        return new ParticipationRepositoryImpl(entityManager);
    }
}
