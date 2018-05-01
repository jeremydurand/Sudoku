package com.example.utilisateur.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GridChoiceActivity extends Activity {

    ListView listView;
    int random = 0;
    private int[] grille = new int[24] ;


    private String[] grid = new String[]{
            "Grille 1", "Grille 2", "Grille 3", "Grille 4", "Grille 5", "Grille 6",
            "Grille 7", "Grille 8", "Grille 9", "Grille 10", "Grille 11", "Grille 12",
            "Grille 13", "Grille 14", "Grille 15", "Grille 16", "Grille 17", "Grille 18",
            "Grille 19", "Grille 20", "Grille 21", "Grille 22", "Grille 23", "Grille 24"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_choice);

        ListView listView = this.findViewById(R.id.listViewGrid);

        int size = grid.length;

        int[] arr = new int[size];

        for(int k = 0; k < size; k++ ){
            random = new Random().nextInt(100);
            arr[k] = random;
            grid[k] = grid[k] + "\n" + arr[k] + "%";
            grille[k]=random;
        }



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GridChoiceActivity.this,
                android.R.layout.simple_list_item_1, grid){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)

                   // tv.setTextColor(Color.RED);
                if (grille[position]<50){
                    tv.setTextColor(Color.RED);
                } else {
                    tv.setTextColor(Color.GREEN);
                }

                // Generate ListView Item using TextView
                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(GridChoiceActivity.this, SudokuGridActivity.class);
                startActivity(intent);
            }
        });
    }
}
