package com.test.yummy.finalterm;

import java.util.ArrayList;

/**
 * Created by Yummy on 2017-06-12.
 */

public class MoneyListClass {
  static ArrayList<String> mGroupList = new ArrayList<String>();
  ArrayList<ArrayList<String>> mChildList = new ArrayList<ArrayList<String>>();
  static ArrayList<String> mChildListContent = new ArrayList<String>();
  //생성자
  public MoneyListClass() {
    mGroupList.add("2017년");
    mGroupList.add("2016년");
    mGroupList.add("2015년");

    mChildListContent.add("1월");
    mChildListContent.add("2월");
    mChildListContent.add("3월");
    mChildListContent.add("4월");
    mChildListContent.add("5월");
    mChildListContent.add("6월");
    mChildListContent.add("7월");
    mChildListContent.add("8월");
    mChildListContent.add("9월");
    mChildListContent.add("10월");
    mChildListContent.add("11월");
    mChildListContent.add("12월");

    mChildList.add(mChildListContent);
    mChildList.add(mChildListContent);
    mChildList.add(mChildListContent);
  }

  public ArrayList getGroupList() { return mGroupList; }

  public ArrayList<ArrayList<String>> getChildList() {
    return mChildList;
  }

  public ArrayList getChildListContent() { return mChildListContent; }
}
