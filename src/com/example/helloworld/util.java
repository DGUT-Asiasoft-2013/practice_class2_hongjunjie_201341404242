package com.example.helloworld;


import android.app.Activity;
import android.app.ProgressDialog;

public class util {

	public static ProgressDialog getProgressDialog(Activity act) {
		ProgressDialog progressDialog = new ProgressDialog(act);
		progressDialog.setMessage("«Î…‘∫Û");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		return progressDialog;
	}
}
