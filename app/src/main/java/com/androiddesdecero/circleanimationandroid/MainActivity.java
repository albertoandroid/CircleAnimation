package com.androiddesdecero.circleanimationandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = findViewById(R.id.circleView);
        play = findViewById(R.id.play);

        play.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        CircleAngleAnimation animation = new CircleAngleAnimation(circleView, 360);
                        animation.setDuration(3000);
                        animation.setInterpolator(new LinearInterpolator());
                        circleView.startAnimation(animation);
                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                circleView.clearAnimation();
                                circleView.setAngle(0);
                                circleView.requestLayout();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        break;
                    case MotionEvent.ACTION_UP:
                        circleView.clearAnimation();
                        circleView.setAngle(0);
                        circleView.requestLayout();
                        break;
                }
                return true;
            }
        });
    }
}
