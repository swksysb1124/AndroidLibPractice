package com.jasonstudio.jy.androidlibpractice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jasonstudio.jy.androidlib.http.remoteservice.ServiceDataReceiver;
import com.jasonstudio.jy.androidlibpractice.service.WeatherService;
import com.jasonstudio.jy.androidlibpractice.service.ap5630w.Ap5630wMobileAPIMock;

public class TestActivity extends AppCompatActivity {

    private WeatherService weatherService;
    private Ap5630wMobileAPIMock apiMock;

    private ServiceDataReceiver serviceDataReceiver = new ServiceDataReceiver() {
        @Override
        public void onSuccess(String key, String content) {
            update(content);
        }

        @Override
        public void onFail(String key, int errorType, String errorMessage) {
            error(errorMessage);
        }

        @Override
        public void onDisconnected(String key, String errorMessage) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TestActivity.this,
                            "Disconnected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onTimeout(String key, String errorMessage) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TestActivity.this,
                            "Timeout", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onUnknownHost(String key, String errorMessage) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(TestActivity.this,
                            "Unknown Host", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setupServices();
        setupInputListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finishWeatherService();
    }

    private void setupServices() {
        weatherService = new WeatherService(this);
        apiMock = new Ap5630wMobileAPIMock(this);
    }

    private void setupInputListener() {
        findViewById(R.id.btnGetWeatherInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherService.getWeatherInfo();
            }
        });

        findViewById(R.id.btnAnyUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherService.invoke(
                        "GET-USER_INFO",
                        "GET",
                        "https://api.github.com/users/octocat/repos",
                        null,
                        null);
            }
        });

        findViewById(R.id.btnGetMockData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiMock.getClientList();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherService.registerDataReceiver(serviceDataReceiver);
        apiMock.registerDataReceiver(serviceDataReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
        weatherService.unregisterDataReceiver();
        apiMock.unregisterDataReceiver();
    }

    public void update(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setTextColor(Color.parseColor("#00AA00"));
                if (result.startsWith("[cached]")) {
                    txtResult.setTextColor(Color.parseColor("#AAAA00"));
                }
                txtResult.setText(result);
            }
        });
    }

    public void error(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setTextColor(Color.parseColor("#AA0000"));
                txtResult.setText(error);
            }
        });
    }

    private void finishWeatherService() {
        if (weatherService != null) {
            weatherService.finish();
            weatherService = null;
        }
    }
}
