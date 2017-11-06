package zjy.com.zhangjiayu20171106.model;

import retrofit2.http.GET;
import rx.Observable;
import zjy.com.zhangjiayu20171106.bean.MyBean;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */


public interface ApiService {
    //动态代理(GET请求)
    @GET("ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<MyBean> getbean();
}
