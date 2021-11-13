package com.finalprojultimate.controller.validation;

@FunctionalInterface
public interface NullChecker<T> {
    boolean isEmpty(T object);
}
