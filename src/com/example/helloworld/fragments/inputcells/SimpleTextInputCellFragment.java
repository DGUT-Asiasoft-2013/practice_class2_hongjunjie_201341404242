package com.example.helloworld.fragments.inputcells;

import java.lang.ref.WeakReference;

import com.example.helloworld.R;
import com.example.helloworld.R.id;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class SimpleTextInputCellFragment extends Fragment{
	
	TextView label;
	EditText edit;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceSrate){
		
		View view = inflater.inflate(R.layout.fragment_inputcell_simpletext,container);
		label=(TextView)view.findViewById(R.id.label);
		edit=(EditText)view.findViewById(R.id.edit);
		return view;
	}
	
	public void setLableText(String labelText){
		label.setText(labelText);
	}
	
	public void setHintText(String setHintText){
		edit.setHint(setHintText);
	}
	
	public String getText(){
		return edit.getText().toString();
	}
	
	public void setIsPassword(boolean setIsPassword){
		if(setIsPassword){
			edit.setInputType(EditorInfo.TYPE_CLASS_TEXT|EditorInfo.TYPE_NUMBER_VARIATION_PASSWORD);
		}else{
			edit.setInputType(EditorInfo.TYPE_CLASS_TEXT);
		}
	}

}