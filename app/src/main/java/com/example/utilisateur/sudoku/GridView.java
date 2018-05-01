package com.example.utilisateur.sudoku;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GridView extends View implements View.OnTouchListener {
    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
    }

    private int number = 0;
    private String reference = "001700509573024106800501002700295018009400305652800007465080071000159004908007053";
    private StringBuilder filling = new StringBuilder(reference);

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int square = screenWidth / 9;

        //Draw grid
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * square, screenWidth, i * square, paint);
            // canvas.drawLine(0, i * square + 1, screenWidth, i * square + 1, paint);

            canvas.drawLine(i * square, 0, i * square, screenWidth, paint);
            // canvas.drawLine(i * square + 1, 0, i * square + 1, screenWidth, paint);

        }
        canvas.drawLine(0, 9 * square + 1, screenWidth, 9 * square + 1, paint);

        //filling grid
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (filling.charAt(x + 9 * y) != '0') {
                    canvas.drawText("" + filling.charAt(x + 9 * y), x * 120 + 40, (y + 1) * 116, paint);
                }
            }
        }

        for (int j = 0; j < 9; j++) {
            paint.setTextSize(100);
            canvas.drawText("" + (j + 1), (square) * j + 20, (square) * 10, paint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x  = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int square = screenWidth / 9;

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                number = (x / square) +1;;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(y<1100){
                    if (number != 0){
                        int coord_x = x/square;
                        int coord_y = y/square;
                        char value = Integer.toString(number).charAt(0);
                        int coeff = coord_x + 9 * coord_y;
                        // Log.e("TAG", "coeff "+coeff +" cx " + coord_x+" cy " + coord_y);
                        //filling.setCharAt(coeff, value);
                        if(reference.charAt(coeff) == '0'){
                            filling.setCharAt(coeff, value);
                        }else{
                            Log.e("TAG", "IMPOSSIBLE DE CHANGER CETTE VALEUR");
                        }
                    }
                    number = 0;
                }
        }

        this.invalidate();
        return true;
    }
}