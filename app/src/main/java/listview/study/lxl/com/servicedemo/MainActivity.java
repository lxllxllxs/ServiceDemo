package listview.study.lxl.com.servicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    firstService.MyBinder binder;
     private  final ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("============Service  connect==============");
            binder=(firstService.MyBinder)service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("===========Service disconnect==============");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart=(Button)findViewById(R.id.start);
        Button btnStop=(Button)findViewById(R.id.stop);
        Button btnStatus=(Button)findViewById(R.id.status);
        final  Intent i=new Intent();
        i.setAction("org.lxl.firstService");
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(i, connection, Service.BIND_AUTO_CREATE);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            unbindService(connection);
            }
        });


        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"service 的 count值为："+binder.getCount(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
