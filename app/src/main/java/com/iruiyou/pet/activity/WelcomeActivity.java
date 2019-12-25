package com.iruiyou.pet.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.iruiyou.common.utils.AppManager;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * 欢迎界面
 * 
 * @author NIT
 * 
 */
public class WelcomeActivity extends BaseActivity {

	private ViewPager vp;// viewpager
	private LinearLayout ll_container;// 盛放小圆点的容器
	private int prePosition = 0;// 前一个小圆点
	private List<View> mList;// 图片集合
	private View childAt;

	@Override
	public int getLayout() {
		return R.layout.activity_welcome;
	}

	@Override
	public void OnActCreate(Bundle savedInstanceState) {
		initView();// 初始化view
		setData();// 设置数据
		Adapter adapter = new Adapter();
		vp.setAdapter(adapter);
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				ll_container.getChildAt(prePosition).setBackgroundResource(
						R.drawable.ic_launcher);

				ll_container.getChildAt(position).setBackgroundResource(
						R.drawable.shape_point_active);
				ObjectAnimator
						.ofFloat(ll_container.getChildAt(position), "alpha",
								0.0f, 1.0f).setDuration(500).start();
				// 改变前一个位置的值
				prePosition = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setData() {
		mList = new ArrayList<>();
		View view1 = getLayoutInflater().inflate(R.layout.welcome1, null);
		View view2 = getLayoutInflater().inflate(R.layout.welcome2, null);
		View view3 = getLayoutInflater().inflate(R.layout.welcome3, null);

		mList.add(view1);
		mList.add(view2);
		mList.add(view3);

		// 创建下面的小圆点
		for (int i = 0; i < mList.size(); i++) {
			View view = new View(this);
			LayoutParams params = new LayoutParams(25, 25);
			view.setBackgroundResource(R.drawable.shape_point);
			if (i != 0) {
				params.leftMargin = 10;
			}
			view.setLayoutParams(params);
			ll_container.addView(view);
		}

		childAt = ll_container.getChildAt(0);
		childAt.setBackgroundResource(R.drawable.shape_point_active);

		Button btn = view3.findViewById(R.id.welcome_btn);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(LoginActivity.class);
				AppManager.getAppManager().finishAllActivity();// 进入主界面，将之前的界面杀死
			}
		});
	}

	@Override
	public void OnViewClick(View v) {
	}

	private void initView() {
		vp = findViewById(R.id.welcome_vp);
		ll_container = findViewById(R.id.welcome_ll);
		ll_container.setVisibility(View.GONE);

	}

	class Adapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public boolean isViewFromObject( View view,  Object object) {
			return view == object;
		}


		@Override
		public Object instantiateItem( ViewGroup container, int position) {
			container.addView(mList.get(position));
			return mList.get(position);
		}

		@Override
		public void destroyItem( ViewGroup container, int position,  Object object) {
			container.removeView(mList.get(position));
		}
	}

}
