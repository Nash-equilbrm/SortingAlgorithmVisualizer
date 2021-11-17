package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;
import com.example.my_algorithm_visualizer.R;

import java.util.Random;

public abstract class Sort {

    private Context context;
    protected int[] intArray;
    private long comparisonsCount=0;



    protected ArrayStatus currentStatus = ArrayStatus.NOT_SORTED;


    public Sort(Context context){
        this.context = context;
    }

    public ArrayStatus getCurrentStatus(){
        return currentStatus;
    }

    public void setCurrentStatus(ArrayStatus status){
        currentStatus = status;
    }


    public long getComparisonsCount() {
        return comparisonsCount;
    }

    public void setComparisonsCount(long value){
        comparisonsCount = value;
    }

    public void sort(ArrayVisualizeView view, long timeDelay){
        //... abstract sort function
    }

    public int[] getArr(){
        return intArray;
    }


    public void randomArray(int limit) {
        Random rd = new Random();
        intArray = new int[limit];

        for(int i=0;i<intArray.length;++i){
            intArray[i] = rd.nextInt(context.getResources().getInteger(R.integer.array_max_size))+1;
        }

    }



    protected void delay(long milisec){
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
