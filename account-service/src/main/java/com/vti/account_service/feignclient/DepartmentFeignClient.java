package com.vti.account_service.feignclient;

import com.vti.account_service.dto.DepartmentDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RibbonClient(name = "department-service")
@FeignClient(name = "department-service", path = "/api/v1")
public interface DepartmentFeignClient {

    @GetMapping("/departments/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") int id);
}
