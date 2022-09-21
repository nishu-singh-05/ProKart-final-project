package com.project.authenticationservice.kafka;


import com.project.authenticationservice.model.UserServiceData;
import com.project.authenticationservice.repository.UserRepository;
import com.project.usermodule.kafka.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    private UserRepository userRepository;



    public UserConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(UserDto event){
        LOGGER.info(String.format("User event received in authentication service => %s", event.toString()));

        //can save user data here

        UserServiceData userServiceData=new UserServiceData();
        userServiceData.setUserName(event.getUserName());
        userServiceData.setPassword(event.getPassword());
        userServiceData.setRole(event.getRole());

        userRepository.save(userServiceData);

    }
}
