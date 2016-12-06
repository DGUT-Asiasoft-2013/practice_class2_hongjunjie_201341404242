package com.example.helloworld;

import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity {
	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	SimpleTextInputCellFragment fragInputCellEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_register);
		
		fragInputCellAccount = (SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellEmail=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_email);
		fragInputCellPassword = (SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat = (SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password_repeat);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		fragInputCellAccount.setLableText("�û���");
		fragInputCellAccount.setHintText("�����û���");
		fragInputCellEmail.setLableText("����");
		fragInputCellEmail.setHintText("��������");
		fragInputCellPassword.setLableText("����");
		fragInputCellPassword.setHintText("��������");
		fragInputCellPassword.setIsPassword(true);
		fragInputCellPasswordRepeat.setLableText("�ظ�����");
		fragInputCellPasswordRepeat.setHintText("�ظ���������");
		fragInputCellPasswordRepeat.setIsPassword(true);
	}

}
