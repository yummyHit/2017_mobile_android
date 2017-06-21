package com.test.yummy.finalterm;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.test.yummy.finalterm.MainActivity.money_memo;
import static com.test.yummy.finalterm.MoneyFrag.month;
import static com.test.yummy.finalterm.MoneyFrag.year;

/**
 * Created by Yummy on 2017-06-16.
 */

public class MoneyManage extends Fragment {
  FragmentManager ftmanager;
  int position, date_year, date_month, index = 0;
  EditText xml_memo;
  TextView day_text;
  Button okBtn;
  String memo;

  public MoneyManage() {
    this.position = 0;
    this.date_year = 0;
    this.date_month = 0;
    this.memo = "";
  }
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.money_manage, container, false);
    xml_memo = (EditText) v.findViewById(R.id.money_memo);
    xml_memo.setHint(getMemo_init());   // 지출내역 초기화
    day_text = (TextView) v.findViewById(R.id.day_money_text);
    day_text.setText("" + date_year + " - " + date_month + " - " + (position + 1));   // 날짜를 받아와 보여줌
    okBtn = (Button) v.findViewById(R.id.money_okBtn);
    // 만약 지출내역이 있었으면 받아옴
    if(!money_memo.get(position).memo.equals("")) xml_memo.setText(money_memo.get(position).getMemo());
    okBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        money_memo.get(position).setMemo(xml_memo.getText().toString());
        ScheduleFrag scheduleFrag = new ScheduleFrag();
        scheduleFrag.setIndex(2);   // 캘린더에서 지출내역으로 인식하기 위한 인덱스
        year = Integer.toString(date_year);
        month = Integer.toString(date_month);
        ftmanager = getFragmentManager();
        ftmanager.beginTransaction().replace(R.id.fragment_appbar, scheduleFrag).commit();
      }
    });
    return v;
  }

  public void setIndex(int y, int m, int position) {
    this.date_year = y;
    this.date_month = m;
    this.position = position;
  }

  public String getMemo() { return memo; }

  public String setMemo(String memo) { return this.memo = memo; }

  public String getMemo_init() { return "지출된 내역을 입력하세요."; }
}
