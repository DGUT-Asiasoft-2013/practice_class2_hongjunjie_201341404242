package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.api.Server;
import com.example.helloworld.entity.User;
import com.example.helloworld.fragments.inputcells.PictureInputCellFragment;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;
import com.example.helloworld.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends Activity {
	SimpleTextInputCellFragment fragInputCellAccount;
	SimpleTextInputCellFragment fragInputCellName;
	SimpleTextInputCellFragment fragInputCellPassword;
	SimpleTextInputCellFragment fragInputCellPasswordRepeat;
	SimpleTextInputCellFragment fragInputCellEmail;
	ProgressDialog progressDialog;
	PictureInputCellFragment fragInputCellPicture;
	private String TAG = "ggg";
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_register);

		fragInputCellAccount = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_account);
		fragInputCellName = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_name);
		fragInputCellEmail = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_email);
		fragInputCellPassword = (SimpleTextInputCellFragment) getFragmentManager()
				.findFragmentById(R.id.input_password);
		fragInputCellPasswordRepeat = (SimpleTextInputCellFragment) getFragmentManager()
				.findFragmentById(R.id.input_password_repeat);
		fragInputCellPicture = (PictureInputCellFragment) getFragmentManager().findFragmentById(R.id.input_picture);
		findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				onRegister();
			}
		});
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("���Ժ�");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
	}

	private void onRegister() {
		String password = fragInputCellPassword.getText();
		String passwordRepeat = fragInputCellPasswordRepeat.getText();
		if (!password.equals(passwordRepeat)) {
			Toast.makeText(this, "������������벻һ��", Toast.LENGTH_SHORT).show();
			return;
		}

		String account = fragInputCellAccount.getText();
		String email = fragInputCellEmail.getText();
		String name = fragInputCellName.getText();

		progressDialog.show();

		OkHttpClient client = Server.getHttpClient();

		MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("account", account).addFormDataPart("name", name)
				.addFormDataPart("passwordHash", MD5.getMD5(password)).addFormDataPart("email", email);
		byte[] b = fragInputCellPicture.getPngData();
		if (b != null) {
			builder.addFormDataPart("avatar", "avatar",
					RequestBody.create(MEDIA_TYPE_PNG, fragInputCellPicture.getPngData()));
		}
		MultipartBody body = builder.build();

		Request request = Server.getRequestBuilderWithApi("register").post(body).build();
		// �첽��������
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(final Call arg0, final Response arg1) throws IOException {

				String jsonString = arg1.body().string();
				ObjectMapper mapper = new ObjectMapper();
				User user = null;
				try {
					user = mapper.readValue(jsonString, User.class);
					if (user != null) {
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								RegisterActivity.this.onResponse(arg0, arg1);
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(RegisterActivity.this, "���ݽ����쳣", Toast.LENGTH_SHORT).show();
							progressDialog.dismiss();
						}
					});
					return;
				}

			}

			@Override
			public void onFailure(final Call arg0, final IOException arg1) {
								runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										RegisterActivity.this.onFailure(arg0, arg1);
									}
								});
			}
		});
	}

	private void onResponse(Call call, Response response) {
		progressDialog.dismiss();
		Toast.makeText(this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
				finish();
		//		try {
		//			new AlertDialog.Builder(this).setTitle("����ɹ�").setMessage(response.body().string())
		//					.setNegativeButton("ȷ��", null).show();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//			Log.e(TAG, e.getMessage());
		//			onFailure(call, e);
		//		}
	}

	private void onFailure(Call call, Exception e) {
		progressDialog.dismiss();
		Log.e(TAG, "�쳣" + e.getMessage());
		new AlertDialog.Builder(this).setTitle("����ʧ��").setMessage(e.getLocalizedMessage()).show();
	}

	@Override
	protected void onResume() {
		super.onResume();

		fragInputCellAccount.setLableText("�û���");
		fragInputCellAccount.setHintText("�����û���");
		fragInputCellName.setLableText("�ǳ�");
		fragInputCellName.setHintText("�����ǳ�");
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
