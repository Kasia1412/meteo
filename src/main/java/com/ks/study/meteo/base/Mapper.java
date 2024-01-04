package com.ks.study.meteo.base;

public interface Mapper<S, T> {
    T map(S source);
}
