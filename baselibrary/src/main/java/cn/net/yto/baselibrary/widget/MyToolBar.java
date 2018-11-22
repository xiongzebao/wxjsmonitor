package cn.net.yto.baselibrary.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.net.yto.baselibrary.R;

public class MyToolBar extends Toolbar {

   private   View rootView;
   private TextView titleView;
   private ImageButton leftImg;
   private ImageButton rightImg;

    public MyToolBar(Context context) {
        super(context);
        init();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        removeAllViews();

       int i= getChildCount();
       setBackgroundColor(Color.BLUE);
        Log.e("xiong","childcount:"+i);

      rootView=   inflate(getContext(), R.layout.layout_toolbar,this);
/*      ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
      rootView.setLayoutParams(lp);*/
        setPadding(0,0,0,0);
      titleView = rootView.findViewById(R.id.title);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0, size = getChildCount(); i < size; i++) {
            View view = getChildAt(i);

            view.layout(l, t, r , b );

        }
    }

    public TextView getTitleView() {
        return titleView;
    }

    public ImageButton getLeftImg() {
        return leftImg;
    }

    public ImageButton getRightImg() {
        return rightImg;
    }

    public View getRootView(){
        return rootView;
    }


}
