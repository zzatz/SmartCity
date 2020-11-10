package nbpt.com.smartcity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2020/9/23.
 */

public class AccountDBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DB_Name="account.db";
    static final  String TABLE_Name="tb_users";

    public AccountDBHelper(Context context, int version) {
        super(context,DB_Name, null, version);
        this.mContext = context;
    }
    public long insertUser(String nn,String m1,String n){
        ContentValues values=new ContentValues();
        values.put("username",nn);
        values.put("password",m1);
        values.put("phone",n);
        //把ContentValued对象插入到数据库中
        long retValue=getReadableDatabase().insert(TABLE_Name,null,values);
        return retValue;
    }

    public Cursor select(String where,String orderby){
        StringBuilder stringBuilder =new StringBuilder("SELECT * FROM "+TABLE_Name);
        if(where!=null){
            stringBuilder.append("WHRER");
            stringBuilder.append(where);
        }
        if(orderby!=null){
            stringBuilder.append("ORDER BY");
            stringBuilder.append(orderby);
        }
        return getWritableDatabase().rawQuery(stringBuilder.toString(),null) ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
