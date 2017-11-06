package zjy.com.zhangjiayu20171106.view;

import java.util.List;

import zjy.com.zhangjiayu20171106.bean.MyBean;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */

public interface IView {
    //创建接口，在P层中调用
    void getData(List<MyBean.SongListBean> list);
}
