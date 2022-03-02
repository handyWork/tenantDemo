package com.example.tenant.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    /**
     * 通过租户id获取租户名称
     * @param tenantId
     * @return
     */
    String loadTenantName(String tenantId);
}
