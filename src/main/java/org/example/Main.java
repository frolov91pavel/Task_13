package org.example;

import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CacheProxy cacheProxy = new CacheProxy(Paths.get("cache_data"), false);
        Service service = cacheProxy.cache(new ServiceImpl());

        System.out.println("Первый вызов run:");
        List<String> r1 = service.run("work1", 10, new Date());
        System.out.println(r1);

        System.out.println("Второй вызов run (из кэша):");
        List<String> r2 = service.run("work1", 10, new Date());
        System.out.println(r2);

        System.out.println("Вызов work:");
        List<String> w1 = service.work("item");
        System.out.println("Size of w1: " + w1.size());

        // Повторный вызов work (из кэша):
        List<String> w2 = service.work("item");
        System.out.println("Size of w2: " + w2.size());
    }
}