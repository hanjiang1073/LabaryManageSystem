package org.example.services.impls;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库或其他数据源中获取用户信息
        // 在这里实现您的逻辑，返回一个实现了UserDetails接口的用户信息对象
        // 如果找不到用户，则抛出UsernameNotFoundException异常
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}


