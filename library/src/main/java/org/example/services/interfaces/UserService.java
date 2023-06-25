package org.example.services.interfaces;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Manager;
import org.example.entity.User;

import java.util.List;


public interface UserService extends IService<User> {


    public boolean login(User user);
    public boolean register(User user);

}
