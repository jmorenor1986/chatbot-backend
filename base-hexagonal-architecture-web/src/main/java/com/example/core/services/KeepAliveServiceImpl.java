package com.example.core.services;

import com.example.ports.primary.KeepAliveService;
import org.springframework.stereotype.Service;

@Service
public class KeepAliveServiceImpl implements KeepAliveService {
    @Override
    public String getAlive() {
        return "Hi!  it´s alive";
    }
}
