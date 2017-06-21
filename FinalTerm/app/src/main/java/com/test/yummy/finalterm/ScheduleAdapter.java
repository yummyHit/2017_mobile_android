package com.test.yummy.finalterm;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import static com.test.yummy.finalterm.ScheduleFrag.mCal;

/**
 * Created by Yummy on 2017-06-01.
 */
// 캘린더 날짜 표시해주는 어댑터
public class ScheduleAdapter extends BaseAdapter{
  private final List<String> list;
  private final LayoutInflater inflater;
  ViewHolder holder;
  int index = 0;
  public ScheduleAdapter(Context context, List<String> list) {
    this.list = list;
    this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  @Override
  public int getCount() { return list.size(); }

  @Override
  public String getItem(int position) { return list.get(position); }

  @Override
  public long getItemId(int position) { return position; }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
      convertView = inflater.inflate(R.layout.calendar_item, parent, false);
      holder = new ViewHolder();
      holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);
      convertView.setTag(holder);
      //해당 날짜 텍스트 컬러,배경 변경
    if(index == 0) {
      mCal = Calendar.getInstance();
      //오늘 day 가져옴
      Integer today = mCal.get(Calendar.DAY_OF_MONTH);
      String sToday = String.valueOf(today);
      if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
        holder.tvItemGridView.setTextColor(Color.BLUE);
      }
    }
    holder.tvItemGridView.setText("" + getItem(position));
    return convertView;
  }

  public void getIndex(int index) {
    this.index = index;
  }

  private class ViewHolder {
    TextView tvItemGridView;
  }
}
