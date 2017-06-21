package com.test.yummy.finalterm;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import static com.test.yummy.finalterm.MainActivity.curMonthFormat;
import static com.test.yummy.finalterm.MainActivity.curYearFormat;
import static com.test.yummy.finalterm.MainActivity.date;
import static com.test.yummy.finalterm.MainActivity.money_memo;
import static com.test.yummy.finalterm.MainActivity.schedule_memo;
import static com.test.yummy.finalterm.MoneyFrag.month;
import static com.test.yummy.finalterm.MoneyFrag.year;

/**
 * Created by Yummy on 2017-05-23.
 */

public class ScheduleFrag extends Fragment {
  // 연/월 텍스트뷰
  private TextView tvDate;
  // 그리드뷰 어댑터
  private ScheduleAdapter scheduleAdapter;
  // 일 저장 할 리스트
  public ArrayList<String> dayList;
  // 그리드뷰
  private GridView gridView;
  // 캘린더 변수
  static public Calendar mCal;
  Button prevBtn, nextBtn;
  int thisMonth, thisYear, dayNum, index = 0;
//  int next_index = 0, prev_index = 0;
  FragmentManager ftmanager;
  ScheduleManage scheduleManage;
  MoneyManage moneyManage;
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.schedule_layout, container, false);
    setHasOptionsMenu(true);
    tvDate = (TextView) v.findViewById(R.id.tv_date);
    gridView = (GridView) v.findViewById(R.id.gridview);
    prevBtn = (Button) v.findViewById(R.id.prevBtn);
    nextBtn = (Button) v.findViewById(R.id.nextBtn);
    // 오늘에 날짜를 세팅 해준다.
    //연,월,일을 따로 저장
    //현재 날짜 텍스트뷰에 뿌려줌
    if(year.equals("") && month.equals("")) {
      thisMonth = Integer.parseInt(curMonthFormat.format(date));
      thisYear = Integer.parseInt(curYearFormat.format(date));
    } else {
      thisMonth = Integer.parseInt(month);
      thisYear = Integer.parseInt(year);
      year = ""; month = "";
    }
    tvDate.setText(thisYear + "년 " + thisMonth + "월");
    //gridview 요일 표시
    dayList = new ArrayList<String>();
    dayday();
    setCalendarDate(mCal.get(Calendar.MONTH) + 1);
    scheduleAdapter = new ScheduleAdapter(getActivity(), dayList);
    if(thisMonth == Integer.parseInt(curMonthFormat.format(date)) && thisYear == Integer.parseInt(curYearFormat.format(date))) scheduleAdapter.getIndex(0);
    else scheduleAdapter.getIndex(-1);
    ftmanager = getFragmentManager();
    gridView.setAdapter(scheduleAdapter);
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (index == 1) {
          if (position < 6 + dayNum)
            Toast.makeText(getActivity(), "잘못된 날짜 입니다!", Toast.LENGTH_SHORT).show();
          else {
//          if(index > 0) scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, next_index - prev_index);
//          else if(index < 0) scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, prev_index - next_index);
//          else scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, index);
            scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum);
            ftmanager.beginTransaction().replace(R.id.fragment_appbar, scheduleManage).commit();
          }
        }
        else if (index == 2) {
          if (position < 6 + dayNum)
            Toast.makeText(getActivity(), "잘못된 날짜 입니다!", Toast.LENGTH_SHORT).show();
          else {
//          if(index > 0) scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, next_index - prev_index);
//          else if(index < 0) scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, prev_index - next_index);
//          else scheduleManage.setIndex(thisYear, thisMonth, position - 6 - dayNum, index);
            moneyManage.setIndex(thisYear, thisMonth, position - 6 - dayNum);
            ftmanager.beginTransaction().replace(R.id.fragment_appbar, moneyManage).commit();
          }
        }
      }//리스트뷰 아이템을 누르 경우
    });
    prevBtn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
            if(thisMonth > 1) {
              thisMonth--;
            } else {
              thisYear--;
              thisMonth = 12;
            }
//        prev_index += mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        index--;
        dayday();
        setCalendarDate(thisMonth);
        if(thisMonth != Integer.parseInt(curMonthFormat.format(date)) || thisYear != Integer.parseInt(curYearFormat.format(date))) scheduleAdapter.getIndex(1);
        else scheduleAdapter.getIndex(0);
        tvDate.setText(thisYear + "년 " + thisMonth + "월");
        scheduleAdapter.notifyDataSetChanged();
      }
    });
    nextBtn.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        if(thisMonth < 12) {
          thisMonth++;
        } else {
          thisYear++;
          thisMonth = 1;
        }
//        next_index += mCal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        index++;
        dayday();
        setCalendarDate(thisMonth);
        if(thisMonth != Integer.parseInt(curMonthFormat.format(date)) || thisYear != Integer.parseInt(curYearFormat.format(date))) scheduleAdapter.getIndex(2);
        else scheduleAdapter.getIndex(0);
        tvDate.setText(thisYear + "년 " + thisMonth + "월");
        scheduleAdapter.notifyDataSetChanged();
      }
    });
    return v;
  }

  private void dayday() {
    dayList.clear();
    dayList.add("일");
    dayList.add("월");
    dayList.add("화");
    dayList.add("수");
    dayList.add("목");
    dayList.add("금");
    dayList.add("토");
    mCal = Calendar.getInstance();
    //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
    mCal.set(thisYear, thisMonth - 1, 1);

    dayNum = mCal.get(Calendar.DAY_OF_WEEK);
    //1일 - 요일 매칭 시키기 위해 공백 add
    for (int i = 1; i < dayNum; i++) { dayList.add(""); }
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    getActivity().getMenuInflater().inflate(R.menu.schedule_setting, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.schedule_add) return true;
    else if (id == R.id.schedule_del) return true;
    else if (id == R.id.schedule_settings) return true;

    return super.onOptionsItemSelected(item);
  }
  // 캘린더 기능인지 가계부 기능인지 구별을 위한 인덱스 함수
  public void setIndex(int index) {
    this.index = index;
  }

  private void setCalendarDate(int month) {
    mCal.set(Calendar.MONTH, month - 1);
    for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
      dayList.add("" + (i + 1));
      scheduleManage = new ScheduleManage();
      schedule_memo.add(scheduleManage);
      moneyManage = new MoneyManage();
      money_memo.add(moneyManage);
    }
  }
}

