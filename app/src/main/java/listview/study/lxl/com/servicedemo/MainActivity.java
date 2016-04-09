package listview.study.lxl.com.servicedemo;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
	 Intent i = new Intent();
	firstService.MyBinder binder;
	private final ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("============Service  connect==============");
			binder = (firstService.MyBinder) service;

		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("===========Service disconnect==============");
		}
	};
	static ImageView imageView;
	static int count=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		i.setAction("org.lxl.firstService");
		imageView=(ImageView)findViewById(R.id.iv);
		findViewById(R.id.start).setOnClickListener(onClickListener);
		findViewById(R.id.stop).setOnClickListener(onClickListener);
		findViewById(R.id.status).setOnClickListener(onClickListener);
		findViewById(R.id.changeui).setOnClickListener(onClickListener);
		timer.schedule(timerTask,0,200);

	}

	public View.OnClickListener onClickListener=new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()){
				case R.id.start:
					bindService(i, connection, Service.BIND_AUTO_CREATE);
					break;
				case R.id.stop:
					unbindService(connection);
					break;
				case  R.id.status:
					Toast.makeText(getApplicationContext(), "service 的 count值为：" + binder.getCount(), Toast.LENGTH_LONG).show();
					break;
				case  R.id.changeui:
					break;
			}

		}
	};
	 static int[] imgs=new int[]{
			R.raw.i1,R.raw.i2,R.raw.i3,R.raw.i4,R.raw.i5,R.raw.i6,R.raw.i7,R.raw.i8
	};
	TimerTask timerTask=new TimerTask() {
		@Override
		public void run() {
			myhandler.sendEmptyMessage(1);
	}};
	Timer timer=new Timer();
	MyHandler myhandler=new MyHandler(this);

	static class  MyHandler extends  Handler{
		WeakReference<MainActivity> mActivity;
		MyHandler(MainActivity activity){
			mActivity=new WeakReference<MainActivity>(activity);
		}
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case  1:
					imageView.setImageResource(imgs[count++ %8]);
					break;
			}
		}
	}

}