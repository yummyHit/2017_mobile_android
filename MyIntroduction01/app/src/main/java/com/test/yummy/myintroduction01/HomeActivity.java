package com.test.yummy.myintroduction01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }

  public void on_introBtnClicked(View v) {
    Intent myIntent = new Intent(getApplicationContext(), IntroActivity.class);
    startActivity(myIntent);
    finish();
  }
  public void on_projectBtnClicked(View v) {
    Intent myIntent = new Intent(getApplicationContext(), ProjectActivity.class);
    startActivity(myIntent);
    finish();
  }
  public void on_photosBtnClicked(View v) {
    Intent myIntent = new Intent(getApplicationContext(), PhotosActivity.class);
    startActivity(myIntent);
    finish();
  }
  public void on_homeBtnClicked(View v) {
    Intent myIntent = new Intent(HomeActivity.this, MainActivity.class);
    startActivity(myIntent);
    finish();
  }
}
