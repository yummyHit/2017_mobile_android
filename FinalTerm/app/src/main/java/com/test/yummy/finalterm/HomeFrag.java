package com.test.yummy.finalterm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.test.yummy.finalterm.MainActivity.curDayFormat;
import static com.test.yummy.finalterm.MainActivity.curMonthFormat;
import static com.test.yummy.finalterm.MainActivity.curYearFormat;
import static com.test.yummy.finalterm.MainActivity.date;
import static com.test.yummy.finalterm.MainActivity.money_memo;
import static com.test.yummy.finalterm.MainActivity.schedule_memo;

/**
 * Created by Yummy on 2017-05-23.
 */
// 기본적인 홈 화면 프래그먼트
public class HomeFrag extends Fragment {
  TextView home_date, today_schedule, today_money;
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.home_layout, container, false);
    setHasOptionsMenu(true);
    home_date = (TextView) v.findViewById(R.id.home_date);
    today_schedule = (TextView) v.findViewById(R.id.today_schedule);
    today_money = (TextView) v.findViewById(R.id.today_money);
    home_date.setText("Today\n" + curYearFormat.format(date) + "년 " + curMonthFormat.format(date) + "월 " + curDayFormat.format(date) + "일");
    // 만약 오늘 일정이 추가되어있으면 if문 실행
    if(schedule_memo.size() != 0) today_schedule.setText("오늘 일정\n" + schedule_memo.get(Integer.parseInt(curDayFormat.format(date)) - 1).getMemo());
    else  today_schedule.setText("오늘 일정\n" + "아직 일정이 없습니다.");
    // 만약 오늘 지출내역이 추가되어있으면 if문 실행
    if(money_memo.size() != 0 ) today_money.setText("오늘 지출\n" + money_memo.get(Integer.parseInt(curDayFormat.format(date)) - 1).getMemo());
    else today_money.setText("오늘 지출\n" + "아직 지출이 없습니다.");
    return v;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    getActivity().getMenuInflater().inflate(R.menu.home_settings, menu);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) return true;

    return super.onOptionsItemSelected(item);
  }
}
