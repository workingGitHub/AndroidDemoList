package com.instance.working.lanrenjizhang.date;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/11/26 0026.
 * 数据类：用于保存账单信息
 *
 */

public class Account {
    private static String STRING_ID = "ID";
    private static String STRING_NAME = "NAME";
    private static String STRING_DATE = "DATE";
    private static String STRING_COST = "COST";


    private UUID mId;
    private String mRemark; //备注信息
    private Date mDate;
    private float mCost;

    Account()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public Date getmDate() {
        return mDate;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmCost(float mCost) {
        this.mCost = mCost;
    }

    public float getmCost() {
        return mCost;
    }

    public void setmRemark(String mRemark) {
        this.mRemark = mRemark;
    }

    public String getmRemark() {
        return mRemark;
    }
}
