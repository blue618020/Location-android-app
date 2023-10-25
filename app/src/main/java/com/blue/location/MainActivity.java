package com.blue.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 핸드폰의 위치를 가져오기 위해서, 시스템 서비스로부터 로케이션 매니저를 받아오기
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // 로케이션 리스너 만들기
        // 위치를 잡으면 동작함. 위치가 변해도 실시간으로 동작해서 잡아옴
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 여기에 위치 로직 작성

                // 위도 가져오기
//                location.getLatitude();

                // 경도 가져오기
//                location.getLongitude();

                Log.i("myLocation", "위도 : " + location.getLatitude());
                Log.i("myLocation", "경도 : " + location.getLongitude());
            }
        };

        // 위치 기반 서비스 권한 필요
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    100);
            return;
        }

        // 체크해서 위치기반 허용했다면, 로케이션 매니저에 리스너를 연결하기
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,
                -1,
                locationListener); // 3초마다 위치 찍기
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            // 위치기반 허용했다면, 한번 더 체크

            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        100);
                return;
            }

            // 체크해서 위치기반 허용했다면, 로케이션 매니저에 리스너를 연결하기
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    3000,
                    -1,
                    locationListener); // 3초마다 위치 찍기
        }
    }
}