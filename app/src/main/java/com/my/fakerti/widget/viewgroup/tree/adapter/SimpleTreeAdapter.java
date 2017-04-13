package com.my.fakerti.widget.viewgroup.tree.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.my.fakerti.R;
import com.my.fakerti.widget.viewgroup.tree.bean.Node;

import java.util.List;


public class SimpleTreeAdapter<T> extends TreeListAdapter<T>
{

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
							 int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException
	{
		super(mTree, context, datas, defaultExpandLevel);
	}

	@Override
	public View getConvertView(Node node , int position, View convertView, ViewGroup parent)
	{

		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.adapter_tree_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_label);
			convertView.setTag(viewHolder);
			viewHolder.next = (ImageView) convertView
					.findViewById(R.id.id_treenode_next);

		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1)
		{
			viewHolder.icon.setVisibility(View.INVISIBLE);
			if(node.getLevel()==3){
				viewHolder.next.setVisibility(View.VISIBLE);
			}else{
				viewHolder.next.setVisibility(View.INVISIBLE);
			}
		} else
		{
			viewHolder.next.setVisibility(View.INVISIBLE);
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}
		viewHolder.label.setText(node.getName());

		return convertView;
	}

	private final class ViewHolder
	{
		ImageView icon;
		ImageView next;
		TextView label;
	}

}
