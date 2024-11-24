package com.api.sysbarweb.services;

import com.api.sysbarweb.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CozinhaServices {
    @Autowired
    CozinhaRepository repository;
}
