package com.example.helloworld;


import android.app.Activity;
import android.app.ProgressDialog;

public class util {

	public static ProgressDialog getProgressDialog(Activity act) {
		ProgressDialog progressDialog = new ProgressDialog(act);
		progressDialog.setMessage("���Ժ�");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		return progressDialog;
	}
}
