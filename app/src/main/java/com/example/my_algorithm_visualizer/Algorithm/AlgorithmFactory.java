package com.example.my_algorithm_visualizer.Algorithm;

import android.content.Context;

import com.example.my_algorithm_visualizer.R;

public class AlgorithmFactory {

    private Context context;
    public AlgorithmFactory(Context context){
        this.context = context;
    }

    public Sort newSort(String algo){
        Sort sort = null;
        if(algo == context.getResources().getString(R.string.Bubble_sort))
            sort = new BubbleSort(context);
        else if (algo == context.getResources().getString(R.string.Selection_sort))
            sort = new SelectionSort(context);
        else if (algo == context.getResources().getString(R.string.Cocktail_sort))
            sort = new CocktailSort(context);
        else if (algo == context.getResources().getString(R.string.Heap_sort)){
            sort = new HeapSort(context);
        }
        return sort;
    }
}
