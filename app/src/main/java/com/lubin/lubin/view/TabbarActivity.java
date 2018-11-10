package com.lubin.lubin.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lubin.lubin.R;
import com.lubin.widget.tabbar.LubinBottomTabBar;
import com.lubin.widget.tabbar.OnTabBarListener;
import com.lubin.widget.tabbar.TabItem;

import java.util.ArrayList;
import java.util.List;

public class TabbarActivity extends AppCompatActivity implements OnTabBarListener {
    private LubinBottomTabBar tabBar;
    private List<TabItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar);
        tabBar = findViewById(R.id.tab_bar);
        list = new ArrayList<>();
        list.add(new TabItem(R.string.first, R.drawable.ic_01, 14, ""));
        list.add(new TabItem(R.string.txt_null, R.drawable.ic_launcher_background, 14, ""));
        list.add(new TabItem(R.string.second,new int[]{R.color.colorAccent, R.color.colorPrimary},12));
        list.add(new TabItem(R.string.us, R.drawable.ic_01, new int[]{R.color.colorAccent, R.color.colorPrimary}, ""));
        tabBar.initData(list, this);
    }

    @Override
    public void onTabClick(int position, TabItem item, View icon) {

    }

    @Override
    public void onTabSelect(int position, TabItem item) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
}