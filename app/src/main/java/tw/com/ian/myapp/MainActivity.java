package tw.com.ian.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Button bt1,bt2;
    Timer timer;
    private  int intHSec;
    TextView tv;
    ListView lv;
    SimpleAdapter adapter;
    boolean isStart;
    List<HashMap<String, String>> data;
    String[] from = {"lap"};
    int [] to ={R.id.tv_time};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        tv = findViewById(R.id.tv);
        lv = findViewById(R.id.lv);
        data = new ArrayList<>();
        isStart = false;
        intHSec = 0;
    }

    public void lap(View view) {



        HashMap<String, String> h = new HashMap<>();
        h.put(from[0],toClock());
        data.add(h);
        Log.v("MainActivity",data.toString());
        adapter = new SimpleAdapter(this,data,R.layout.items,from,to);
        lv.setAdapter(adapter);
    }

    public void start(View view) {
        if (isStart==false){
            timer = new Timer();
            timer.schedule(new MyTask(),0,10);
            bt2.setText("STOP");
        }else{
            bt2.setText("START");
            timer.purge();
            timer.cancel();
        }

        isStart = !isStart;
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