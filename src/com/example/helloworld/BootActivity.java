package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.api.Server;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BootActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_boot);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Handler handler = new Handler();
		// handler.postDelayed(new Runnable() {
		// public void run() {
		// startLoginActivity();
		// }
		// }, 1000);

		getData();

	}

	private void getData() {
		// �����ͻ��˶���
		OkHttpClient client = Server.getHttpClient();
		// �������󲢽�����ŵ��������
		Request request = Server.getRequestBuilderWithApi("hello").build();
		// �첽��������
		client.newCall(request).enqueue(new Callback() {

			/**
			 * ����ɹ��Ļص�����
			 */
			@Override
			public void onResponse(Call arg0, final Response arg1) throws IOException {
				BootActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						try {
							Toast.makeText(BootActivity.this, arg1.body().string(), Toast.LENGTH_SHORT).show();
							startLoginActivity();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}

			/**
			 * ����ʧ�ܵĻص�����
			 */
			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				BootActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(BootActivity.this, arg1.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}

	/**
	 * ��ת���µ�activity
	 */
	void startLoginActivity() {
		Intent itnt = new Intent(this, LoginActivity.class);
		startActivity(itnt);
		finish();
	}
}
