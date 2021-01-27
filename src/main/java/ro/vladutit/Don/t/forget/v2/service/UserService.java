package ro.vladutit.Don.t.forget.v2.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.User;
import ro.vladutit.Don.t.forget.v2.model.UserData;
import ro.vladutit.Don.t.forget.v2.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(UserData userData) throws UserAlreadyExistException {
        if (checkIfUserExist(userData.getEmail())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User user = new User();
        BeanUtils.copyProperties(userData, user);
        encodePassword(user, userData);
        userRepository.save(user);
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
}
