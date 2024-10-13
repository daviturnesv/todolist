package br.com.daviturnesv.todolist.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class FirstController {


    public String firstMessage(){
        return "Funcionou";
    }
}
