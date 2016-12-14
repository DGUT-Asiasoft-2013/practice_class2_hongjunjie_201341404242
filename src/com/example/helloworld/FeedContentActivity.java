package com.example.helloworld;

import com.example.helloworld.entity.Article;
import com.example.helloworld.fragments.AvatarView;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class FeedContentActivity extends Activity {

	private TextView tvTitle;
	private TextView tvTime;
	private TextView title;
	private TextView articleText;
	private AvatarView avatar;
	private TextView authorName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Article article = (Article) getIntent().getSerializableExtra("text");
		setContentView(R.layout.activity_feed_content);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvTime = (TextView) findViewById(R.id.tvTime);
		title = (TextView) findViewById(R.id.title);
		articleText = (TextView) findViewById(R.id.articleText);
		avatar = (AvatarView) findViewById(R.id.avatar);
		authorName = (TextView) findViewById(R.id.tvAuthor);

		tvTitle.setText(article.getTitle());
		tvTime.setText(new DateFormat().format("yyyy-MM-dd HH:mm", article.getCreateDate()));
		avatar.load(article.getAuthorAvatar());
		title.setText(article.getTitle());
		articleText.setText(article.getText());
		authorName.setText(article.getAuthorName());

	}

}