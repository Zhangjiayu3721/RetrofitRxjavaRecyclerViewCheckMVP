package zjy.com.zhangjiayu20171106.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import zjy.com.zhangjiayu20171106.R;
import zjy.com.zhangjiayu20171106.bean.CheckBean;
import zjy.com.zhangjiayu20171106.bean.MyBean;
import zjy.com.zhangjiayu20171106.view.OneFragment;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    List<MyBean.SongListBean> list = new ArrayList<>();
    List<CheckBean> checkBeen = new ArrayList<>();
    Context context;
    int flag;
    OneFragment one;

    //有参构造
    public MyAdapter(List<MyBean.SongListBean> list, List<CheckBean> checkBeen, Context context,OneFragment one) {
        this.list = list;
        this.checkBeen = checkBeen;
        this.context = context;
        this.one = one;
    }

    //创建布局
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = null;
        View view = View.inflate(context,R.layout.fragment_one_item,null);
        holder = new MyViewHolder(view);
        return holder;
    }
    //绑定视图，传入数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //Fresco使用
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(list.get(position).getPic_big())
                .setOldController(holder.img.getController())
                .setTapToRetryEnabled(true)
                .build();
        holder.img.setController(controller);
        holder.tv.setText(list.get(position).getTitle());
        //设置多选框选中状态
        holder.item_check.setChecked(checkBeen.get(position).ischeck());
        //判断是否全选和非全选
        holder.item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置CheckBox的值
                checkBeen.get(position).setIscheck(!checkBeen.get(position).ischeck());
                //初始值
                flag = 0;
                for (int i =0;i<list.size();i++){
                    //获取CheckBox的状态
                    if(!checkBeen.get(i).ischeck()){
                        flag=1;
                    }
                }
                //调用OneFragment自定义方法，当flag等于1的时候，为非选中，字体内容为全选
                if(flag==1){
                    one.setCheckChange(false,"全选");
                    //反之同上
                }else {
                    one.setCheckChange(true,"取消全选");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        //数据大小
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        //初始化控件
        SimpleDraweeView img;
        TextView tv;
        CheckBox item_check;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.tv);
            item_check = (CheckBox) itemView.findViewById(R.id.item_check);
        }
    }
}
