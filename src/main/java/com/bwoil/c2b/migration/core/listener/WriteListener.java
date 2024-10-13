package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.util.CsvUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.io.IOException;
import java.util.List;

public class WriteListener<T> implements ItemWriteListener<T> {

    private final String name;

    public WriteListener(String name) {
        this.name = name;
    }

    @Override
    public void beforeWrite(List<? extends T> items) {

    }

    @Override
    public void afterWrite(List<? extends T> items) {
        try {
            CsvUtil.saveWriteSuccess(name, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWriteError(Exception exception, List<? extends T> items) {
        try {
            System.err.println("write error: " + exception.toString());
            CsvUtil.saveWriteError(name, items, exception);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
