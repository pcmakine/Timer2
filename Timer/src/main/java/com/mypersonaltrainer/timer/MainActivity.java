package com.mypersonaltrainer.timer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private int number;
    private UIUpdater mUIUpdater;

    /**
     * Called when the user clicks the Send button
     *
     * @param view
     */

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void addOne(View view) {
        final EditText numbers = (EditText) findViewById(R.id.running_numbers);
        CharSequence numero =  numbers.getText();
        String num = numero.toString();
        if(num.equals("")){
            number = 0;
        }
        else{
            number = Integer.parseInt(num);
        }

        mUIUpdater = new UIUpdater(new Runnable(){
            @Override
            public void run(){
                numbers.setText(Integer.toString(number));
                number--;
                if(number == 0){
                    mUIUpdater.stopUpdates();
                }
            }
        });

        mUIUpdater.startUpdates();





/*        for (int seconds = number ; seconds >= 0; seconds--)
        {
            try {
                Thread.sleep(1000);
                numbers.setText(String.format("%d:%02d", 0, seconds));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                break;
            }

        }*/
    }

    public void setNumber(int num){
        number = num;
    }

    public int getNumber(){
        return number;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // For the main activity, make sure the app icon in the action bar
            // does not behave as a button
            ActionBar actionBar = getActionBar();
            actionBar.setHomeButtonEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

