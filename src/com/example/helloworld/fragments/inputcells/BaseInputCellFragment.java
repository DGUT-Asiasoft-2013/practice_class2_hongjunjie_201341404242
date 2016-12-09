package com.example.helloworld.fragments.inputcells;

import android.app.Fragment;

public abstract class BaseInputCellFragment extends Fragment {
	public abstract void setLabel(String label);
	
	public abstract void setInputHint(String hint);
}
