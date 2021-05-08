package kk.crm.controller;

import io.jsonwebtoken.Jwt;
import kk.crm.entity.Platform;
import kk.crm.entity.ResEntity;
import kk.crm.util.JWTUtil;
import kk.crm.util.ResGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ResEntity getPlatform(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("role","user");
        final String token = JWTUtil.createToken(map);
        return ResGenerator.success(token);
    }
}
