/**
 * Copyright (c) 2016 The AnyRTC project authors. All Rights Reserved.
 * <p>
 * Please visit https://www.anyrtc.io for detail.
 * <p>
 * The GNU General Public License is a free, copyleft license for
 * software and other kinds of works.
 * <p>
 * The licenses for most software and other practical works are designed
 * to take away your freedom to share and change the works.  By contrast,
 * the GNU General Public License is intended to guarantee your freedom to
 * share and change all versions of a program--to make sure it remains free
 * software for all its users.  We, the Free Software Foundation, use the
 * GNU General Public License for most of our software; it applies also to
 * any other work released this way by its authors.  You can apply it to
 * your programs, too.
 * See the GNU LICENSE file for more info.
 */
package org.anyrtc.anyrtmp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.anyrtc.core.AnyRTMP;

import java.util.Locale;

public class TestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = TestActivity.class.getSimpleName();

    private Spinner spinnerSize;
    private Spinner spinnerRate;
    private Spinner spinnerAddress;
    private EditText etDevice;

    private String[] sizes;
    private String[] rates;
    private String[] addresses;

    private int currentWidth;
    private int currentHeight;
    private int currentRate;
    private String currentAddress;
    private String currentDevice;

    String rtmpUrl;

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AnyRTMP.Inst();

        initView();
    }

    public void OnBtnClicked(View view) {
        currentDevice = etDevice.getText().toString();
        if (TextUtils.isEmpty(currentDevice)) {
            etDevice.setError("设备名不能为空");
            return;
        }

        if (currentAddress.equals("外网")) {
            rtmpUrl = "rtmp://60.190.225.252:1935/myapp/" + currentDevice;
        } else {
            rtmpUrl = "rtmp://10.170.35.58:1935/myapp/" + currentDevice;
        }

        if (view.getId() == R.id.btn_start_live) {
            StringBuilder sb = new StringBuilder();
            sb.append("宽：" + currentWidth + "\n");
            sb.append("长：" + currentHeight + "\n");
            sb.append("帧率：" + currentRate + "\n");
            sb.append("地址：" + rtmpUrl);

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("信息确认")
                    .setMessage(sb.toString())
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(context, HosterActivity.class);
                            Bundle bd = new Bundle();
                            bd.putString("rtmp_url", rtmpUrl);

                            bd.putInt("width", currentWidth);
                            bd.putInt("height", currentHeight);
                            bd.putInt("rate", currentRate);

                            it.putExtras(bd);
                            startActivity(it);
                        }
                    })
                    .setNegativeButton("取消", null);

            builder.show();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("地址：" + rtmpUrl);

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("信息确认")
                    .setMessage(sb.toString())
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(context, GuestActivity.class);
                            Bundle bd = new Bundle();
                            bd.putString("rtmp_url", rtmpUrl);
                            it.putExtras(bd);
                            startActivity(it);
                        }
                    })
                    .setNegativeButton("取消", null);

            builder.show();
        }
    }

    private void initView() {
        spinnerSize = (Spinner) findViewById(R.id.spinner_size);
        spinnerRate = (Spinner) findViewById(R.id.spinner_rate);
        spinnerAddress = (Spinner) findViewById(R.id.spinner_address);
        etDevice = (EditText) findViewById(R.id.et_device);

        spinnerSize.setOnItemSelectedListener(this);
        spinnerRate.setOnItemSelectedListener(this);
        spinnerAddress.setOnItemSelectedListener(this);

        sizes = getResources().getStringArray(R.array.size);
        rates = getResources().getStringArray(R.array.rate);
        addresses = getResources().getStringArray(R.array.address);

        spinnerSize.setSelection(4);
        spinnerRate.setSelection(5);

        String[] ss = sizes[4].split("x");

        currentWidth = Integer.valueOf(ss[0]);
        currentHeight = Integer.valueOf(ss[1]);
        currentRate = Integer.valueOf(rates[5]);

        currentAddress = addresses[0];
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int i = parent.getId();
        if (i == R.id.spinner_size) {
            String[] ss = sizes[position].split("x");

            currentWidth = Integer.valueOf(ss[0]);
            currentHeight = Integer.valueOf(ss[1]);

        } else if (i == R.id.spinner_rate) {
            currentRate = Integer.valueOf(rates[position]);

        } else if (i == R.id.spinner_address) {
            currentAddress = addresses[position];

        }
        String str = String.format(Locale.CHINA, "width = %d  height = %d rate = %d address = %s", currentWidth, currentHeight, currentRate, currentAddress);
        Log.e(TAG, "onItemSelected: " + str);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
