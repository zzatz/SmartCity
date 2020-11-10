package nbpt.com.smartcity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegiSter extends Activity {
    EditText name,mm1,mm2,number;
    private Button zc;
    private String nn,m1,m2,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_ster);

        name=findViewById(R.id.name);
        mm1=findViewById(R.id.mm1);
        mm2=findViewById(R.id.mm2);
        number=findViewById(R.id.phone);
        zc=findViewById(R.id.btn1);
        zc.setOnClickListener(listener);
    }
    View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nn = name.getText().toString();
            m1 = mm1.getText().toString();
            m2 = mm2.getText().toString();
            n = number.getText().toString();


            if(nn.isEmpty()){
                Toast.makeText(RegiSter.this, "请输入用户名!", Toast.LENGTH_LONG).show();
            }
            else
            {
                if(m1.isEmpty()){
                    Toast.makeText(RegiSter.this, "请输入密码!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(m2.isEmpty()){
                        Toast.makeText(RegiSter.this, "请再一次输入密码!", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(!m1.equals(m2))
                        {
                            Toast.makeText(RegiSter.this, "两次密码输入不一致，请重新输入!", Toast.LENGTH_LONG).show();
                            mm1.setText("");
                            mm2.setText("");
                        }
                        else
                        {
                            AccountDBHelper db=new AccountDBHelper(RegiSter.this,1);
                            long ret =db.insertUser(nn,m1,n);
                            if(n.isEmpty()){
                                Toast.makeText(RegiSter.this, "请输入手机号!", Toast.LENGTH_LONG).show();
                            }
                            else if(!n.isEmpty()){
                                if(n.length()!=11)
                                {
                                    Toast.makeText(RegiSter.this, "请输入11位手机号!", Toast.LENGTH_LONG).show();
                                }
                                else if(!n.matches("^[0-9_]+$")){
                                    Toast.makeText(RegiSter.this, "只能输入0-9!", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(RegiSter.this, "注册成功！", Toast.LENGTH_LONG).show();
                                    Intent data = new Intent();
                                    data.putExtra("username",nn);
                                    data.putExtra("password",m1);
                                    setResult(0,data);
                                    finish();
                                }
                            }
                        }
                    }
                }
            }

        }
    };


}
