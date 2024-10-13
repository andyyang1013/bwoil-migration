package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.util.CsvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

import java.io.IOException;

public class ReadListener<O> implements ItemReadListener<O> {

    private final String name;

    public ReadListener(String name) {
        this.name = name;
    }

    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(O item) {
        try {
            CsvUtil.saveReadSuccess(name, item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReadError(Exception ex) {
        try {
            CsvUtil.saveReadError(name, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
