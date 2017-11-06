package zjy.com.zhangjiayu20171106;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zjy.com.zhangjiayu20171106.adapter.MyFragmentAdapter;
import zjy.com.zhangjiayu20171106.view.OneFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout mytab;
    ViewPager vp;
    TextView mytxt;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytab = (TabLayout) findViewById(R.id.mytab);
        vp = (ViewPager) findViewById(R.id.viewpager);
        mytxt = (TextView) findViewById(R.id.mytext);
        initData();
    }

    private void initData() {
        //添加Fragment
        list = new ArrayList<>();
        for (int i = 0;i<2;i++){
            list.add(new OneFragment());
        }
        //与Fragment适配器绑定，实现Viewpager和Fragment联动
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager());
        adapter.setList(list);
        vp.setAdapter(adapter);
        //添加tab
        for (int i = 0; i <2 ;i ++){
            mytab.addTab(mytab.newTab());
        }
        //与Viewpager绑定联动
        mytab.setupWithViewPager(vp);
        mytab.getTabAt(0).setText("商品");
        mytab.getTabAt(1).setText("路线/旅游攻略");
    }
}
