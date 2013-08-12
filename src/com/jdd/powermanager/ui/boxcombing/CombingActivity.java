package com.jdd.powermanager.ui.boxcombing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.jdd.powermanager.R;
import com.jdd.powermanager.basic.BaseFragmentActivity;
import com.jdd.powermanager.ui.boxcombing.allboxs.AssetsDetailFragment;
import com.jdd.powermanager.ui.boxcombing.submitboxs.SubmitFragment;
import com.jdd.powermanager.ui.boxcombing.unsubmitboxs.UnSubmitFragment;

public class CombingActivity extends BaseFragmentActivity
{
	private TextView mBtn_tab_un_submit;
	
	private TextView mBbtn_tab_submit;
	
	private TextView mBtn_tab_assets;
	
	private TextView mTitle;
	
	private String mDistrictId;
	
	private String mDistrictLogo;
	
	private int mTab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.combing_page);
		
		mDistrictId = (String) getIntent().getCharSequenceExtra("DistrictId");
		
		mDistrictLogo = (String) getIntent().getCharSequenceExtra("DistrictLogo");
		
		initViews();
	}
	
	private void initViews()
	{
		mTitle = (TextView) findViewById(R.id.title_text);
		
		mTitle.setText(R.string.box_combing);
		
		mBtn_tab_un_submit = (TextView) findViewById(R.id.tab_btn_un_submit);
		
		mBbtn_tab_submit = (TextView) findViewById(R.id.tab_btn_submit);
		
		mBtn_tab_assets = (TextView) findViewById(R.id.tab_btn_assets);
		
		mBtn_tab_un_submit.setOnClickListener(mOnClickLis);
		
		mBbtn_tab_submit.setOnClickListener(mOnClickLis);
		
		mBtn_tab_assets.setOnClickListener(mOnClickLis);
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		
		switch( mTab )
		{
			case 0:
				
				mOnClickLis.onClick(mBtn_tab_un_submit);;
				
				break;
				
			case 1:
				
				mOnClickLis.onClick(mBbtn_tab_submit);
				
				break;
				
			case 2:
				
				mOnClickLis.onClick(mBtn_tab_assets);
				
				break;
		}
	}
	
	private OnClickListener mOnClickLis = new OnClickListener()
	{
		public void onClick(View v) 
		{
			switch(v.getId())
			{
				case R.id.tab_btn_un_submit:
					
					mTab = 0;
					
					mBtn_tab_un_submit.setSelected(true);
					mBbtn_tab_submit.setSelected(false);
					mBtn_tab_assets.setSelected(false);
					
					showUnSubmit();
					
					break;
					
				case R.id.tab_btn_submit:
					
					mTab = 1;
					
					mBtn_tab_un_submit.setSelected(false);
					mBbtn_tab_submit.setSelected(true);
					mBtn_tab_assets.setSelected(false);
					
					showSubmit();
					
					break;
					
				case R.id.tab_btn_assets:
					
					mTab = 2;
					
					mBtn_tab_un_submit.setSelected(false);
					mBbtn_tab_submit.setSelected(false);
					mBtn_tab_assets.setSelected(true);
					
					showAssetsDetail();
					
					break;
					
				default:
					
					break;
			}
		}
	};
	
	private void showAssetsDetail()
	{
		AssetsDetailFragment f = new AssetsDetailFragment();
		
		Bundle b = new Bundle();
		
		b.putCharSequence("DistrictId", mDistrictId);
		
		f.setArguments(b);
		
		showFragment(f);
	}
	
	private void showUnSubmit()
	{
		UnSubmitFragment f = new UnSubmitFragment();
		
		Bundle b = new Bundle();
		
		b.putCharSequence("DistrictId", mDistrictId);
		
		b.putCharSequence("DistrictLogo", mDistrictLogo);
		
		f.setArguments(b);
		
		showFragment(f);
	}
	
	private void showSubmit()
	{
		SubmitFragment f = new SubmitFragment();
		
		Bundle b = new Bundle();
		
		b.putCharSequence("DistrictId", mDistrictId);
		
		f.setArguments(b);
		
		showFragment(f);
	}
	
	private void showFragment(Fragment f)
	{
		FragmentManager fm = getSupportFragmentManager();
		
		fm.beginTransaction().replace(R.id.content_fragment, f).
			setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
	}
	
}