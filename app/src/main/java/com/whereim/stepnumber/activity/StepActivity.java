package com.whereim.stepnumber.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.whereim.stepnumber.AppActivity;
import com.whereim.stepnumber.R;
import com.whereim.stepnumber.library.eventbus.EventBean;
import com.whereim.stepnumber.library.eventbus.EventBusSingle;
import com.whereim.stepnumber.library.eventbus.EventListener;
import com.whereim.stepnumber.params.AppParams;
import com.whereim.stepnumber.utils.DbFactory;

/**
 * Created by HLD on 2015/7/13.
 */
public class StepActivity extends AppActivity implements EventListener{
    private TextView txtStep;
    private long steps=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBusSingle.register(this);
        setContentView(R.layout.activity_step);
        prepare();
        init();
    }
    @Override
    public void onEvent(EventBean bean) {
        switch (bean.getEventType()){
            case AppParams.EVENT_STEP:
                runOnUiThread(stepRunnable);
                break;
        }
    }
    private Runnable stepRunnable=new Runnable() {
        @Override
        public void run() {
            steps++;
            txtStep.setText(""+steps);
        }
    };

    private void init(){
        steps = DbFactory.queryTodayStepCount(this);
        txtStep.setText(""+steps);
    }
    private void prepare(){
        txtStep=(TextView)findViewById(R.id.txtStep);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusSingle.unregister(this);
    }


}
