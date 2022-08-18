package jp.ac.ritsumei.ise.phy.exp2.is0576pr.preventmissingstopsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //立ち上げた際に最初に呼ばれるメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_main.xml(レイアウトファイル)を表示
        setContentView(R.layout.activity_main);

        //start buttonが押されたときの処理
        findViewById(R.id.start_button).setOnClickListener(new View.OnClickListener() {
            //画面遷移
            @Override
            public void onClick(View view) {
                //MapActivityへ
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    

}