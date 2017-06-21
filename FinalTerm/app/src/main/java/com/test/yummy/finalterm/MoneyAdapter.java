package com.test.yummy.finalterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yummy on 2017-06-12.
 */
// 가계부의 ExpandableListView 출력기능
public class MoneyAdapter extends BaseExpandableListAdapter {
  ArrayList<String> groupList;
  ArrayList<ArrayList<String>> childList;
  ArrayList<String> childListContent;
  LayoutInflater inflater;
  private ViewHolder viewHolder;

  public MoneyAdapter(Context c, ArrayList<String> groupList, ArrayList<ArrayList<String>> childList){
    this.inflater = LayoutInflater.from(c);
    this.groupList = groupList;
    this.childList = childList;
  }

  @Override
  public String getGroup(int groupPosition) {
    return groupList.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return groupList.size();
  }

  @Override
  public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override
  public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    View v = convertView;

    if(v == null){
      viewHolder = new ViewHolder();
      v = inflater.inflate(R.layout.money_item, parent, false);
      viewHolder.tv_groupName = (TextView) v.findViewById(R.id.tv_group);
      v.setTag(viewHolder);
    }else{
      viewHolder = (ViewHolder)v.getTag();
    }
    viewHolder.tv_groupName.setText(getGroup(groupPosition));

    return v;
  }

  @Override
  public String getChild(int groupPosition, int childPosition) {
    return childList.get(groupPosition).get(childPosition);
  }

  @Override
  public int getChildrenCount(int groupPosition) {
    return childList.get(groupPosition).size();
  }

  @Override
  public long getChildId(int groupPosition, int childPosition) {
    return childPosition;
  }

  @Override
  public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    View v = convertView;

    if(v == null){
      viewHolder = new ViewHolder();
      v = inflater.inflate(R.layout.money_item, null);
      viewHolder.tv_childName = (TextView) v.findViewById(R.id.tv_child);
      v.setTag(viewHolder);
    }else{
      viewHolder = (ViewHolder)v.getTag();
    }

    viewHolder.tv_childName.setText(getChild(groupPosition, childPosition));

    return v;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }

  class ViewHolder {
    public TextView tv_groupName;
    public TextView tv_childName;
  }
}
