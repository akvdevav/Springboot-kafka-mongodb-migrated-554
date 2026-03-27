package com.rajeshkawali.service;

import com.rajeshkawali.entity.User;
import com.rajeshkawali.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserService{
    private static final String QUEUE_NAME = "myQueue";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<User> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user;
    }

    public String sendSimpleMessage(User user) {
        userRepository.save(user);
        rabbitTemplate.convertAndSend(QUEUE_NAME, user);
        return "Success";
    }

    public String sendCallbackMessage(User user) {
        userRepository.save(user);
        rabbitTemplate.convertAndSend(QUEUE_NAME, user);
        return "Success";
    }
}