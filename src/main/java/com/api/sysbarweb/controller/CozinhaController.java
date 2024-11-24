package com.api.sysbarweb.controller;
import com.api.sysbarweb.services.CozinhaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CozinhaController {
    @Autowired
    CozinhaServices services;
}
