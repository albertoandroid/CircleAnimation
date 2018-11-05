package com.androiddesdecero.circleanimationandroid;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/*
La clase Canvas representa una hoja de papel
La clase Paint representa el lapíz con el que vamos a pintar sobre el canvas
 */

public class CircleView extends View {
    private static final int START_ANGLE = -90;

    private Paint foregroundCirclePaint;
    private Paint backgroundCirclePaint;

    private RectF rectF;
    private int backgroundCircleColor;
    private int foregroundCircleColor;

    private float backgroundStrokeWidth;
    private float foregroundStrokeWidth;

    private float angle;

    public CircleView(Context context, AttributeSet attrs){
        super(context, attrs);
        readAttributesAndSetupField(context, attrs);
        setUpbackgroundcirclePaint();
        setUpForegorundcirclePaint();
    }

    public float getAngle(){
        return angle;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    @Override
    public void onDraw(final Canvas canvas){
        super.onDraw(canvas);
        //RectF - > Contine la cuatro cordenadas flotates de un rectangulo
        rectF = new RectF(0 + foregroundStrokeWidth / 2,
                0 + foregroundStrokeWidth / 2,
                getWidth() - foregroundStrokeWidth / 2,
                getHeight() - foregroundStrokeWidth / 2);
        //Coloreamos la pare del circulo que queremos ver
        canvas.drawArc(rectF, START_ANGLE, angle, false, foregroundCirclePaint);
        //Coloreamos el circulo de Background "El de color gris"
        canvas.drawCircle(getWidth() /2,
                getHeight() / 2,
                getWidth() / 2 - backgroundStrokeWidth / 2,
                backgroundCirclePaint);
    }

    private void readAttributesAndSetupField(Context context, AttributeSet attrs){
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CircleView,
                0,
                0);
        try {
            applyAttirbutes(context, typedArray);
        }finally {
            typedArray.recycle();
        }
    }

    private void applyAttirbutes(Context context, TypedArray typedArray){
        readBackCircleColorFromAttributes(typedArray);
        readForegroudnCircleColorFromAttributes(context, typedArray);
        foregroundStrokeWidth = typedArray.getDimension(R.styleable.CircleView_foregroundStrokeWidth, getDefaultStrokeWidth(context));
        backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircleView_backgroundStrokeWidth, getDefaultStrokeWidth(context));

    }

    private void readForegroudnCircleColorFromAttributes(Context context, TypedArray typedArray){
        ColorStateList colorStateList = typedArray.getColorStateList(R.styleable.CircleView_foregorundCircleColor);
        if(colorStateList != null){
            foregroundCircleColor = colorStateList.getDefaultColor();
        }
    }

    private void readBackCircleColorFromAttributes(TypedArray typedArray){
        ColorStateList colorStateList = typedArray.getColorStateList(R.styleable.CircleView_backgroundCircleColor);
        if(colorStateList != null){
            backgroundCircleColor = colorStateList.getDefaultColor();
        }
    }

    private int getDefaultStrokeWidth(Context context){
        return (int) context.getResources().getDisplayMetrics().density *10;
    }

    private void setUpForegorundcirclePaint(){
        foregroundCirclePaint = new Paint();
        foregroundCirclePaint.setColor(foregroundCircleColor);
        foregroundCirclePaint.setStyle(Paint.Style.STROKE);
        foregroundCirclePaint.setStrokeWidth(foregroundStrokeWidth);
    }

    private void setUpbackgroundcirclePaint(){
        backgroundCirclePaint = new Paint();
        backgroundCirclePaint.setColor(backgroundCircleColor);
        backgroundCirclePaint.setStyle(Paint.Style.STROKE);
        backgroundCirclePaint.setStrokeWidth(backgroundStrokeWidth);
    }
}
