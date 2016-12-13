package com.example.helloworld.fragments;

import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;

public class PasswordRecorverStep1Fragment extends Fragment {
	SimpleTextInputCellFragment fragEmail;
	View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if (view==null){
		view=inflater.inflate(R.layout.fragment_password_recover_step1,null);
		
		fragEmail=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_email);
		
		view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goNext();
				
			}
		});
	}
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		fragEmail.setLableText("◊¢≤·” œ‰");
		fragEmail.setHintText(" ‰»Î◊¢≤·” œ‰µÿ÷∑");
	}
	
	private void goNext() {
		if (onGoNextListener != null) {
			onGoNextListener.goNext();
		}
	}
	
	public String getEmailText(){
		return fragEmail.getText();
	}

	public static interface OnGoNextListener {
		void goNext();
	}

	private OnGoNextListener onGoNextListener;

	public void setOnNextListener(OnGoNextListener onGoNextListener) {
		this.onGoNextListener = onGoNextListener;
	}
}
	
	
	
	
