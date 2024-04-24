package org.example.sevices.implementation;

import ch.qos.logback.core.testUtil.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.example.models.Smth;
import org.example.repositories.SmthRepository;
import org.example.sevices.interfaces.SmthService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SmthServiceImplementation implements SmthService {

    private final SmthRepository repo;

    @Override
    public void save() {
        Smth tmp = new Smth();
        tmp.setNumber(RandomUtil.getPositiveInt());
        repo.save(tmp);
    }
}
