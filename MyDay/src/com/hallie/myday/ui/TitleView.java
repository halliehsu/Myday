package com.hallie.myday.ui;

import com.hallie.myday.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("Recycle")
public class TitleView extends RelativeLayout implements OnClickListener {
	private TextView tv_left;
	private TextView tv_title;
	private TextView tv_right;

	private ImageView iv_left;
	private ImageView iv_right;


	/**
	 * 右边按钮监听
	 */
	private OnTitleRightButtonClickListener rightBtnListener;

	/**
	 * 左边按钮监听
	 */
	private OnTitleLeftButtonClickListener leftBtnListener;

	/**
	 * 左边按钮默认为返回，如果左边按钮只有返回操作，设置为true，则不需要再设置leftBtnListener�?
	 * 如果左边按钮不是返回操作，则设置为false，另外设置leftBtnListener�?
	 */
	private boolean backBtnClickable = true;

	public boolean isBackBtnClickable() {
		return backBtnClickable;
	}

	public void setBackBtnClickable(boolean backBtnClickable) {
		this.backBtnClickable = backBtnClickable;
	}

	public interface OnTitleRightButtonClickListener {
		void onRightButtonClick(View v);
	}

	public interface OnTitleLeftButtonClickListener {
		void onLeftButtonClick(View v);
	}

	public void setRightBtnListener(
			OnTitleRightButtonClickListener rightBtnListener) {
		this.rightBtnListener = rightBtnListener;
	}

	public void setLeftBtnListener(
			OnTitleLeftButtonClickListener leftBtnListener) {
		this.leftBtnListener = leftBtnListener;
	}
	
	public void setItemTitle(String title)
	{
		tv_title.setText(title);
	}
	
	public void setRightTextView(String tvRight){
		tv_right.setText(tvRight);
	}
	public String getRightTextView(){
		return tv_right.getText().toString();
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View.inflate(context, R.layout.title_view, this);
		tv_left = (TextView) this.findViewById(R.id.tv_left);
		iv_left = (ImageView) this.findViewById(R.id.iv_left);

		String item_title_left = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.gome.videoonline",
				"item_title_left");
		if (item_title_left != null) {
			tv_left.setText(item_title_left);
			tv_left.setVisibility(View.VISIBLE);
			tv_left.setOnClickListener(this);
		}

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.ImageView);
		int left_src = typedArray.getResourceId(R.styleable.ImageView_left_src,
				0);
		if (left_src != 0) {
			iv_left.setImageResource(left_src);
			iv_left.setVisibility(View.VISIBLE);
			iv_left.setOnClickListener(this);
		}

		tv_title = (TextView) this.findViewById(R.id.tv_title);
		String item_title = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.gome.videoonline",
				"item_title");
		if (item_title != null) {
			tv_title.setText(item_title);
			tv_title.setVisibility(View.VISIBLE);
		}

		tv_right = (TextView) this.findViewById(R.id.tv_right);
		iv_right = (ImageView) this.findViewById(R.id.iv_right);
		String item_title_right = attrs.getAttributeValue(
				"http://schemas.android.com/apk/res/com.gome.videoonline",
				"item_title_right");
		if (item_title_right != null) {
			tv_right.setText(item_title_right);
			tv_right.setVisibility(View.VISIBLE);
			tv_right.setOnClickListener(this);
		}

		int right_src = typedArray.getResourceId(
				R.styleable.ImageView_right_src, 0);
		if (right_src != 0) {
			iv_right.setImageResource(right_src);
			iv_right.setVisibility(View.VISIBLE);
			iv_right.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (R.id.tv_left == id || R.id.iv_left == id) {

			if (null != leftBtnListener) {
				// 加这个监听为了按返回键时可以处理想要处理的动�?
				leftBtnListener.onLeftButtonClick(v);
			}
			Context context = v.getContext();
			if (backBtnClickable && (context instanceof Activity)) {
				((Activity) context).onBackPressed();

			}
		} else if (R.id.tv_right == id || R.id.iv_right == id) {
			if (null != rightBtnListener)
				rightBtnListener.onRightButtonClick(v);
		}
	}

	

}
