package com.example.tenant.controller;


import com.example.tenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录接口
     * @param tenantId  租户id
     * @return
     */
    @GetMapping("/login")
    public HashMap<String, Object> login(@RequestParam String tenantId) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = sra.getRequest().getSession(true);
        session.setAttribute("tenantId", tenantId);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("200", "登录成功");
        return map1;
    }

    @GetMapping("/testTenant")
    public HashMap<String, Object> testTenant() {

        String tenantName = userService.loadTenantName("0001");
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("200", "当前租户名称" + tenantName);
        return map1;
    }


}
