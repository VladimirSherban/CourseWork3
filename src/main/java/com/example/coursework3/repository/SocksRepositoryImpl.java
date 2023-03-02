package com.example.coursework3.repository;

import com.example.coursework3.model.Socks;
import com.example.coursework3.model.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SocksRepositoryImpl implements SocksRepository {

    private HashMap<Socks, Integer> socksMap = new HashMap<>();

    @Override

    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    @Override
    public Integer remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();

        if (socksMap.containsKey(socks)) {
            Integer quantity = socksMap.get(socks);

            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }
}
