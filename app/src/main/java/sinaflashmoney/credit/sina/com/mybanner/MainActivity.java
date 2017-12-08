package sinaflashmoney.credit.sina.com.mybanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import sinaflashmoney.credit.sina.com.mylibrary.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(MainActivity.this,"你好");
            }
        });


    }
}
