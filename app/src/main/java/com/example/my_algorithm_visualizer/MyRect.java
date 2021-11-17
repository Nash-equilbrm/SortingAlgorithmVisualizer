package com.example.my_algorithm_visualizer;

import android.graphics.Canvas;


public class MyRect extends Shape {
    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(P1.x,P1.y,P2.x,P2.y,paint);
    }


}
