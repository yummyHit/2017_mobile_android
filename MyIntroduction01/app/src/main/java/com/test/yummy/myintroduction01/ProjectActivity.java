package com.test.yummy.myintroduction01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ProjectActivity extends AppCompatActivity {
  Button pcapBtn, tcpBtn, arpspoofBtn, srv2clntBtn, itbankBtn, atmBtn;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_project);

    pcapBtn = (Button) findViewById(R.id.pcapBtn);
    tcpBtn = (Button) findViewById(R.id.tcpBtn);
    arpspoofBtn = (Button) findViewById(R.id.arpspoofBtn);
    srv2clntBtn = (Button) findViewById(R.id.srv2clntBtn);
    itbankBtn = (Button) findViewById(R.id.itbankBtn);
    atmBtn = (Button) findViewById(R.id.atmBtn);

    pcapBtn.setOnClickListener(onClickListener);
    tcpBtn.setOnClickListener(onClickListener);
    arpspoofBtn.setOnClickListener(onClickListener);
    srv2clntBtn.setOnClickListener(onClickListener);
    itbankBtn.setOnClickListener(onClickListener);
    atmBtn.setOnClickListener(onClickListener);
  }
  View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent intent;
      if(v.getId()==R.id.pcapBtn) intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/pcap_test"));
      else if(v.getId()==R.id.tcpBtn) intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/tcp_test"));
      else if(v.getId()==R.id.arpspoofBtn) intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/arpspoof_test"));
      else if(v.getId()==R.id.srv2clntBtn) intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/server2client"));
      else if(v.getId()==R.id.itbankBtn) intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/itbank_data"));
      else intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/yummyHit/2016_winter_ATM"));

      startActivity(intent);
    }
  };

  public void on_backBtnClicked(View v) {
    Intent myIntent = new Intent(ProjectActivity.this, HomeActivity.class);
    startActivity(myIntent);
    finish();
  }
}
