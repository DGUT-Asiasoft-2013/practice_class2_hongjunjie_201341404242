package com.example.helloworld;

import com.example.helloworld.entity.Article;
import com.example.helloworld.fragments.AvatarView;

import java.text.SimpleDateFormat;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class FeedContentActivity extends Activity implements OnClickListener {
	
	private Article article;
	private TextView tvTitle;
	private TextView tvTime;
	private TextView tvContent;
	private AvatarView avatar;
	private TextView authorName;
	private Button btnComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		article = (Article) getIntent().getSerializableExtra("text");
		setContentView(R.layout.activity_feed_content);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvTime = (TextView) findViewById(R.id.tvTime);
		tvContent = (TextView) findViewById(R.id.tvContent);
		avatar = (AvatarView) findViewById(R.id.avatar);
		btnComment=(Button) findViewById(R.id.btnComment);
		authorName = (TextView) findViewById(R.id.tvAuthor);

		tvTitle.setText(article.getTitle());
		tvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(article.getCreateDate()));
		tvContent.setText(article.getText());
		authorName.setText(article.getAuthorName());
		avatar.load(article.getAuthorAvatar()==null?"upload/face.jpg":article.getAuthorAvatar());
				tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
				btnComment.setOnClickListener(this);
			}
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btnComment:
					Intent intent=new Intent(this,AddCommentActivity.class);
					intent.putExtra("article", article);
					startActivity(intent);
					break;
		
				default:
					break;
				}

	}

}