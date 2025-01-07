package org.example;

import java.util.Date;
import java.util.List;

public interface Service {
    @org.example.Cache(
            cacheType = org.example.CacheType.FILE,
            fileNamePrefix = "data",
            zip = true,
            identityBy = {String.class, double.class})
    List<String> run(String item, double value, Date date);

    @org.example.Cache(
            cacheType = org.example.CacheType.IN_MEMORY,
            listLimit = 100_000)
    List<String> work(String item);
}