package com.acciojob.BookMyShowMAY.Services;

import com.acciojob.BookMyShowMAY.Models.User;
import com.acciojob.BookMyShowMAY.Repositories.UserRepository;
import com.acciojob.BookMyShowMAY.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public String addUser(AddUserRequest addUserRequest){

        User user= User.builder().age(addUserRequest.getAge())
                .emailId(addUserRequest.getEmailId())
                .name(addUserRequest.getName())
                .mobileNo(addUserRequest.getMobileNo())
                .build();

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(addUserRequest.getEmailId());
        mailMessage.setFrom("se.hasanjafar@gmail.com");
        mailMessage.setSubject("Welcome to Book my show Application!!");
        String body="hi "+addUserRequest.getName()+"! Enjoy Welcome Bonus 10%off on tickets";
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);

        user=userRepository.save(user);

        return "User has been saved to DB with id="+user.getUserId();

    }
}
