package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity {
    EditText name;
    EditText password;
    private Button login,zc;
    private String nn,mm;
    private CheckBox checkBox,checkBox2;
    private Boolean isCheck=false; //记录CheckBox勾选状态的变量
    private Boolean isCheck2=false;
    public SharedPreferences sp;
    private SharedPreferences s;
    private String sname,spassword;
    private int REQUEST_CREATENEW =0;//定义请求指令
    private int RESULT_OK =0;
    private int RESULT_FAIL =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name=findViewById(R.id.user);
        password=findViewById(R.id.password);
        login=findViewById(R.id.Login);
        zc=findViewById(R.id.btnRegister);
        login.setOnClickListener(listener);
        zc.setOnClickListener(listener);
        name.setHint("请输入用户名");
        password.setHint("请输入密码");

        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(chkBox_listener);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox2.setOnCheckedChangeListener(chkBox2_listener);
        sp=SecondActivity.this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        s=SecondActivity.this.getSharedPreferences("autologin", Context.MODE_PRIVATE);
        sname=sp.getString("username","");
        spassword=sp.getString("password","");
        if(sp.getBoolean("isCheck",false))
        {
            name.setText(sname);
            password.setText(spassword);
            checkBox.setChecked(true);
        }
        if(s.getBoolean("isCheck2",false)&&sp.getBoolean("isCheck",true))
        {
            Intent intent1=new Intent(SecondActivity.this,MenuActivity.class);
            startActivity(intent1);
            checkBox2.setChecked(true);
            //finish();
        }
    }
    CompoundButton.OnCheckedChangeListener chkBox_listener= new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            isCheck=b;
        }
    };
    CompoundButton.OnCheckedChangeListener chkBox2_listener= new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            s.edit().putBoolean("isCheck2",b).commit();
          isCheck2=b;
        }
    };
    View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nn = name.getText().toString();
            mm = password.getText().toString();
            switch(view.getId()){
                case   R.id.Login:
                {
                    if ((!nn.isEmpty()) && (!mm.isEmpty())) {
                        AccountDBHelper db = new AccountDBHelper(SecondActivity.this,1);
                        Cursor cursor = db.select(null, null);
                        if (cursor.getCount() != 0) {
                            cursor.moveToFirst();
                            do {
                                if(nn.equals(cursor.getString(1)) ) {
                                    if ( mm.equals(cursor.getString(2))) {
                                        if(isCheck)
                                        {
                                            SharedPreferences.Editor editor=sp.edit();//获取对象
                                            SharedPreferences.Editor editor2=s.edit();
                                            editor.putString("username",nn);
                                            editor.putString("password",mm);
                                            editor.putBoolean("isCheck",isCheck); //添加相关信息
                                            editor.commit();//保存
                                            s.edit().putBoolean("isCheck2",isCheck2).commit();
                                        }
                                        else
                                        {
                                            SharedPreferences.Editor editor=sp.edit();
                                            editor.clear();
                                        }
                                        // Toast.makeText(SecondActivity.this, "登陆成功!", Toast.LENGTH_LONG).show();
                                        Intent intent1=new Intent(SecondActivity.this,MenuActivity.class);
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(SecondActivity.this, "密码错误!", Toast.LENGTH_LONG).show();
                                        }
                                }
//                                else
//                                {
//                                    Toast.makeText(SecondActivity.this, "账号未注册!", Toast.LENGTH_LONG).show();
//                                }
                            } while (cursor.moveToNext());
                            cursor.close();
                        }
                    }else if (nn.isEmpty()&&mm.isEmpty()){
                        Toast.makeText(SecondActivity.this, "账号和密码为空!", Toast.LENGTH_LONG).show();
                    }else if(mm.isEmpty()){
                        Toast.makeText(SecondActivity.this, "密码为空!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SecondActivity.this, "账号为空!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
                case R.id.btnRegister:
                {
//                    Intent intent=new Intent(SecondActivity.this,RegiSter.class);
//                    startActivity(intent);
                    Intent intent=new Intent(SecondActivity.this,RegiSter.class);
                    startActivityForResult(intent,REQUEST_CREATENEW);
                }
                break;
            }

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CREATENEW){
            //判断执行返回值
            if(resultCode==RESULT_OK){
                String user = data.getStringExtra("username");
                String Password = data.getStringExtra("password");
                name.setText(user);
                password.setText(Password);
            }
            else{
                Log.d("","注册新用户返回失败");}}
        }

}


