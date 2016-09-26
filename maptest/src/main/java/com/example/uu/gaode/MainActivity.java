package com.example.uu.gaode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

   Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    Button button6;
    Button button7;
    Button button8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
        init();
    }
    /**
     * 初始化AMap对象
     */
    private void init() {
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        button5= (Button) findViewById(R.id.button5);
        button6= (Button) findViewById(R.id.button6);
        button7= (Button) findViewById(R.id.button7);
        button8= (Button) findViewById(R.id.button8);
       button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
       /*     case R.id.button1:
                intent=new Intent(MainActivity.this,ViewpagerTest.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button2:
                intent=new Intent(MainActivity.this,RouteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button3:
                intent=new Intent(MainActivity.this,LocationModeSourceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button4:
                intent=new Intent(MainActivity.this,ScreenShotActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button5:
                intent=new Intent(MainActivity.this,BuslineActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button6:
                intent=new Intent(MainActivity.this,MarkerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button7:
                intent=new Intent(MainActivity.this,BuslineActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;
            case R.id.button8:
                intent=new Intent(MainActivity.this,BuslineActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold, R.anim.fade);
                break;*/
        }
    }
}
