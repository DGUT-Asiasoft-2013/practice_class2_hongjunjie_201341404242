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
		
		fragInputCellAccount.setLableText("用户名");
		fragInputCellAccount.setHintText("输入用户名");
		fragInputCellEmail.setLableText("邮箱");
		fragInputCellEmail.setHintText("输入邮箱");
		fragInputCellPassword.setLableText("密码");
		fragInputCellPassword.setHintText("输入密码");
		fragInputCellPassword.setIsPassword(true);
		fragInputCellPasswordRepeat.setLableText("重复密码");
		fragInputCellPasswordRepeat.setHintText("重复输入密码");
		fragInputCellPasswordRepeat.setIsPassword(true);
	}

}
