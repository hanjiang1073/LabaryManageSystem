package org.example.services.interfaces;

import org.example.entity.Manager;
import org.example.entity.User;

import java.util.List;


public interface UserService {


    public User login(User user);
    public void register(User user);

}
