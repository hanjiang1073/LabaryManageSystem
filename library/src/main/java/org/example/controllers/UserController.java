package org.example.controllers;

import org.example.common.Result;
import org.example.entity.JwtUtil;
import org.example.entity.Manager;
import org.example.entity.User;
import org.example.services.interfaces.UserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    @Resource
    private JwtUtil jwtUtil;

    @RequestMapping("/test")
    public Map<String, Object> test(@RequestParam("token") String jwt) throws Exception {
        //这个步骤可以使用自定义注解+AOP编程做解析jwt的逻辑，这里为了简便就直接写在controller里
        System.out.println("66666");
        Claims claims = JwtUtil.parseJWT(jwt);
        System.out.println(claims);
        HashMap<String, Object> map = new HashMap<>();
        String name = claims.get("username", String.class);
        map.put("username", name);
        map.put("code", "0");
        map.put("msg", "请求成功");
        return map;
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        User res = userService.login(user);
        if(res != null){
            Map<String,String> info = new HashMap<>();
            info.put("username",user.getUsername());
            String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user.getUsername(), null);
            info.put("token",token);
            System.out.println(info);
            return Result.ok(info);
        }else{
            return Result.error(null);

        }
    }
    @RequestMapping("/register")
    public Result<Object> register(@RequestBody User user) {
        userService.register(user);
        Map<String,String> info = new HashMap<>();
        info.put("username",user.getUsername());
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), user.getUsername(), null);
        info.put("token",token);
        System.out.println(info);
        return Result.ok(info);
    }


}
