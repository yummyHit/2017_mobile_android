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
import android.widget.ExpandableListView;

import static com.test.yummy.finalterm.MainActivity.money_static_list;
import static com.test.yummy.finalterm.MoneyListClass.mChildListContent;
import static com.test.yummy.finalterm.MoneyListClass.mGroupList;

/**
 * Created by Yummy on 2017-05-23.
 */

public class MoneyFrag extends Fragment {
  MoneyAdapter moneyAdapter;
  ExpandableListView money_list;
  FragmentManager ftmanager;
  String year_tmp, month_tmp;
  static String year = "", month = "";    // 클릭한 연도와 월을 받아 캘린더로 뿌려주기 위한 static 선언
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.money_layout, container, false);
    setHasOptionsMenu(true);
    money_list = (ExpandableListView) v.findViewById(R.id.money_list);
    moneyAdapter = new MoneyAdapter(getActivity(), money_static_list.getGroupList(), money_static_list.getChildList());//어댑터 선언

    money_list.setAdapter(moneyAdapter);
    // 그룹 클릭 했을 경우 이벤트
    money_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
      @Override
      public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return false;
      }
    });

    // 차일드 클릭 했을 경우 이벤트
    money_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
      @Override
      public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        year_tmp = mGroupList.get(groupPosition);
        month_tmp = mChildListContent.get(childPosition);
        for(int i = 0; i < year_tmp.length(); i++) if(year_tmp.charAt(i) != '년') year += year_tmp.charAt(i);
        for(int i = 0; i < month_tmp.length(); i++) if(month_tmp.charAt(i) != '월') month += month_tmp.charAt(i);
        ScheduleFrag scheduleFrag = new ScheduleFrag();
        scheduleFrag.setIndex(2);
        ftmanager = getFragmentManager();
        ftmanager.beginTransaction().replace(R.id.fragment_appbar, scheduleFrag).commit();
        return false;
      }
    });

    // 그룹이 닫힐 경우 이벤트
    money_list.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
      @Override
      public void onGroupCollapse(int groupPosition) {
      }
    });

    // 그룹이 열릴 경우 이벤트
    money_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
      @Override
      public void onGroupExpand(int groupPosition) {
      }
    });
    return v;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    getActivity().getMenuInflater().inflate(R.menu.money_setting, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.money_add) return true;
    else if (id == R.id.money_del) return true;
    else if (id == R.id.money_settings) return true;

    return super.onOptionsItemSelected(item);
  }
}
