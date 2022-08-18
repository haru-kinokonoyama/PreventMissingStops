package jp.ac.ritsumei.ise.phy.exp2.is0576pr.preventmissingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.*;

public class MainActivity extends Activity {

    private FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LocationClientクラスのインスタンス作成
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // 位置情報取得開始
        startUpdateLocation();

    }

    /**
     * 位置情報取得開始メソッド
     */
    private void startUpdateLocation() {
        // 位置情報取得権限の確認
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //権限がない場合、許可ダイアログ表示
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, 2000);
            return;
        }

        //位置情報の取得方法
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000); //位置情報の取得間隔
        locationRequest.setFastestInterval(5000); //位置情報をこれ以上早い間隔で取得しないようにする

        //どれくらい優先して位置情報を取得するか
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new MyLocationCallback(),null);
    }

    /**
     * 位置情報受取コールバッククラス
     */
    private class MyLocationCallback extends LocationCallback {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            if (locationResult == null) {
                return;
            }
            // 現在値を取得
            Location location = locationResult.getLastLocation();
            // 画面に表示
            TextView view = findViewById(R.id.text_view);
            view.setText("緯度:" + location.getLatitude() + " 経度:" + location.getLongitude());
        }
    }

    /**
     * 許可ダイアログの結果受取
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2000 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 位置情報取得開始
            startUpdateLocation();
        }
    }
}