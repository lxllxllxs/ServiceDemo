package listview.study.lxl.com.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/21.
 */
public class firstService extends Service {
    private final String TAG = "TestService2";
    private  MyBinder myBinder=new MyBinder();
    private  int count;
    private  boolean quit;
    public  class MyBinder extends Binder{
        public  int getCount(){
            return  count;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind方法被调用");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate方法被调用");
        new Thread(){
            public  void run(){
                while (!quit){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }count++;
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit=true;
        Log.i(TAG, "onDestroy方法被调用");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind方法被调用");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"onRebind方法被调用");
        super.onRebind(intent);
    }
}
