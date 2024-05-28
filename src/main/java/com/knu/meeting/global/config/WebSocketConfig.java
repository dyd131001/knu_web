package com.knu.meeting.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@Slf4j
@EnableWebSocketMessageBroker //웹 소켓 메시지를 다룰 수 있게 허용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //발행자가 "/topic/xx"의 경로로 메시지를 주면 이를 구독하는 구독자들에게 처리를 거치지 않고 바로 전달
        //구독자는 "/topic/xx"로 토픽을 구독할 수 있음.
        config.enableSimpleBroker("/topic");
        // 발행자가 "/app/xx"의 경로로 메시지를 주면 가공을 해서 구독자들에게 전달 (@SendTo("topic/xx")로 최종적으로 메세지를 보냄)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 해당 경로로 소켓을 만들수있음. 소켓을 만들고 /topic/xx으로 원하는 토픽을 구독할 수 있음.
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }

}