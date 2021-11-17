package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;

public class SelectionSort extends Sort{

    public SelectionSort(Context context) {
        super(context);
    }

    @Override
    public void sort(ArrayVisualizeView view, long timeDelay) {
        currentStatus = ArrayStatus.SORTING;


        int n = intArray.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            view.focusRect(i);
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                view.focusRect(j);
                if (intArray[j] < intArray[min_idx])
                    min_idx = j;
                setComparisonsCount(getComparisonsCount()+1);
                delay(timeDelay);

                view.unfocusRect(j);

            }

            // Swap the found minimum element with the first element
            int temp = intArray[min_idx];
            intArray[min_idx] = intArray[i];
            intArray[i] = temp;
            view.swapRect(min_idx,i);

            view.unfocusRect(i);

        }

        currentStatus = ArrayStatus.SORTED;

    }
}
