package com.example.rindou11.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button changeText;
    private TextView textView;

    //UPDATE_TEXT表示更新的动作
    private static final int UPDATE_TEXT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeText=(Button)findViewById(R.id.change_text);
        textView=(TextView)findViewById(R.id.text);

        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个子线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    private Handler handler=new Handler(){
        //新增一个handler对象,并在下面重写handleMessage()这个父类中的方法
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("OK!");
                    changeText.setText("OK!");
                    break;
                default:
                    break;
            }
        }
    };
}
