package com.example.helloworld;

import java.io.IOException;

import com.example.helloworld.api.Server;
import com.example.helloworld.entity.Article;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddCommentActivity extends Activity {

	private EditText etComment;
	private Article article;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_comment);
		etComment=(EditText) findViewById(R.id.etComment);
		article = (Article) getIntent().getSerializableExtra("article");
		findViewById(R.id.btnSubmit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onSubmit();
			}
		});
	}

	private void onSubmit() {
		String comment = etComment.getText().toString();
		if (TextUtils.isEmpty(comment)) {
			Toast.makeText(this, "��������������", Toast.LENGTH_SHORT).show();
			return;
		}
		OkHttpClient client = Server.getHttpClient();
		MultipartBody body = new MultipartBody.Builder().addFormDataPart("text", comment).build();
		Request request = Server.getRequestBuilderWithApi("article/" + article.getId() + "/comments").post(body)
				.build();
		client.newCall(request).enqueue(new Callback() {

			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {
				final String jsonString = arg1.body().string();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						onSuccess(jsonString);
					}
				});
			}

			@Override
			public void onFailure(Call arg0, final IOException arg1) {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						onFailed(arg1);
					}
				});
			}
		});
	}

	private void onSuccess(String jsonString) {
		finish();
		Toast.makeText(this, "�������۳ɹ�", Toast.LENGTH_SHORT).show();
	}

	private void onFailed(Exception e) {
		new AlertDialog.Builder(this).setTitle("���").setMessage("����ʧ��")
				.setNegativeButton("", null).show();
	}
}