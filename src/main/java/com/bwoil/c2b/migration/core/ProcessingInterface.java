package com.bwoil.c2b.migration.core;

import java.util.List;

public interface ProcessingInterface<T> {

    List<T> process(List<T> items);

}
