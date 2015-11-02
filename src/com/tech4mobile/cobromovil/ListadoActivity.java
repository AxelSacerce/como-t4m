package com.tech4mobile.cobromovil;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zebra.printer.MobilePrinter;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class ListadoActivity extends Fragment {
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	static MobilePrinter mMobilePrinter; // constante para manejo de imrpesora

	public ListadoActivity() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_listado, container, false);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	public static ListadoActivity newInstance(int sectionNumber)
	{
		ListadoActivity frag = new ListadoActivity();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		frag.setArguments(args);
		
		return frag;
		
		
	}

}
