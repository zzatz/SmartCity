package nbpt.com.smartcity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class Main3Activity extends Activity {

    String FruitName[] = {"苹果","香蕉","樱桃","葡萄","芒果","桔子","梨","菠萝","草莓","西瓜"};
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,FruitName);

        gridView.setAdapter(adapter);


}
}
