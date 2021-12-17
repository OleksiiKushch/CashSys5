package com.finalprojultimate.model.validation;

@FunctionalInterface
public interface NullChecker<T> {
    boolean isEmpty(T object);
}
