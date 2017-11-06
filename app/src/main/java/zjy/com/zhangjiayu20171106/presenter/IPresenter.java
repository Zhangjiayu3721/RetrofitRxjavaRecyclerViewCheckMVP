package zjy.com.zhangjiayu20171106.presenter;

import java.util.List;

import zjy.com.zhangjiayu20171106.bean.MyBean;
import zjy.com.zhangjiayu20171106.model.Model;
import zjy.com.zhangjiayu20171106.view.IView;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */

public class IPresenter implements Model.OnFinish{


    Model model;
    IView iView;
    //实例化Model层和IView层
    public IPresenter(IView iView) {
        this.iView = iView;
        this.model = new Model();
        model.setOnFinish(this);
    }
    //自定义方法， 调用Medol层geturl方法
    public void getUrl(String url){
        model.geturl(url);
    }
    //实现Model层OnFinsh方法
    @Override
    public void OnFinshListener(List<MyBean.SongListBean> list) {
        //给IView层赋值
        iView.getData(list);
    }
}
