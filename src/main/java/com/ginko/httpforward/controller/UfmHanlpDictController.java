package com.ginko.httpforward.controller;

import com.ginko.httpforward.entity.UfmHanlpDict;
import com.ginko.httpforward.repository.UfmHanlpDictRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v0/ufm/hanlp/dict", produces = "application/json;charset=UTF-8")
public class UfmHanlpDictController {

    private final UfmHanlpDictRepository repo;

    public UfmHanlpDictController(UfmHanlpDictRepository repo) {
        this.repo = repo;
    }

    // 查詢所有詞
    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<UfmHanlpDict> list() {
        return repo.findAll();
    }
}
