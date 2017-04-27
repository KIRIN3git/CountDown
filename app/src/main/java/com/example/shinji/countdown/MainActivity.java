package com.example.shinji.countdown;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CountDown countDown;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.timer);
        text.setText("00:00.000");

        setStartBtn();
        setCancelBtn();
    }


    class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //　タイマー満了時に呼ばれる
        @Override
        public void onFinish() {
            // 完了
            text.setText("00:00.000");
        }

        // インターバルで呼ばれる
        @Override
        public void onTick(long millisUntilFinished) {
            // 残り時間を分、秒、ミリ秒に分割
            long mm = millisUntilFinished / 1000 / 60;
            long ss = millisUntilFinished / 1000 % 60;
            long ms = millisUntilFinished - ss * 1000 - mm * 1000 * 60;

            text.setText(String.format("%1$02d:%2$02d.%3$03d", mm, ss, ms));
        }
    }

    private void setStartBtn() {
        Button btn = (Button)findViewById(R.id.startButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // インスタンス生成
                // CountDownTimer(long millisInFuture, long countDownInterval)
                // 3分= 3x60x1000 = 180000 msec
                countDown = new CountDown(180000, 100);
                countDown.start();
            }
        });
    }
    private void setCancelBtn() {
        Button btn = (Button)findViewById(R.id.stopButton);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                countDown.cancel();
            }
        });
    }
}
