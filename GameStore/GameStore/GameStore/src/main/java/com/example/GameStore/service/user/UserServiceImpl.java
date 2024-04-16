package com.example.GameStore.service.user;

import com.example.GameStore.entity.Role;
import com.example.GameStore.entity.User;
import com.example.GameStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        validateUserData(user);
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại!");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại!");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(encodedPassword);
        user.setRoles(Role.USER);
        return userRepository.save(user);
    }

    private void validateUserData(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Người dùng không được null");
        }
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("Tên người dùng không được trống");
        }
        if (user.getUserName().length() < 6 || user.getUserName().length() > 10) {
            throw new IllegalArgumentException("Tên người dùng phải có từ 6 đến 10 ký tự");
        }

        if (user.getPassWord() == null || user.getPassWord().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được trống");
        }
        if (!isValidPassword(user.getPassWord())) {
            throw new IllegalArgumentException("Mật khẩu không đáp ứng yêu cầu: ít nhất một chữ hoa, một chữ thường và một ký tự đặc biệt");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email không được trống");
        }
        if (!isValidEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }

    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new EntityNotFoundException("Không tìm thấy người dùng với id: "+userId);
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("Không tìm thấy người dùng với id: "+userId);
        }
        validateUserData(updatedUser);
        User existingUser = userOptional.get();
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setPassWord(passwordEncoder.encode(updatedUser.getPassWord()));
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("Không tìm thấy người dùng với id: "+userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User login(String userName, String password) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (!userOptional.isPresent()){
            return null;
        }
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassWord())){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public boolean checkUserRole(User user, Role role) {
        return false;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setPassWord(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }else {
            throw new EntityNotFoundException("Không tìm thấy người dùng với id: "+userId);
        }

    }
}
