package com.test.yummy.myintroduction01;

  import android.content.Intent;
  import android.graphics.Color;
  import android.os.Bundle;
  import android.support.v7.app.AppCompatActivity;
  import android.view.View;
  import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {
  TextView introText;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro);

    introText = (TextView) findViewById(R.id.introText);

    introText.setText("이름 : 장욱진\n나이 : 26세\n" +
      "거주지 : 경기도 안양시\n학교 : 가천대학교\n전공 : 컴퓨터공학과\n학번 : 201233425\n취미 : 술, 노래방, 당구, 보안 공부\n" +
      "경력\n - 융합보안관 1기\n - 프로젝트 6개 수행\n - 네트워크 보안 및 시스템 보안 진행중\n - 모의해킹 툴 제작중\n - 코드게이트 동영상 공모전 입상\n" +
      "자격증 : 리눅스마스터 1급\n사용가능 언어\n - Socket Programming using C\n - Kernel Programming using C\n - Python, Node.js, Angular.js\n" +
      " - Qt Programming\n - Reversing(Assembler)");
    introText.setTextSize(20);
    introText.setTextColor(Color.BLUE);
  }

  public void on_returnBtnClicked(View v) {
    Intent myIntent = new Intent(IntroActivity.this, HomeActivity.class);
    startActivity(myIntent);
    finish();
  }
}
