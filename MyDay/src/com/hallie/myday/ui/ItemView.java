package com.hallie.myday.ui;

import com.hallie.myday.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("Recycle")
public class ItemView extends RelativeLayout {
	private static final int NO_RESOURCE = -1;
	private ImageView item_icon;
	// private ImageView icon_down;
	private TextView item_title;
	private TextView item_info;

	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.item_view, this);
		init();

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.ItemView);
		// 设置标题item_title
		
		
		// 设置图片资源item_icon
		setImageInfo(typedArray, R.styleable.ItemView_ItemView_left_src,
				item_icon);
		
		setText(typedArray, R.styleable.ItemView_ItemView_title, item_title);
		// 设置具体信息内容 item_info
		//setText(typedArray, R.styleable.ItemView_ItemView_right_text, item_info);
		

	}

	/**
	 * 设置图片资源
	 * 
	 * @param typedArray
	 * @param id
	 * @param view
	 */
	private void setImageInfo(TypedArray typedArray, int id, ImageView view) {
		int imageId = typedArray.getResourceId(id, NO_RESOURCE);
		if (imageId != NO_RESOURCE) {
			view.setBackgroundResource(imageId);
		}
	}

	/**
	 * 设置textview文本内容
	 * 
	 * @param typedArray
	 * @param id
	 * @param view
	 */
	private void setText(TypedArray typedArray, int id, TextView view) {
		int titleId = typedArray.getResourceId(id, NO_RESOURCE);
		if (titleId != NO_RESOURCE) {
			String title = getResources().getString(titleId);
			view.setText(title);
		}
	}

	/**
	 * 加载布局
	 */
	private void init() {
		item_icon = (ImageView) this.findViewById(R.id.item_icon);
		item_title = (TextView) this.findViewById(R.id.item_title);
		item_info = (TextView) this.findViewById(R.id.item_info);

	}

}
