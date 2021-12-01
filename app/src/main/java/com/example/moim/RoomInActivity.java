package com.example.moim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

public class RoomInActivity extends AppCompatActivity implements OnMapReadyCallback {

    int i=0;
    int count=0;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;

    //private NaverMap.setLocationSource(LocationSource);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_in);

        //버튼 구현부
        TextView back = (TextView) findViewById(R.id.btn_backroom);
        ImageButton btn_imoti = (ImageButton) findViewById(R.id.btn_imoticon);
        ImageButton btn_gps = (ImageButton) findViewById(R.id.btn_gps);
        ImageButton btn_invite = (ImageButton) findViewById(R.id.btn_invite);


        //실시간 위치 확인
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        //위치설정
        LocationManager locationManager;
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

        //getMapAsync를 호출하여 비동기로 onMapReady 콜백 매서드 호출(네이버 플렛폼 참고)
        if(mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        //나가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        //imoticon 버튼클릭
        btn_imoti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                i= i++;
                count = i%3;
//                if(count == 0){
//                    locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot01));
//                }
//                else if (count ==2){
//                    locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot02));
//                }
//                else{
//                    locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot03));
//                }
                Toast.makeText(getApplicationContext(),"!!!!imoticon 구현예정!!!!", Toast.LENGTH_SHORT).show();
            }
        });

        //gps 버튼클릭
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 현제 gps가 off상태이면 gps on을 위해 설정으로 이동
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    //GPS 설정화면으로 이동
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                } else {
                    //GPS on일경우 toast
                    //Toast.makeText(getApplicationContext(), "GPS가 이미 활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
                    //GPS가 on일경우 알림메세지 출력
                    AlertDialog.Builder builder = new AlertDialog.Builder(RoomInActivity.this);
                    builder.setMessage("GPS가 ON상태 입니다 지도 좌측 하단의 버튼을 눌러 현위치를 표시할 수 있습니다.");
                    builder.setPositiveButton("확인",null);
                    builder.create().show();
                }
            }
        });

        //intvite 버튼클릭
        btn_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"!!!!invite 구현예정!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //현재 위치 사용권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) { //요청 허용if문
            if(!locationSource.isActivated()){ //요청이 거부된 경우
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap=naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.getUiSettings().setLocationButtonEnabled(true);
        naverMap.setLocationTrackingMode(LocationTrackingMode.None);
        //현위치 아이콘 커스텀을 위한 설정
        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
        if(count == 0){
            locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot01));
        }
        else if (count ==1){
            locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot02));
        }
        else{
            locationOverlay.setIcon(OverlayImage.fromResource(R.drawable.ic_baseline_imot03));
        }


    }
}
