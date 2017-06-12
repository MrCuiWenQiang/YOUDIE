package com.my.fakerti;




import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.my.fakerti.base.activity.toolbar.ActivityToolbar;


public class MainActivity extends ActivityToolbar {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("1024");
        setLeftButton(R.drawable.homeleft, new OnClickListener() {
            @Override
            public void onclick() {
                finish();
            }
        });
        setMenu(R.menu.menutest, new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.f) {
                    Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_LONG).show();

                } else if (i == R.id.mtest) {
                    Toast.makeText(getApplicationContext(), "12545563", Toast.LENGTH_LONG).show();

                }
                return true;
            }
        });
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initData() {

    }


    @Override
    protected int getContentChildView() {
        return R.layout.activity_main;
    }
}
