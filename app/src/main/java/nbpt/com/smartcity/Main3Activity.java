package nbpt.com.smartcity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends Activity {

    String FruitName[] = {"苹果", "香蕉", "樱桃", "葡萄", "芒果", "桔子", "梨", "菠萝", "草莓", "西瓜"};

    int image[] = {R.drawable.apple_pic, R.drawable.banana_pic,
            R.drawable.cherry_pic, R.drawable.grape_pic,
            R.drawable.mango_pic, R.drawable.orange_pic,
            R.drawable.pear_pic, R.drawable.pineapple_pic,
            R.drawable.strawberry_pic, R.drawable.watermelon_pic};

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        String[] from = {"img", "name"};
        int[] to = {R.id.imageView, R.id.textView};
        List<Map<String, Object>> imageList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", image[i]);
            map.put("name", FruitName[i]);
            imageList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(Main3Activity.this, imageList, R.layout.gridview, from, to);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override

            public void onItemClick(AdapterView<?>adapterView, View view, int i, longl){
                Toast.makeText(Main3Activity.this,"当前点击的水果是："+FruitName[i],Toast.LENGTH_SHORT).show();}});

    }
    }


}
