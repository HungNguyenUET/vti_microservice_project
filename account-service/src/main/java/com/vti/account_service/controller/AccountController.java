package com.vti.account_service.controller;

import com.vti.account_service.dto.AccountDTO;
import com.vti.account_service.dto.DepartmentDTO;
import com.vti.account_service.entity.Account;
import com.vti.account_service.feignclient.DepartmentFeignClient;
import com.vti.account_service.service.IAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Value("${greeting.text}")
    private String greetingText;

    private final IAccountService acService;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final DepartmentFeignClient departmentFeignClient;

    public List<AccountDTO> getListAccounts() {

        List<Account> accounts = acService.getListAccounts();

        List<AccountDTO> listAccountDTO = modelMapper.map(
                accounts,
                new TypeToken<List<AccountDTO>>() {}.getType());

        return listAccountDTO;
    }

    @GetMapping("/hello")
    public String hello() {
        return greetingText;
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentByAccountId(@PathVariable final int id) {
        Account account = acService.findAccountById(id);
        int departmentId = account.getDepartment().getId();
//        DepartmentDTO departmentDTO = restTemplate.getForObject("http://localhost:8080/api/v1/departments/" + departmentId, DepartmentDTO.class);
        DepartmentDTO departmentDTO = departmentFeignClient.getDepartmentById(departmentId);
        log.info("Department DTO: {}", departmentDTO);
        return departmentDTO;
    }
}
