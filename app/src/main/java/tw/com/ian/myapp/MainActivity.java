package tw.com.ian.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;
    Timer timer;
    private  int intHSec;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        tv = findViewById(R.id.tv);
        timer = new Timer();
        timer.schedule(new MyTask(),0,10);
        intHSec = 0;
    }
    class MyTask extends TimerTask {

        @Override
        public void run() {
            intHSec++;
            tv.setText(toClock());
        }
    }
    private  String toClock(){
        int th = intHSec%100;
        int tsec = intHSec/100;
        int hh = tsec/(60*60);
        int mm = (tsec - hh*60*60)/60;
        int ss = tsec %60;

        return hh+":"+(mm<10?"0"+mm:mm)+":"+
                (ss<10?"0"+ss:ss) + "."+(th<10?"0"+th:th);
    }
}