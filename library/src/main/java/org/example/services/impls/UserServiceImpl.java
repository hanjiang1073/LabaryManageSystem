package org.example.services.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.UserMapper;
import org.example.entity.User;
import org.example.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private UserMapper userMapper;

    public boolean login(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username", user.getUsername());
        System.out.println("login");

        User temp = userMapper.selectOne(queryWrapper);

        if(temp==null){

            return false;
        }

        if(temp.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
    public  boolean register(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("username", user.getUsername());
        System.out.println("register");

        User temp = userMapper.selectOne(queryWrapper);

        if(temp==null){
            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            int id =0;
            Random random = new Random();
            User temp1 = null;
           boolean out = true;
            while(out){
            id = random.nextInt(100) + 1;
            queryWrapper1.eq("id", id);
            temp1 = userMapper.selectOne(queryWrapper1);
            if(temp1 == null){
                user.setId(id);
                out = true;
            }
            out = false;
            }


            userMapper.insert(user);
            return true;
        }

        return false;
    }

}
