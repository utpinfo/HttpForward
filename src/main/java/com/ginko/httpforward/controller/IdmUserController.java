package com.ginko.httpforward.controller;

import com.ginko.httpforward.entity.IdmUser;
import com.ginko.httpforward.repository.IdmUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v0/idm/users", produces = "application/json;charset=UTF-8")
public class IdmUserController {

    private final IdmUserRepository repo;

    public IdmUserController(IdmUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<IdmUser> getAllUsers() {
        List<IdmUser> users = repo.findAll();
        System.out.println("数据库查到的用户数: " + users.size());
        users.forEach(System.out::println);
        return users;
    }
}
