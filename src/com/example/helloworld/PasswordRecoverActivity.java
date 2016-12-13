package com.example.helloworld;

import com.example.helloworld.fragments.PasswordRecorverStep1Fragment;
import com.example.helloworld.fragments.PasswordRecorverStep1Fragment.OnGoNextListener;
import com.example.helloworld.fragments.PasswordRecorverStep2Fragment;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

import com.example.helloworld.api.Server;
import com.example.helloworld.entity.User;
import com.example.helloworld.fragments.PasswordRecorverStep1Fragment;
import com.example.helloworld.fragments.PasswordRecorverStep1Fragment.OnGoNextListener;
import com.example.helloworld.fragments.PasswordRecorverStep2Fragment;
import com.example.helloworld.fragments.PasswordRecorverStep2Fragment.OnSubmitListener;
import com.example.helloworld.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PasswordRecoverActivity extends Activity {

	private PasswordRecorverStep1Fragment step1 = new PasswordRecorverStep1Fragment();
	private PasswordRecorverStep2Fragment step2 = new PasswordRecorverStep2Fragment();
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_recover);
		getFragmentManager().beginTransaction().replace(R.id.container, step1).commit();
		step1.setOnNextListener(new OnGoNextListener() {

			@Override
			public void goNext() {
				goStep2();
			}
		});
		step2.setOnSubmitListener(new OnSubmitListener() {

			@Override
			public void onSubmit() {
				PasswordRecoverActivity.this.onSubmit();
			}
		});
	}

	private void goStep2() {
		OkHttpClient client = Server.getHttpClient();
		MultipartBody.Builder builder = new MultipartBody.Builder().addFormDataPart("email", step1.getEmailText());
		MultipartBody body = builder.build();
		Request request = Server.getRequestBuilderWithApi("inputEmail").post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {

				String jsonString = arg1.body().string();
				ObjectMapper mapper = new ObjectMapper();
				try {
					user = mapper.readValue(jsonString, User.class);
					if (user != null) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								PasswordRecoverActivity.this.onResponse(arg0, arg1);
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(PasswordRecoverActivity.this, "���ݽ����쳣", Toast.LENGTH_SHORT).show();
						}
					});
					return;
				}

			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						new AlertDialog.Builder(PasswordRecoverActivity.this).setTitle("����ʧ��")
								.setMessage(arg1.getLocalizedMessage()).show();
					}
				});
			}
		});

	}

	private void onSubmit() {
		String Verify = step2.getVerify();
		String password = step2.getPassword();
		String password_Repeat = step2.getPassword_Repeat();
		if (!password.equals(password_Repeat)) {
			Toast.makeText(this, "������������벻һ��", Toast.LENGTH_SHORT).show();
			return;
		}
		OkHttpClient client = Server.getHttpClient();
		MultipartBody.Builder builder = new MultipartBody.Builder().addFormDataPart("Verify", Verify)
				.addFormDataPart("email", step1.getEmailText()).addFormDataPart("passwordHash", MD5.getMD5(password));
		MultipartBody body = builder.build();
		Request request = Server.getRequestBuilderWithApi("passwordrecover").post(body).build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {
				String response = arg1.body().string();
				Log.e("ggg", response);
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						step2OnResponse(arg0, arg1);
					}
				});
				return;

			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						new AlertDialog.Builder(PasswordRecoverActivity.this).setTitle("����ʧ��")
								.setMessage(arg1.getLocalizedMessage()).show();
					}
				});
			}
		});

	}

	private void onResponse(Call call, Response response) {
		// setCustomAnimations()����������add��remove��replace����֮ǰ�����ã�����������
		getFragmentManager()
				.beginTransaction().setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left,
						R.animator.slide_in_left, R.animator.slide_out_right)
				.replace(R.id.container, step2).addToBackStack(null).commit();
	}

	private void step2OnResponse(Call call, Response response) {
		Toast.makeText(this, "�޸�����ɹ��������µ�¼", Toast.LENGTH_SHORT).show();
		finish();
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}
}