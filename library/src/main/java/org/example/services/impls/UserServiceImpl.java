package org.example.services.impls;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public User login(User user) {
        return userDao.login(user);
    }
    public  void register(User user) {
        userDao.register(user);
    }

}
