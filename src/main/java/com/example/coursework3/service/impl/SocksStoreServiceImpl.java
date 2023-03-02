package com.example.coursework3.service.impl;

import com.example.coursework3.exception.ValidationException;
import com.example.coursework3.model.Color;
import com.example.coursework3.model.Size;
import com.example.coursework3.model.Socks;
import com.example.coursework3.model.SocksBatch;
import com.example.coursework3.repository.SocksRepository;
import com.example.coursework3.service.SocksStoreService;
import com.example.coursework3.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksStoreServiceImpl implements SocksStoreService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;


    @Override
    public void accept(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);

        socksRepository.save(socksBatch);
    }

    @Override
    public Integer issuance(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public Integer reject(SocksBatch socksBatch) {
        checkSocksBatch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public Integer getCount(Color color, Size size, Integer cottonMin, Integer cottonMax) {

        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new ValidationException();
        }

        Map<Socks, Integer> socksMap = socksRepository.getAll();
        for (Map.Entry<Socks, Integer> socksItem : socksMap.entrySet()) {
            Socks socks = socksItem.getKey();

            if (socks.getColor().equals(color) &&
                    socks.getSize().equals(size) &&
                    socks.getCottonPart() >= cottonMin &&
                    socks.getCottonPart() <= cottonMax) {
                return socksItem.getValue();
            }
        }
        return 0;
    }

    private void checkSocksBatch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
