package com.test.yummy.myintroduction01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button exitBtn = (Button) findViewById(R.id.exitBtn);
    exitBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("정말로 종료하시겠습니까?");
        builder.setTitle("종료 알림창")
          .setCancelable(false)
          .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
              finish();
            }
          })
          .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
              dialog.cancel();
            }
          });
        AlertDialog alert = builder.create();
        alert.setTitle("종료 알림창");
        alert.show();
      }
    });
  }

  public void on_enterBtnClicked(View v) {
    Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(myIntent);
    finish();
  }

}
