package com.example.tenant.service;

import com.example.tenant.vo.TenantInfo;

import java.util.List;

public interface ITenantInfoService {
    // 初始化租户信息
    List<TenantInfo> InitTenantInfo();
}
