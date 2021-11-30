package com.example.my_algorithm_visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ArrayVisualizeView extends View {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private float viewWidth;
    private float viewHeight;

    public ArrayVisualizeView(Context context) {
        super(context);
        viewWidth = convertDpToPx(this.getContext(),400);
        viewHeight = convertDpToPx(this.getContext(),400);

    }

    public ArrayVisualizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewWidth = convertDpToPx(this.getContext(),400);
        viewHeight = convertDpToPx(this.getContext(),400);
    }

    //Draw rectangles on view
    public void visualize(int[] arr){
        // initialize rects array
        initRects(arr);

        // draw rectangles
        this.invalidate();
        Log.i("Tag","Thread name:" + Thread.currentThread().getName());
    }

    //Highlight selected rectangles on view

    public void focusRect(int index){
        shapes.get(index).paint.setColor(Color.RED);
    }

    public void unfocusRect(int index){
        shapes.get(index).paint.setColor(Color.GREEN);
    }

    //Swap 2 rectangles on view
    public void swapRect(int i,int j){
        int tmpy;
        tmpy = shapes.get(i).P2.y;
        shapes.get(i).P2.y = shapes.get(j).P2.y;
        shapes.get(j).P2.y = tmpy;

    }

    //Set value for rect
    public void setRectValue(int pos, int val){
        shapes.get(pos).P2.y = (int)(val*viewHeight/this.getContext()
                .getResources()
                .getInteger(R.integer.array_max_size));
    }

    private void initRects(int[] arr) {
        // clear shapes array first
        if(shapes.isEmpty()){
            // width of one bar = view width in px / array size
            int w = (int)(viewWidth/arr.length);

            //add rectangles to shapes array
            for(int k=0;k<arr.length;++k){
                Shape obj = new MyRect();
                obj.P1 = new Point(k*w,0);
                obj.P2 = new Point(obj.P1.x+w,(int)(arr[k]*viewHeight/this.getContext()
                        .getResources()
                        .getInteger(R.integer.array_max_size)));
                obj.paint = new Paint();
                obj.paint.setColor(Color.GREEN);
                shapes.add(obj);
            }

        }
    }


    public void shuffleRects(int[] arr){
        shapes.clear();
        initRects(arr);
    }

    public void reset(){
        shapes.clear();
    }


    private void deleteCanvasContent(Canvas canvas){
        canvas.drawColor(Color.WHITE);
    }



    private float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        deleteCanvasContent(canvas);
        for (int i = 0; i < shapes.size(); ++i) {
            shapes.get(i).draw(canvas);
        }
    }
}
