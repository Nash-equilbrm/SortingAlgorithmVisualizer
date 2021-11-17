package com.example.my_algorithm_visualizer;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_algorithm_visualizer.Algorithm.AlgorithmFactory;
import com.example.my_algorithm_visualizer.Algorithm.ArrayStatus;
import com.example.my_algorithm_visualizer.Algorithm.BubbleSort;
import com.example.my_algorithm_visualizer.Algorithm.Sort;




public class MainActivity extends AppCompatActivity {

    private AlgorithmFactory algorithmFactory = new AlgorithmFactory(this);
    private Sort sortAlgorithm = new BubbleSort(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        TextView arraySizeDisplay = (TextView) findViewById(R.id.arraySizeDisplay);
        TextView comparisonCountDisplay = (TextView) findViewById(R.id.comparisonCountDisplay);
        Button shuffleButton = (Button) findViewById(R.id.shuffleButton);
        Button sortButton = (Button) findViewById(R.id.sortButton);
        ArrayVisualizeView arrayVisualizeView = (ArrayVisualizeView) findViewById(R.id.AlgoVisualView);



        //Delay time in visualization
        long timeDelay = 1; //  miliseconds

        // set text for current defaut array size
        arraySizeDisplay.setText(R.string.array_start_size);
        // Generate random integer array
        sortAlgorithm.randomArray(getResources().getInteger(R.integer.array_start_size));
        // state the default algorithm
        Toast.makeText(this,getResources().getString(R.string.Bubble_sort),Toast.LENGTH_SHORT).show();


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
                if(sortAlgorithm.getCurrentStatus()==ArrayStatus.SORTED
                        || sortAlgorithm.getCurrentStatus()==ArrayStatus.NOT_SORTED) {
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

                    if(sortAlgorithm.getCurrentStatus()== ArrayStatus.SORTED) {
                        handler.removeCallbacks(updateUIRunnable);
                    }
                }



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(getResources().getString(R.string.Selection_sort));
        menu.add(getResources().getString(R.string.Bubble_sort));
        menu.add(getResources().getString(R.string.Cocktail_sort));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Sort newAlgorithm = algorithmFactory.newSort(item.getTitle().toString());

        newAlgorithm.setCurrentStatus(sortAlgorithm.getCurrentStatus());
        newAlgorithm.setArr(sortAlgorithm.getArr());
        newAlgorithm.setComparisonsCount(sortAlgorithm.getComparisonsCount());

        sortAlgorithm = newAlgorithm;
        Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}