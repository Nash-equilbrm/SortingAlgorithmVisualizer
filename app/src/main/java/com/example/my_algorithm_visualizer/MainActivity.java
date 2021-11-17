package com.example.my_algorithm_visualizer;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.my_algorithm_visualizer.Algorithm.ArrayStatus;
import com.example.my_algorithm_visualizer.Algorithm.BubbleSort;
import com.example.my_algorithm_visualizer.Algorithm.SelectionSort;
import com.example.my_algorithm_visualizer.Algorithm.Sort;




public class MainActivity extends AppCompatActivity {

    private long timeDelay = 5; //  miliseconds
    private Sort sortAlgorithm = new SelectionSort(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        TextView arraySizeDisplay = (TextView) findViewById(R.id.arraySizeDisplay);
        TextView comparisonCountDisplay = (TextView) findViewById(R.id.comparisonCountDisplay);
        Button shuffleButton = (Button) findViewById(R.id.shuffleButton);
        Button sortButton = (Button) findViewById(R.id.sortButton);


        ArrayVisualizeView arrayVisualizeView = (ArrayVisualizeView) findViewById(R.id.AlgoVisualView);




        arraySizeDisplay.setText(R.string.array_start_size);

        //get array current size value
        int currentSize = getResources().getInteger(R.integer.array_start_size);

        // Generate random integer array
        sortAlgorithm.randomArray(currentSize);


        // visualize array:
        arrayVisualizeView.visualize(sortAlgorithm.getArr());

        // set listener on seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                arraySizeDisplay.setText(String.valueOf(i));
                sortAlgorithm.randomArray(i);
                arrayVisualizeView.visualize(sortAlgorithm.getArr());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });


        // set listener on shuffle button: Make new array when pressed
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sortAlgorithm.getCurrentStatus()==ArrayStatus.SORTED || sortAlgorithm.getCurrentStatus()==ArrayStatus.NOT_SORTED) {
                    comparisonCountDisplay.setText("");
                    sortAlgorithm.randomArray(seekBar.getProgress());
                    sortAlgorithm.setCurrentStatus(ArrayStatus.NOT_SORTED);
                    sortAlgorithm.setComparisonsCount(0);
                    arrayVisualizeView.shuffleRects(sortAlgorithm.getArr());
                    arrayVisualizeView.visualize(sortAlgorithm.getArr());
                }

            }
        });


        Handler handler = new Handler();
        Runnable updateUIRunnable = new Runnable() {
            @Override
            public void run() {
                arrayVisualizeView.visualize(sortAlgorithm.getArr());
                comparisonCountDisplay.setText(String.valueOf(sortAlgorithm.getComparisonsCount()));
                handler.postDelayed(this,timeDelay);

            }
        };

        // set listener on sort button: sort the array and visualize
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if sorted or sorting => dont run runnable

                if(sortAlgorithm.getCurrentStatus()== ArrayStatus.NOT_SORTED ) {


                    Runnable sortingRunnable = new Runnable() {
                        @Override
                        public void run() {
                            sortAlgorithm.sort(arrayVisualizeView,timeDelay);

                        }

                    };
                    Thread thread = new Thread(sortingRunnable);
                    thread.start();


                    updateUIRunnable.run();


                }



            }
        });
    }










}