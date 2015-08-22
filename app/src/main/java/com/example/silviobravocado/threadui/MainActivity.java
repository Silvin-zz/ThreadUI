package com.example.silviobravocado.threadui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {

    ProgressBar bar;

    android.os.Handler handler = new android.os.Handler(){

        public void handleMessage(){

            bar.incrementProgressBy(5);
            if(bar.getProgress() == 100){

                bar.setProgress(0);
                Toast.makeText(MainActivity.this, "La ejecucion ha terminado", Toast.LENGTH_SHORT).show();
            }
        }



    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();


        bar.setProgress(0);
        Thread background  = new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    for( int i = 0; i < 20; i++){
                        Thread.sleep(1000);
                        Toast.makeText(MainActivity.this, "Entramos", Toast.LENGTH_SHORT).show();
                        handler.sendMessage(handler.obtainMessage());
                    }


                }
                catch(Throwable t){

                }

            }
        });
        Toast.makeText(MainActivity.this, "Recorremos", Toast.LENGTH_SHORT).show();
        background.start();

    }



}
