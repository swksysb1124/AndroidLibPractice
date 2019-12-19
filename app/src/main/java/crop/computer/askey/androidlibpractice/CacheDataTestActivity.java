package crop.computer.askey.androidlibpractice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import crop.computer.askey.androidlib.http.remoteservice.ServiceDataReceiver;
import crop.computer.askey.androidlibpractice.service.WeatherService;

public class CacheDataTestActivity extends AppCompatActivity {

    private WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_data_test);

        setupWeatherService();
        setupInputListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        finishWeatherService();
    }

    private void setupWeatherService() {
        weatherService = new WeatherService(this);
        weatherService.registerDataReceiver(new ServiceDataReceiver() {
            @Override
            public void onSuccess(String key, String content) {
                update(content);
            }

            @Override
            public void onFail(String key, int errorType, String errorMessage) {
                error(errorMessage);
            }
        });
    }

    private void setupInputListener() {
        findViewById(R.id.btnGetWeatherInfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherService.getWeatherInfo();
            }
        });
    }

    public void update(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView txtResult = findViewById(R.id.txtResult);
                txtResult.setTextColor(Color.parseColor("#00AA00"));
                if(result.startsWith("[cached]")) {
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
        if(weatherService != null) {
            weatherService.finish();
            weatherService = null;
        }
    }
}
