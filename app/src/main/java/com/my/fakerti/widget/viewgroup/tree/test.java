package com.my.fakerti.widget.viewgroup.tree;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.my.fakerti.widget.viewgroup.tree.adapter.SimpleTreeAdapter;
import com.my.fakerti.widget.viewgroup.tree.adapter.TreeListAdapter;
import com.my.fakerti.widget.viewgroup.tree.bean.BaseTreeBean;
import com.my.fakerti.widget.viewgroup.tree.bean.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * 任意级树列表调用例子
 * Created by TB on 2017/4/1.
 */
public class test extends Activity {
    private List<BaseTreeBean> mDatas = new ArrayList<BaseTreeBean>();
    private ListView mLvTree;
    private SimpleTreeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            mLvTree = (ListView) findViewById(R.id.listview);
//            initData();
    }

    public void initData() {
        //增加第一层数据
        mDatas.add(new BaseTreeBean("1", "0", "文件夹"));
        //增加第二层数据
        mDatas.add(new BaseTreeBean("2", "1", "学习"));
        mDatas.add(new BaseTreeBean("3", "1", "游戏"));
        mDatas.add(new BaseTreeBean("4", "1", "娱乐"));
        //增加第三层数据
        mDatas.add(new BaseTreeBean("5", "2", "数学资料"));
        mDatas.add(new BaseTreeBean("6", "2", "语文资料"));
        mDatas.add(new BaseTreeBean("7", "2", "英语资料"));

        mDatas.add(new BaseTreeBean("8", "3", "竞技游戏"));
        mDatas.add(new BaseTreeBean("9", "3", "休闲游戏"));

        mDatas.add(new BaseTreeBean("10", "4", "电影"));
        mDatas.add(new BaseTreeBean("11", "4", "音乐"));
        //增加第四层数据
        mDatas.add(new BaseTreeBean("12", "5", "小学数学"));
        mDatas.add(new BaseTreeBean("13", "5", "初中数学"));
        mDatas.add(new BaseTreeBean("14", "5", "高中数学"));

        mDatas.add(new BaseTreeBean("15", "8", "英雄联盟"));
        mDatas.add(new BaseTreeBean("16", "8", "穿越火线"));

        mDatas.add(new BaseTreeBean("17", "10", "大话西游"));
        mDatas.add(new BaseTreeBean("18", "10", "功夫足球"));
        //可以一直添加数据层

        try {
            mAdapter = new SimpleTreeAdapter<BaseTreeBean>(mLvTree, test.this, mDatas, 0);
            mAdapter.setOnTreeNodeClickListener(new TreeListAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    if (node.isLeaf() && node.getLevel() == 3) {
                        Toast.makeText(test.this, node.getName(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mLvTree.setAdapter(mAdapter);
    }

}
