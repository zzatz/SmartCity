package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    private TextView tvskip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvskip=findViewById(R.id.tvspik);
        tvskip.setOnClickListener(listener);
        countDownTimer.start();
    }
    View.OnClickListener listener =new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (countDownTimer != null)
            {
                countDownTimer.cancel();
                Intent intent1=new Intent(WelcomeActivity.this,SecondActivity.class);
                startActivity(intent1);
                finish();
            }
        }
    };
    CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {

        @Override
        public void onTick(long l) {
            tvskip.setText(getString(R.string.skip_wait,(l/1000)));
        }

        @Override
        public void onFinish() {
            tvskip.setText("正在跳转");
            Intent intent1=new Intent(WelcomeActivity.this,SecondActivity.class);
            startActivity(intent1);
            finish();
        }
    };

}
