package com.example.boarddemo.controller;

import com.example.boarddemo.service.boardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class boardRestController {
    private boardService service;


}
