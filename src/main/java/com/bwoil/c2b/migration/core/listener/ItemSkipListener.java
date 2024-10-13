package com.bwoil.c2b.migration.core.listener;

import com.bwoil.c2b.migration.core.util.CsvUtil;
import org.springframework.batch.core.SkipListener;

import java.io.IOException;

public class ItemSkipListener<O, T> implements SkipListener<O, T> {

    private final String name;

    public ItemSkipListener(String name) {
        this.name = name;
    }

    @Override
    public void onSkipInRead(Throwable t) {
        try {
            CsvUtil.saveReadError(name, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSkipInWrite(T item, Throwable t) {
        try {
            CsvUtil.saveWriteError(name, item, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSkipInProcess(O item, Throwable t) {
        try {
            CsvUtil.saveProcessError(name, item, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
