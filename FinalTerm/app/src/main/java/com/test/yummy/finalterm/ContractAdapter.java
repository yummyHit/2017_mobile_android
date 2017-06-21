package com.test.yummy.finalterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Yummy on 2017-05-23.
 */

public class ContractAdapter extends BaseAdapter {
  ImageView contract_list_img;
  TextView nameTextView;
  TextView addressTextView;
  TextView phoneTextView;

  Context mContext;//전달받은 Context 객체를 저장할 변수
  int contract_item;
  ArrayList<ContractListClass> contract_static_list;
  LayoutInflater inflater;

  //어댑터 생성자
  public ContractAdapter(Context context, int contract_item, ArrayList<ContractListClass> contract_static_list) {
    mContext = context;
    this.contract_item = contract_item;
    this.contract_static_list = contract_static_list;
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return contract_static_list.size();//리스트의 크기
  }

  //파라미터로 전달된 인덱스에 해당하는 데이터를 반환
  @Override
  public Object getItem(int position) {
    return contract_static_list.get(position);//리스트에서 아이템을 가져와 반환
  }

  //현재 아이템의 Id값을 인덱스값(position)을 반환
  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override//화면에 보일 아이템을 위한 뷰를 만들어 반환
  public View getView(int position, View convertView, ViewGroup parent) {
    //아이템을 위한 레이아웃 생성
    if (convertView == null) {
      convertView = inflater.inflate(this.contract_item, parent, false);
    }

    //아이템의 인덱스값(position)을 이용해 리스트에 들어있는 아이템을 가져옴

    contract_list_img = (ImageView) convertView.findViewById(R.id.contract_list_img);
    nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
    addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);
    phoneTextView = (TextView) convertView.findViewById(R.id.phoneTextView);

    contract_list_img.setImageResource(contract_static_list.get(position).getResId());
    nameTextView.setText(contract_static_list.get(position).getName());
    addressTextView.setText(contract_static_list.get(position).getAddress());
    phoneTextView.setText(contract_static_list.get(position).getPhoneNumber());
    return convertView;//레이아웃을 리턴
  }
}
