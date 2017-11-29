package me.xujichang.rtmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.anyrtc.anyrtmp.TestActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.sample_text);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}
