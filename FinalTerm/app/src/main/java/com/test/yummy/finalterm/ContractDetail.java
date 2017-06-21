package com.test.yummy.finalterm;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.test.yummy.finalterm.MainActivity.contract_static_list;

/**
 * Created by Yummy on 2017-06-12.
 */

public class ContractDetail extends Fragment{
  ImageView imageView;
  TextView detail_nameTextView, detail_phoneTextView, detail_addressTextView, detail_memoTextView;
  Button backBtn;
  FragmentManager manager;
  int index = 0;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.contract_detail, container, false);
    imageView = (ImageView) v.findViewById(R.id.imageView);
    detail_nameTextView = (TextView) v.findViewById(R.id.detail_nameTextView);
    detail_phoneTextView = (TextView) v.findViewById(R.id.detail_phoneTextView);
    detail_addressTextView = (TextView) v.findViewById(R.id.detail_addressTextView);
    detail_memoTextView = (TextView) v.findViewById(R.id.detail_memoTextView);
    backBtn = (Button) v.findViewById(R.id.backBtn);

    // 전화번호부의 사진 받아옴
    imageView.setImageResource(contract_static_list.get(index).getResId());
    // 전화번호부의 이름 받아옴
    detail_nameTextView.setText(contract_static_list.get(index).getName());
    // 전화번호부의 전화번호 받아옴
    detail_phoneTextView.setText(contract_static_list.get(index).getPhoneNumber());
    // 전화번호부의 주소 받아옴
    detail_addressTextView.setText(contract_static_list.get(index).getAddress());
    // 전화번호부의 메모 받아옴
    detail_memoTextView.setHint(contract_static_list.get(index).getMemo_init());
    // 메모가 존재했으면 그 메모를 다시 받아옴
    if(!contract_static_list.get(index).memo.equals("")) detail_memoTextView.setText(contract_static_list.get(index).getMemo());
    // 돌아가기 버튼을 눌렀을 때 적용
    backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // 메모 저장
          contract_static_list.get(index).setMemo(detail_memoTextView.getText().toString());
          manager = getFragmentManager();
         manager.beginTransaction().replace(R.id.fragment_appbar, new ContractFrag()).commit();
       }
      });//end of setOnClickListener
    return v;
  }

  public void setIndex(int index){
    this.index = index;
  }
}
