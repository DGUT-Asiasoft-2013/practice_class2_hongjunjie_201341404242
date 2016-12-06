package com.example.helloworld.fragments;

import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverSetp2Fragment extends Fragment{
	View view;
	SimpleTextInputCellFragment fragInputCellVerify;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPassword_repeat;
	
	@Override
	public void onResume(){
		super.onResume();
		
		fragInputCellVerify.setLableText("��֤��");
		fragInputCellVerify.setHintText("������֤��");
		fragInputCellPassword.setLableText("������");
		fragInputCellPassword.setHintText("����������");
		fragInputCellPassword.setIsPassword(true);
		fragInputCellPassword_repeat.setLableText("�ظ�����");
		fragInputCellPassword_repeat.setHintText("�ظ���������");
		fragInputCellPassword_repeat.setIsPassword(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(view==null){
			view=inflater.inflate(R.layout.fragment_password_recover_step2,null);
			fragInputCellVerify = (SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_verify);
			fragInputCellPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
			fragInputCellPassword_repeat = (SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password_repeat);
		}
		return view;
	}
}
