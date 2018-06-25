package com.github.mateusmiguel.cadastrei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // Declara o botão
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaPrimeiraTela();
            }
        });
    }
    void chamaPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FullscreenActivity.class);
        startActivity(intent);
        finish();
    }
}
