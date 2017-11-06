package zjy.com.zhangjiayu20171106;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by ZhangJiaYu on 2017/11/6.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Fresco
        Fresco.initialize(this);
    }
}
