package com.muy.muysamples.generic;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.muy.muysamples.App;
import com.muy.muysamples.generic.model.Apple;
import com.muy.muysamples.generic.model.Fruit;

import java.util.ArrayList;

/**
 * Created by James on 2020/6/7.
 * Desc:
 */
public class Test {

    private void main() {
        AppleArrayList apples = new AppleArrayList();
        apples.add(new Apple());

        ArrayList<? extends Fruit> fruits = new ArrayList<Apple>();

        Object apple = fruits.get(0);
//        fruits.add(new Fruit()); // Can't do this

        ArrayList<? extends View> list = new ArrayList<TextView>();

        View view = list.get(0);

        ArrayList<? super AppCompatTextView> list1 = new ArrayList<TextView>();

//        list1.add(new TextView());
//        AppCompatTextView textView = list1.get(0);
    }
}
