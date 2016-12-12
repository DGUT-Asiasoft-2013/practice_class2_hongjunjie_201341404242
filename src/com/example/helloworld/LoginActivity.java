package com.example.helloworld;

import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import java.io.IOException;

import com.example.helloworld.fragments.inputcells.PictureInputCellFragment;
import com.example.helloworld.MD5;
import com.example.helloworld.util;
import com.example.helloworld.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends Activity {
	SimpleTextInputCellFragment fragAccount,fragPassword;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goRegister();
				
			}
		});
		
		findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goLogin();
				
			}
		});
		
		findViewById(R.id.btn_forget_password).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goRecoverPassword();
				
			}
		});
		
		fragAccount=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_account);
		fragPassword=(SimpleTextInputCellFragment)getFragmentManager().findFragmentById(R.id.input_password);
		progressDialog=util.getProgressDialog(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		fragAccount.setLableText("用户名");
		fragAccount.setHintText("输入用户名");
		fragPassword.setLableText("密码");
		fragPassword.setHintText("输入密码");
		fragPassword.setIsPassword(true);
		
	}
	
	void goRecoverPassword(){
		Intent itnt = new Intent(this,PasswordReconverActivity.class);
		startActivity(itnt);
	}
	
	void goLogin(){
		//Intent itnt = new Intent(this,HelloWorldActivity.class);
		//startActivity(itnt);
		
		String account=fragAccount.getText();
		String password=fragPassword.getText();
		progressDialog.show();
		
		OkHttpClient client = new OkHttpClient();

		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("account", account).addFormDataPart("passwordHash", MD5.getMD5(password));
		MultipartBody body = builder.build();

		Request request = new Request.Builder().method("GET", null).post(body)
				.url("http://172.27.0.4:8080/membercenter/api/login").build();
	
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						LoginActivity.this.onResponse(arg0, arg1);
					}
				});
			}

			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						LoginActivity.this.onFailure(arg0, arg1);
					}
				});
			}
		});
		
	}

	private void onResponse(Call call, Response response) {
		progressDialog.dismiss();
		Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this, HelloWorldActivity.class);
				startActivity(intent);
				finish();
	}

	private void onFailure(Call call, Exception e) {
		progressDialog.dismiss();
		e.printStackTrace();
		new AlertDialog.Builder(this).setTitle("登录失败").setMessage(e.getLocalizedMessage()).show();
}
		
	
	
	void goRegister(){
		Intent itnt = new Intent(this,RegisterActivity.class);
		startActivity(itnt);
		
	}
}
