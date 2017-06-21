package com.test.yummy.finalterm;

/**
 * Created by Yummy on 2017-05-23.
 */

public class ContractListClass {
  int resId;
  String name;//이름
  String address;
  String phone_number;//소속
  String memo;

  //생성자
  public ContractListClass(int resId, String name, String address, String phone_number) {
    this.resId = resId;
    this.name = name;
    this.address = address;
    this.phone_number = phone_number;
    this.memo = "";
  }

  public int getResId() { return resId; }

  public String getName() {
    return name;
  }

  public String getAddress() { return address; }

  public String getPhoneNumber() {
    return phone_number;
  }

  public String getMemo_init() { return name + "님을 위한 메모 입력란 입니다.";  }

  public String getMemo() { return memo; }

  public void setMemo(String memo) { this.memo = memo; }
}
