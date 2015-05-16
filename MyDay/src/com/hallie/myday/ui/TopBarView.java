package com.hallie.myday.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hallie.myday.R;

@SuppressLint("Recycle")
public class TopBarView extends RelativeLayout implements OnClickListener {
	private static final int NO_RESOURCE = -1;
	private Button left_btn;// 返回
	private TextView tv_tile;// 标题
	private Button right_btn;// 保存

	public TopBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.view_top_bar, this);
		init();// 加载布局文件

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.TopBarView);

		// 设置标题
		setTitle(typedArray);

		// 设置左边按钮资源
		setButtonLeftInfo(typedArray);

		// 设置右边按钮资源
		setButtonRightInfo(typedArray);

	}

	/**
	 * 设置标题
	 * 
	 * @param typedArray
	 */
	private void setTitle(TypedArray typedArray) {
		int titleId = typedArray.getResourceId(
				R.styleable.TopBarView_top_title, NO_RESOURCE);
		if (titleId != NO_RESOURCE) {
			String title = getResources().getString(titleId);
			tv_tile.setText(title);
		}
	}

	/**
	 * 设置右边按钮资源
	 * 
	 * @param typedArray
	 */
	private void setButtonRightInfo(TypedArray typedArray) {
		int right = typedArray.getResourceId(
				R.styleable.TopBarView_top_button_right_text, NO_RESOURCE);
		if (right != NO_RESOURCE) {
			String button_right_text = getResources().getString(right);
			right_btn.setText(button_right_text);
			right_btn.setVisibility(View.VISIBLE);
		}
		int buttontop_right_src = typedArray.getResourceId(
				R.styleable.TopBarView_top_button_right_src, NO_RESOURCE);
		if (buttontop_right_src != NO_RESOURCE) {
			left_btn.setBackgroundResource(buttontop_right_src);
			right_btn.setOnClickListener(this);
		}
	}

	/**
	 * 设置左边按钮资源
	 * 
	 * @param typedArray
	 */
	private void setButtonLeftInfo(TypedArray typedArray) {

		int left = typedArray.getResourceId(
				R.styleable.TopBarView_top_button_left_text, NO_RESOURCE);
		if (left != NO_RESOURCE) {
			String button_left_text = getResources().getString(left);

			left_btn.setText(button_left_text);
			left_btn.setVisibility(View.VISIBLE);
		}
		int buttontop_left_src = typedArray.getResourceId(
				R.styleable.TopBarView_top_button_left_src, NO_RESOURCE);
		if (buttontop_left_src != NO_RESOURCE) {
			left_btn.setBackgroundResource(buttontop_left_src);
			left_btn.setOnClickListener(this);
		}
	}

	/**
	 * 加载布局文件
	 */
	private void init() {
		left_btn = (Button) this.findViewById(R.id.left_btn);
		tv_tile = (TextView) this.findViewById(R.id.tv_tile);

		right_btn = (Button) this.findViewById(R.id.right_btn);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
