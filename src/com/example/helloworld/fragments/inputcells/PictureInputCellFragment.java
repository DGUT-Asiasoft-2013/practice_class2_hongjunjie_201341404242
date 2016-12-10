package com.example.helloworld.fragments.inputcells;

import com.example.helloworld.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PictureInputCellFragment extends Fragment {
	final int REQUESTCODE_CAMERA = 1;
	final int REQUESTCODE_ALBUM = 2;

	ImageView imageView;
	TextView labelText;
	TextView hintText;
	private byte[] pngData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_inputcell_picture, container);

		imageView = (ImageView) view.findViewById(R.id.image);
		labelText = (TextView) view.findViewById(R.id.label);
		hintText = (TextView) view.findViewById(R.id.hint);

		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onImageViewClicked();
			}
		});
		return view;
	}

	void onImageViewClicked() {
		String[] items = { "≈ƒ’’", "œ‡≤·" };

		new AlertDialog.Builder(getActivity()).setTitle(labelText.getText())
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case 0:
							takephoto();
							break;

						case 1:
							pickFromAlbum();
							break;

						default:
							break;

						}

					}
				})

				.show();
	}

	void takephoto() {
		Intent itnt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(itnt, REQUESTCODE_CAMERA);
	}

	private void setPngData(Bitmap bmp) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		pngData = stream.toByteArray();
	}

	public byte[] getPngData() {
		return pngData;
	}

	void pickFromAlbum() {
		Intent itnt = new Intent(Intent.ACTION_PICK);
		itnt.setType("image/*");
		startActivityForResult(itnt, REQUESTCODE_ALBUM);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Activity.RESULT_CANCELED)
			return;
		if (requestCode == REQUESTCODE_CAMERA) {
			Bitmap bmp = (Bitmap) data.getExtras().get("data");
			setPngData(bmp);
			imageView.setImageBitmap(bmp);
		} else if (requestCode == REQUESTCODE_ALBUM) {
			Bitmap bmp;
			try {
				bmp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
				setPngData(bmp);
				imageView.setImageBitmap(bmp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setLabelText(String labelText) {
		this.labelText.setText(labelText);
	}

	public void setHintText(String hintText) {
		this.hintText.setText(hintText);
	}

}
