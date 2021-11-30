package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;

public class InsertionSort extends Sort{
    public InsertionSort(Context context) {
        super(context);
    }

    @Override
    public void sort(ArrayVisualizeView view, long timeDelay) {
        setCurrentStatus(ArrayStatus.SORTING);
        int n = intArray.length;
        for (int i = 1; i < n; ++i) {
            view.focusRect(i);

            int key = intArray[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && intArray[j] > key) {
                view.focusRect(j);
                setComparisonsCount(getComparisonsCount()+1);
                delay(timeDelay);
                intArray[j + 1] = intArray[j];
                view.swapRect(j+1,j);
                view.unfocusRect(j);
                j = j - 1;

            }
            intArray[j + 1] = key;
            view.setRectValue(j+1,key);
            view.unfocusRect(i);
        }
        setCurrentStatus(ArrayStatus.SORTED);

    }
}
