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
import android.widget.ListView;

import static com.test.yummy.finalterm.MainActivity.contract_static_list;

/**
 * Created by Yummy on 2017-05-23.
 */

public class ContractFrag extends Fragment {
  ListView contract_list;
  ContractAdapter contractAdapter;
  ContractDetail contractDetail;
  FragmentManager fragmentManager;
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    ViewGroup v = (ViewGroup) inflater.inflate(R.layout.contract_layout, container, false);
    setHasOptionsMenu(true);
    contract_list = (ListView) v.findViewById(R.id.contract_list);
    contractAdapter = new ContractAdapter(getActivity(), R.layout.contract_adapter, contract_static_list);//어댑터 선언
    contract_list.setAdapter(contractAdapter);//어댑터set
   contract_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      contractDetail = new ContractDetail();
      contractDetail.setIndex(position);
      fragmentManager = getFragmentManager();
      fragmentManager.beginTransaction().replace(R.id.fragment_appbar, contractDetail).commit();
    }
  });//리스트뷰 아이템을 누르 경우
    return v;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    getActivity().getMenuInflater().inflate(R.menu.contract_setting, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.contract_add) return true;
    else if (id == R.id.contract_del) return true;
    else if (id == R.id.contract_settings) return true;

    return super.onOptionsItemSelected(item);
  }
}
