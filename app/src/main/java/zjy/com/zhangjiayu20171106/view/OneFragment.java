package zjy.com.zhangjiayu20171106.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zjy.com.zhangjiayu20171106.R;
import zjy.com.zhangjiayu20171106.adapter.MyAdapter;
import zjy.com.zhangjiayu20171106.bean.CheckBean;
import zjy.com.zhangjiayu20171106.bean.MyBean;
import zjy.com.zhangjiayu20171106.presenter.Api;
import zjy.com.zhangjiayu20171106.presenter.IPresenter;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */
public class OneFragment extends Fragment implements IView, View.OnClickListener {

    private RecyclerView recyclerView;
    TextView mytext,delte;
    CheckBox cb;
    LinearLayout ll;
    private List<MyBean.SongListBean> list;
    private List<CheckBean> checklist;
    boolean b;
    boolean flag = true;
    private MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载布局
        View view = View.inflate(getActivity(), R.layout.fragment_one,null);
        //初始化控件
        mytext = (TextView) view.findViewById(R.id.mytext);
        cb = (CheckBox) view.findViewById(R.id.cb);
        delte = (TextView) view.findViewById(R.id.delte);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        //创建RecyclerView布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //实例化CheckBoxBean类
        checklist = new ArrayList<>();
        //实例化P层
        IPresenter iPresenter = new IPresenter(this);
        //调用P层自定义方法，传入网址接口
        iPresenter.getUrl(Api.URL);
        //监听事件
        cb.setOnClickListener(this);
        mytext.setOnClickListener(this);
        delte.setOnClickListener(this);
        return view;
    }

    @Override
    public void getData(List<MyBean.SongListBean> list) {
        this.list = list;
        //根据数据大小添加CheckBox数量
        for (int i = 0 ; i<list.size();i++){
            checklist.add(new CheckBean(false));
        }
        //创建适配器，RecyclerView添加适配器，显示数据
        adapter = new MyAdapter(list, checklist, getActivity(),OneFragment.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mytext:
                //boolean判断点击是否显示和改变字体内容
                if(b){
                    b = false;
                    ll.setVisibility(View.VISIBLE);
                    mytext.setText("完成");
                }else {
                    b = true;
                    ll.setVisibility(View.GONE);
                    mytext.setText("编辑");
                }
                break;
            case R.id.cb:
                //通过flag判断是否全选，默认为true
                if(flag){
                    for (int i=0;i<list.size();i++){
                        //全选
                        checklist.get(i).setIscheck(true);
                    }
                    //由选中变为非选中，执行else
                    flag = !flag;
                    //改变字体内容
                    cb.setText("取消全选");
                }else{
                    for (int i=0;i<list.size();i++){
                        //非全选
                        checklist.get(i).setIscheck(false);
                    }
                    //非选中变为选中
                    flag = !flag;
                    //改变字体内容
                    cb.setText("全选");
                }
                //刷新适配器
                adapter.notifyDataSetChanged();
                break;
            case R.id.delte:
                for (int i=0;i<list.size();i++){
                    if(checklist.get(i).ischeck()){
                        //CheckBox和RecyclerView内容全部移除
                        checklist.remove(i);
                        list.remove(i);
                        i--;
                    }
                }
                //刷新适配器
                adapter.notifyDataSetChanged();
                break;
        }
    }
    //自定义方法，改变选中非选中与字体内容，在适配器中调用
    public void setCheckChange(boolean b,String s){
        cb.setChecked(b);
        cb.setText(s);
    }
}
