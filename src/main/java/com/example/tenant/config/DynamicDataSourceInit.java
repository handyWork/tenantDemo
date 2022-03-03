package com.example.tenant.config;

import com.example.tenant.dataSource.DynamicDataSource;
import com.example.tenant.service.ITenantInfoService;
import com.example.tenant.util.ApplicationContextProvider;
import com.example.tenant.vo.TenantInfo;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化动态数据源
 *
 * @author guomh
 * @date 2019/11/06
 */
@Slf4j
@Configuration
public class DynamicDataSourceInit {

    @Autowired
    private ITenantInfoService tenantInfoService;

    @Autowired
    private ApplicationContextProvider provider;

    @PostConstruct
    public void InitDataSource() {
        log.info("=====初始化动态数据源=====");

        DynamicDataSource dynamicDataSource = (DynamicDataSource) provider.getBean("dynamicDataSource");
        HikariDataSource master = (HikariDataSource) provider.getBean("master");
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", master);

        List<TenantInfo> tenantList = tenantInfoService.InitTenantInfo();
        for (TenantInfo tenantInfo : tenantList) {
            log.info(tenantInfo.toString());
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setDriverClassName(tenantInfo.getDatasourceDriver());
            dataSource.setJdbcUrl(tenantInfo.getDatasourceUrl());
            dataSource.setUsername(tenantInfo.getDatasourceUsername());
            dataSource.setPassword(tenantInfo.getDatasourcePassword());
            dataSource.setDataSourceProperties(master.getDataSourceProperties());
            dataSourceMap.put(tenantInfo.getTenantId(), dataSource);
        }
        //设置数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        /**
         * 必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效
         * 相当于定义一个map   tenantId,对应的数据源
         */
        dynamicDataSource.afterPropertiesSet();
    }

}

