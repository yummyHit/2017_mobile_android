package com.test.yummy.finalterm;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
  implements NavigationView.OnNavigationItemSelectedListener {
  static ArrayList<ContractListClass> contract_static_list;
  static MoneyListClass money_static_list;
  static ArrayList<ScheduleManage> schedule_memo;
  static ArrayList<MoneyManage> money_memo;
  FragmentManager manager;
  ImageView home_img, schedule_img, money_img, contract_img;
  HomeFrag homeFrag;
  ScheduleFrag scheduleFrag;
  MoneyFrag moneyFrag;
  ContractFrag contractFrag;

  static final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
  static final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
  static final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
  static final Date date = new Date(System.currentTimeMillis());

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // 전화번호부 내용 입력
    contract_static_list = new ArrayList<ContractListClass>();
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_camera, "김김김", "서울시", "010-0000-0000"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_gallery, "이이이", "구리시", "010-1111-1111"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_manage, "박박박", "수원시", "010-2222-2222"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_send, "최최최", "안양시", "010-3333-3333"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_share, "장장장", "의정부시", "010-4444-4444"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_slideshow, "민민민", "과천시", "010-5555-5555"));
    contract_static_list.add(new ContractListClass(R.drawable.ic_menu_camera, "홍홍홍", "성남시", "010-6666-6666"));
    money_static_list = new MoneyListClass();
    schedule_memo = new ArrayList<ScheduleManage>();
    money_memo = new ArrayList<MoneyManage>();
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    homeFrag = new HomeFrag();
    scheduleFrag = new ScheduleFrag();
    moneyFrag = new MoneyFrag();
    contractFrag = new ContractFrag();//생성
    manager = getFragmentManager();
    manager.beginTransaction().replace(R.id.fragment_appbar, homeFrag).commit();//프레그먼트의 맨위를 해당으로 대체

    home_img = (ImageView) findViewById(R.id.home_img);
    schedule_img = (ImageView) findViewById(R.id.schedule_img);
    money_img = (ImageView) findViewById(R.id.money_img);
    contract_img = (ImageView) findViewById(R.id.contract_img);

    home_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        manager.beginTransaction().replace(R.id.fragment_appbar, homeFrag).commit();
      }
    });
    schedule_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        scheduleFrag.setIndex(1);
        manager.beginTransaction().replace(R.id.fragment_appbar, scheduleFrag).commit();
      }
    });
    money_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        manager.beginTransaction().replace(R.id.fragment_appbar, moneyFrag).commit();
      }
    });
    contract_img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        manager.beginTransaction().replace(R.id.fragment_appbar, contractFrag).commit();
      }
    });
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    manager = getFragmentManager();
    if (id == R.id.home_layout) {
      manager.beginTransaction().replace(R.id.fragment_appbar, new HomeFrag()).commit();
    } else if (id == R.id.schedule_layout) {
      manager.beginTransaction().replace(R.id.fragment_appbar, new ScheduleFrag()).commit();
    } else if (id == R.id.money_layout) {
      manager.beginTransaction().replace(R.id.fragment_appbar, new MoneyFrag()).commit();
    } else if (id == R.id.contract_layout) {
      manager.beginTransaction().replace(R.id.fragment_appbar, new ContractFrag()).commit();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
