package com.androiddesdecero.circleanimationandroid;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by albertopalomarrobledo on 5/11/18.
 */

public class CircleAngleAnimation extends Animation {

    private CircleView circleView;
    private float startAngle;
    private float endAngle;

    public CircleAngleAnimation(CircleView circleView, int endAngle){
        this.startAngle = circleView.getAngle();
        this.endAngle = endAngle;
        this.circleView = circleView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation){
        float angle = startAngle + ((endAngle - startAngle)*interpolatedTime);
        Log.d("TAG1", "startAngle: " + startAngle + " endAngle: " + endAngle + " interpolatedTime: " + interpolatedTime);
        circleView.setAngle(angle);
        circleView.requestLayout();
    }
}
