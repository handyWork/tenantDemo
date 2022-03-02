package com.example.tenant.service;

import com.example.tenant.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String loadTenantName(String tenantId) {
        String tenantName = userMapper.loadTenantName(tenantId);
        return tenantName;
    }
}
