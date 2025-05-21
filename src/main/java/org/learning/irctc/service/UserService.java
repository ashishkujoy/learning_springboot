package org.learning.irctc.service;

import org.learning.irctc.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUserDetails(String user) {
        System.out.println("UserService: fetching details for user " + user);
        userRepository.getUser(user);
    }
}
