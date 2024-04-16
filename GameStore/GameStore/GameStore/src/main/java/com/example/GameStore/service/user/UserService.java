package com.example.GameStore.service.user;

import com.example.GameStore.entity.Role;
import com.example.GameStore.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User getUserById(Long userId);

    List<User> getAllUser();

    User createUser(User user);

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);

    User login(String username, String password);

    boolean checkUserRole(User user, Role role);

    void changePassword(Long userId, String newPassword);


}
