package com.ks.study.meteo.base;

import java.util.Map;

public interface MapperParametrized<S, T, P> {
    T map(S source, Map<String, P> params);
}
