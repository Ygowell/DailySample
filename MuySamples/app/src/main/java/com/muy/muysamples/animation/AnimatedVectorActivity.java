package com.muy.muysamples.animation;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.muy.muysamples.R;

public class AnimatedVectorActivity extends Activity {

    private ImageView mFontIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_vertor);

        mFontIv = findViewById(R.id.imageView);
        mFontIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = mFontIv.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        });
    }

}
