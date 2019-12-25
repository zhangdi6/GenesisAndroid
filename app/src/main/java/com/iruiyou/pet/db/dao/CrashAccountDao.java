package com.iruiyou.pet.db.dao;

import com.iruiyou.pet.bean.CrashAccountEntity;
import com.iruiyou.pet.db.DBHelper;
import com.iruiyou.pet.utils.StringUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

public class CrashAccountDao {
    private Dao<CrashAccountEntity, Integer> dao;

    public CrashAccountDao() {
        try {
            this.dao = DBHelper.getInstance().getDao(CrashAccountEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(CrashAccountEntity data)
    {
        try {
            dao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public CrashAccountEntity getCrashAccountByUid(String userId,int accountType)
    {
        List<CrashAccountEntity> returnValue=null;
        if(StringUtil.isNotEmpty(userId))
        {
            try {
                returnValue=dao.queryBuilder().where().eq("userId",userId).and().eq("accountType",accountType).query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(returnValue!=null&&returnValue.size()>0)
        {
            return returnValue.get(0);
        }
        else
        {
            return null;
        }
    }

    public boolean delCrashAccountByUid(String userId,int accountType)
    {
        int returnValue=0;
        if(StringUtil.isNotEmpty(userId))
        {
            try {
                DeleteBuilder deleteQuery=dao.deleteBuilder();
                Where where=deleteQuery.where();
                where.eq("userId",userId).and().eq("accountType",accountType);
                returnValue=deleteQuery.delete();
            } catch (SQLException e) {
                e.printStackTrace();
                returnValue=0;
            }
        }

        return (returnValue>0);
    }



}
