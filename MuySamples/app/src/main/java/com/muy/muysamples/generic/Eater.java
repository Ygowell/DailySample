package com.muy.muysamples.generic;

/**
 * Created by James on 2020/6/7.
 * Desc:
 */
interface Eater<T extends Food> {
    void eat(T food);
}
