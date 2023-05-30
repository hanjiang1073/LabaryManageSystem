package org.example.dao;

import org.example.entity.Manager;
import org.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserDao {

    User login(User user);
    Boolean register(User user);

}
