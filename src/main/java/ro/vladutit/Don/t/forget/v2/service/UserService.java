package ro.vladutit.Don.t.forget.v2.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.model.UserData;
import ro.vladutit.Don.t.forget.v2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public void register(UserData userData) throws UserAlreadyExistException {
        if (checkIfUserExist(userData.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = new User();
        BeanUtils.copyProperties(userData, user);
        encodePassword(user, userData);
        userRepository.save(user);
    }

    public void sendNotificationEmail(User user, String message, String subject)
            throws UnsupportedEncodingException, MessagingException {
        String senderName = "Don't forget app team";
        String mailContent = "<p>Dear " + user.getFullname() + ", <br><br>" + message +
                "<br><br>Thank you!<br>The Don't forget app team.</p>";

        MimeMessage message1 = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message1);

        helper.setFrom("dontforget@vladutit.ro", senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        mailSender.send(message1);
    }

    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    private void encodePassword(User user, UserData userData) {
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return  users;
    }

    public User getByEmail(String userEmail) { return userRepository.findByEmail(userEmail);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void changeUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}