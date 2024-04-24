package org.example.sevices.implementation;

import lombok.RequiredArgsConstructor;
import org.example.sevices.interfaces.SheduledService;
import org.example.sevices.interfaces.SmthService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SheduledServiceImplementation implements SheduledService {
    private StringRedisTemplate stringRedisTemplate;
    private SmthService service;
    @Override
    public void script() {
        if(stringRedisTemplate.opsForValue().setIfAbsent("mutex", "LOCKED")){
            service.save();
            System.out.println("No recording, continue working");
            stringRedisTemplate.expire("mutex", 5, TimeUnit.SECONDS);// Время жизни ключа в 5 секунд
        }
        else System.out.println("Such record is already exist, exiting");
    }
}
