package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.ArrayVisualizeView;

public class HeapSort extends Sort{
    public HeapSort(Context context) {
        super(context);
    }

    @Override
    public void sort(ArrayVisualizeView view, long timeDelay) {
        int n = intArray.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--){
            view.focusRect(i);
            heapify( view,n, i);
            delay(timeDelay);
            view.unfocusRect(i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            view.focusRect(i);
            // Move current root to end
            int temp = intArray[0];
            intArray[0] = intArray[i];
            intArray[i] = temp;

            view.swapRect(0,i);

            // call max heapify on the reduced heap
            heapify( view,i, 0);

            delay(timeDelay);
            view.unfocusRect(i);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. heapSize is size of heap
    void heapify(ArrayVisualizeView view, int heapSize, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < heapSize && intArray[l] > intArray[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < heapSize && intArray[r] > intArray[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = intArray[i];
            intArray[i] = intArray[largest];
            intArray[largest] = swap;

            view.swapRect(i,largest);

            // Recursively heapify the affected sub-tree
            heapify(view, heapSize, largest);
        }
    }}
