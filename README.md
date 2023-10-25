# 내 실시간 위치를 찾는 앱

📝 <b> tistory : </b> https://blue618020.tistory.com/120

### 🔍 학습 내용
-  내 핸드폰 위치를 파악해서 위도, 경도값을 자동으로 가져오기
-  위치 기반 서비스 허용하기

### 💻 실습
-  핸드폰의 위치를 가져오기 위해서, 시스템 서비스로부터 <b> locationManager </b>를 받아오기

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

-  위치를 잡으면 동작하는 <b> locationListener </b>를 만들기
-  위치가 변해도 실시간으로 동작해서 위치를 잡아오는걸 확인하기 위해 <b> Log </b>를 출력해봄

         locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 여기에 위치 로직 작성
                Log.i("myLocation", "위도 : " + location.getLatitude());
                Log.i("myLocation", "경도 : " + location.getLongitude());
             }
          };

-  위치 기반 서비스 권한을 요청하는 코드
-  허용하면 <b> requestCode </b>가 100이 됨 

          if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return;
        }

        // 체크해서 위치기반 허용했다면, 로케이션 매니저에 리스너를 연결하기
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, -1,
                locationListener); // 3초마다 위치 찍기

-  위치기반을 허용했는지 한번 더 체크하기 (requestCode == 100)
-  Logcat을 확인하면 3초에 한번씩 위도와 경도 정보가 뜸
  
