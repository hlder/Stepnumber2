package com.whereim.stepnumber.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HLD on 2015/7/14.
 */
public class StepView extends View {

    private Paint paint=new Paint();
    private Paint paint2=new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawArc(0, 0, 0, 0, 0, 0, true, paint);
//        canvas.drawCircle(100, 100, 50, paint);
        canvas.drawCircle(50,50,50,paint);

        RectF oval=new RectF(0,0,50,50);
        canvas.drawArc(oval, 90, 0, false, paint2);
    }

    public StepView(Context context) {
        super(context);
        init();
    }
    public StepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public void init(){
        paint.setColor(Color.RED);
        paint2.setColor(Color.BLUE);
    }
}
