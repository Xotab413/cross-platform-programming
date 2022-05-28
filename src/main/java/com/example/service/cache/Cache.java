package com.example.service.cache;

import com.example.service.process.ProcessedParams;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Cache {

    static public Map<Integer, ProcessedParams> cache = new HashMap<>();

    public void add(Integer year, ProcessedParams params) {
        if (!cache.containsKey(year)) {
            cache.put(year, params);
        }
    }

    public void printMap() {
        System.out.println(cache);
    }

    public @Nullable ProcessedParams find(Integer year) {
        if (cache.containsKey(year)) {
            return cache.get(year);
        }
        return null;
    }


    public static String getStaticCache() {
        return ("Cache:\n" + cache);
    }

}
