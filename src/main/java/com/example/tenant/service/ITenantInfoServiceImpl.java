package com.example.tenant.service;

import com.example.tenant.vo.TenantInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个到时候可以做为一个查询数据源的方法   初始化的时候加载进去
 */
@Service("iTenantInfoService")
public class ITenantInfoServiceImpl implements  ITenantInfoService {
    @Override
    public List<TenantInfo> InitTenantInfo() {

        // 将所有的租户数据源加载进去
        ArrayList<TenantInfo> objects = new ArrayList<>();
        TenantInfo tenantInfo = new TenantInfo();
        tenantInfo.setDatasourceDriver("com.mysql.jdbc.Driver");
        tenantInfo.setDatasourceUrl("jdbc:mysql://localhost:3306/ccb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        tenantInfo.setDatasourceUsername("root");
        tenantInfo.setDatasourcePassword("Cl0vdsec.mysql");
        tenantInfo.setTenantId("0001");
        objects.add(tenantInfo);
//
        TenantInfo tenantInfo2 = new TenantInfo();
        tenantInfo2.setDatasourceDriver("com.mysql.jdbc.Driver");
        tenantInfo2.setDatasourceUrl("jdbc:mysql://localhost:3306/blade?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        tenantInfo2.setDatasourceUsername("root");
        tenantInfo2.setDatasourcePassword("Cl0vdsec.mysql");
        tenantInfo2.setTenantId("0002");
        objects.add(tenantInfo2);
        return objects;
    }
}
