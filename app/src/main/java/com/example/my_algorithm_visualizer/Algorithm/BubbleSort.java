package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;

public class BubbleSort extends Sort{

    public BubbleSort(Context context) {
        super(context);
    }


    @Override
    public void sort(ArrayVisualizeView view, long timeDelay) {
        setCurrentStatus(ArrayStatus.SORTING);
        int n = intArray.length;
        for (int i = 0; i < n-1; i++){
            view.focusRect(i);
            for (int j = 0; j < n-i-1; j++){
                view.focusRect(j);
                if (intArray[j] > intArray[j+1])
                {
                    int temp = intArray[j];
                    intArray[j] = intArray[j+1];
                    intArray[j+1] = temp;

                    view.swapRect(j,j+1);
                }
                setComparisonsCount(getComparisonsCount()+1);
                delay(timeDelay);
                if(i!=j)
                    view.unfocusRect(j);
            }
            view.unfocusRect(i);
        }
        setCurrentStatus(ArrayStatus.SORTED);



    }
}
