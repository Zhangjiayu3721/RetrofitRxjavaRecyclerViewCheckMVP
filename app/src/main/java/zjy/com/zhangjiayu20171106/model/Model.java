package zjy.com.zhangjiayu20171106.model;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import zjy.com.zhangjiayu20171106.LoggingInterceptor;
import zjy.com.zhangjiayu20171106.bean.MyBean;
import zjy.com.zhangjiayu20171106.presenter.Api;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */

public class Model implements IModel{


    OnFinish onFinish;
    //做一个接口OnFinish，里面传入网络请求之后的数据，然后在prrsenter中调用
    public interface OnFinish{
        void OnFinshListener(List<MyBean.SongListBean> list);
    }
    //实例化接口
    public void setOnFinish(OnFinish onFinish){
        this.onFinish = onFinish;
    }

    //调用IModel中的方法
    @Override
    public void geturl(String url) {
        //添加拦截器
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        //Retrofit请求数据，添加Retrofit拦截器
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.URL).addConverterFactory(GsonConverterFactory.create()).client(client).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        //动态代理
        ApiService apiService = retrofit.create(ApiService.class);
        //获取被观察者
        Observable<MyBean> bean = apiService.getbean();
        //通过被观察者订阅观察者重写方法来得到数据
        bean.subscribeOn(Schedulers.io())//开启IO流线程
                .observeOn(AndroidSchedulers.mainThread())//返回主线程
                .subscribe(new Observer<MyBean>() {//订阅观察者
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyBean myBean) {
                        //获取数据
                        List<MyBean.SongListBean> data = myBean.getSong_list();
                        //把数据添加到OnFinsh中
                        onFinish.OnFinshListener(data);
                    }
                });
    }
}
