package nbpt.com.smartcity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class Main2Activity extends Activity {
    EditText name;
    EditText wid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.tnn);
        wid=findViewById(R.id.tmm);
        name.setHint("请输入姓名");
        wid.setHint("请输入性别");
    }

}
