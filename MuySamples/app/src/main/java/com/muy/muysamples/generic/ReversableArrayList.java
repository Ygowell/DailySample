package com.muy.muysamples.generic;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by James on 2020/6/7.
 * Desc:
 */
public class ReversableArrayList<T> extends ArrayList<T> {

    public void reverse() {
        Collections.reverse(this);
    }
}