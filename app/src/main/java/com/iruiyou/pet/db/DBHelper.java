package com.iruiyou.pet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iruiyou.common.utils.T;
import com.iruiyou.pet.App;
import com.iruiyou.pet.bean.CrashAccountEntity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private Context mContext;
    private static final String TABLE_NAME = "genesis.db";// 数据库名字

    private static final int databaseVersion=2;
    private  static ArrayList<Class> tableLsit= new ArrayList<>();

    static
    {
        tableLsit.add(CrashAccountEntity.class);// 用户提现账号
    }

    private DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
        mContext=context;
        getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource) {
//        SQLiteStatement stmt;
        // 涉及多个表和数据用事务保证完整一致
//        sqLiteDatabase.beginTransaction();
        try
        {
            for(Class classTemp:tableLsit)
            {
                int count= TableUtils.createTableIfNotExists(connectionSource,classTemp);
                //Log.e("test","count is "+count+"    createTable "+classTemp.getName());
            }
//            TableUtils.createTable(connectionSource,CellGeneralInfo.class);
        }
        catch (Exception e)
        {
            T.showShort("初始化数据库失败!!");
            e.printStackTrace();
        }
        finally {
//            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,CrashAccountEntity.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 内部枚举类
    private enum EnmuSingleton{
        Singleton(App.getInstance());
        private DBHelper singleton;

        //枚举类的构造方法在类加载是被实例化
        EnmuSingleton(Context context){
            singleton = new DBHelper(context,TABLE_NAME,null,databaseVersion);
        }

        public DBHelper getInstance(){
            return singleton;
        }
    }

    public static DBHelper getInstance() {
        return EnmuSingleton.Singleton.getInstance();
    }

}
