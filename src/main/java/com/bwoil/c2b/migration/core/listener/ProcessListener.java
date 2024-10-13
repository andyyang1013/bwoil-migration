package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.util.CsvUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemProcessListener;

import java.io.IOException;

public class ProcessListener<O, T> implements ItemProcessListener<O, T> {

    private final String name;

    public ProcessListener(String name) {
        this.name = name;
    }

    @Override
    public void beforeProcess(O item) {

    }

    @Override
    public void afterProcess(O item, T result) {

    }

    @Override
    public void onProcessError(O item, Exception e) {
        try {
            System.err.println("process error: " + e.toString());
            CsvUtil.saveProcessError(name, item, e);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
