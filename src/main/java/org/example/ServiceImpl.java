package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceImpl implements Service {

    @Override
    public List<String> run(String item, double value, Date date) {
        // тяжелая операция, например сортировка большого массива
        try {
            Thread.sleep(2000); // имитация тяжелой операции
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<String> result = new ArrayList<>();
        result.add("Item: " + item);
        result.add("Value: " + value);
        result.add("Date: " + date);
        return result;
    }

    @Override
    public List<String> work(String item) {
        try {
            Thread.sleep(1000); // имитация тяжелой операции
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            result.add(item + "_" + i);
        }
        return result;
    }
}