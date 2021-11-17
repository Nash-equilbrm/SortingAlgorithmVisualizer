package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;

public class CocktailSort extends Sort{

    public CocktailSort(Context context) {
        super(context);
    }

    @Override
    public void sort(ArrayVisualizeView view, long timeDelay) {
        setCurrentStatus(ArrayStatus.SORTING);

        boolean swapped = true;
        int start = 0;
        int end = intArray.length;

        while (swapped == true)
        {
            // reset the swapped flag on entering the
            // loop, because it might be true from intArray
            // previous iteration.
            swapped = false;

            // loop from bottom to top same as
            // the bubble sort
            for (int i = start; i < end - 1; ++i)
            {
                view.focusRect(i);
                if (intArray[i] > intArray[i + 1]) {
                    int temp = intArray[i];
                    intArray[i] = intArray[i + 1];
                    intArray[i + 1] = temp;

                    view.swapRect(i,i+1);

                    swapped = true;
                }
                setComparisonsCount(getComparisonsCount()+1);
                delay(timeDelay);
                view.unfocusRect(i);
            }

            // if nothing moved, then array is sorted.
            if (swapped == false)
                break;

            // otherwise, reset the swapped flag so that it
            // can be used in the next stage
            swapped = false;

            // move the end point back by one, because
            // item at the end is in its rightful spot
            end = end - 1;

            // from top to bottom, doing the
            // same comparison as in the previous stage
            for (int i = end - 1; i >= start; i--)
            {
                view.focusRect(i);
                if (intArray[i] > intArray[i + 1])
                {
                    int temp = intArray[i];
                    intArray[i] = intArray[i + 1];
                    intArray[i + 1] = temp;

                    view.swapRect(i,i+1);
                    swapped = true;
                }
                setComparisonsCount(getComparisonsCount()+1);
                delay(timeDelay);
                view.unfocusRect(i);
            }

            // increase the starting point, because
            // the last stage would have moved the next
            // smallest number to its rightful spot.
            start = start + 1;
        }
        setCurrentStatus(ArrayStatus.SORTED);

    }
}
