package com.hallie.myday;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hallie.myday.db.dao.TypeDao;
import com.hallie.myday.util.MyTextUtils;

public class ManageTypeActivit extends Activity {
	private EditText et_name_type;
	private Button bt_add;
	private ListView lv_type;
	private Button bt_upper_page;

	private List<String> allType = new ArrayList<String>();
	private TypeDao typeDao;

	private TypeAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mange_type);

		et_name_type = (EditText) findViewById(R.id.et_name_type);
		bt_add = (Button) findViewById(R.id.bt_add);
		lv_type = (ListView) findViewById(R.id.lv_type);
		bt_upper_page = (Button) findViewById(R.id.bt_upper_page);

		typeDao = new TypeDao(ManageTypeActivit.this);
		allType = typeDao.findAllType();

		bt_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String newType = et_name_type.getText().toString().trim();
				if (TextUtils.isEmpty(newType)) {
					Toast.makeText(ManageTypeActivit.this, "请输入事件类型",
							Toast.LENGTH_LONG).show();
					return;
				}
				typeDao.addType(newType);
				allType.add(newType);
				adapter.notifyDataSetChanged();

			}
		});
		bt_upper_page.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		adapter = new TypeAdapter();
		lv_type.setAdapter(adapter);
	}

	class TypeAdapter extends BaseAdapter {
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			LinearLayout ll = new LinearLayout(ManageTypeActivit.this);
			ll.setOrientation(LinearLayout.HORIZONTAL);
			ll.setGravity(Gravity.CENTER_VERTICAL);
			TextView tv = new TextView(ManageTypeActivit.this);
			tv.setText(allType.get(position));
			MyTextUtils.setText(tv);
			tv.setPadding(20, 2, 0, 2);
			ll.addView(tv);

			//System.out.println(allType.get(position).toString());

			if (position >= ConstantValue.defaultType.length) {
				ImageButton ib = new ImageButton(ManageTypeActivit.this);
				ib.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.cross));
				ib.setLayoutParams(new LayoutParams(24, 24));
				ib.setPadding(40, 0, 0, 0);
				ll.addView(ib);

				ib.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						typeDao.deleteType(allType.get(position));

						allType = typeDao.findAllType();
						adapter.notifyDataSetChanged();

					}
				});

			}

			return ll;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allType.size();
		}
	}

}
