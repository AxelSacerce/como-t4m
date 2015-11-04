package com.tech4mobile.cobromovil;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tech4mobile.cobromovil.R;
import com.zebra.printer.MobilePrinter;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class DetalleActivityFragment extends Fragment{
	
	// Declaración de variables
	ImageView mapa;
	GPSTracker gps;
	private static final String ARG_SECTION_NUMBER = "section_number";
	
	static MobilePrinter mMobilePrinter; // constante para manejo de imrpesora
	
	
	

	public DetalleActivityFragment() {
		// Required empty public constructor
	}
	

	public static boolean verificaConexion(Context ctx) {
	    boolean bConectado = false;
	    ConnectivityManager connec = (ConnectivityManager) ctx
	            .getSystemService(Context.CONNECTIVITY_SERVICE);
	    // No sólo wifi, también GPRS
	    NetworkInfo[] redes = connec.getAllNetworkInfo();
	    // este bucle debería no ser tan ñapa
	    for (int i = 0; i < redes.length; i++) {
	        // ¿Tenemos conexión? ponemos a true
	        if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
	            bConectado = true;
	        }
	    }
	    return bConectado;
	}
	
	
	
	/* Creamos los elementos de la actividad*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
				
		if(verificaConexion(getActivity()) == false) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(getResources().getString(R.string.lblAccion).toString());
			builder.setMessage(getResources().getString(R.string.lblSeguroExit).toString());
			builder.setPositiveButton(getResources().getString(R.string.lblSi).toString(), new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int which) {
			    	
			    	System.exit(0);
			    				        
			    }
			
			});
			
			/*builder.setNegativeButton(getResources().getString(R.string.lblNo).toString(), new DialogInterface.OnClickListener() {

			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	
			        dialog.cancel();
			    }
			});*/			


			AlertDialog alert = builder.create();
			alert.show();
        }else
        {
        	
        	gps = new GPSTracker(getActivity());
        	
        }
		
			
			
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_detalle_activity, container, false);
		
			mapa = (ImageView) view.findViewById(R.id.imgTitleMapDetalle);
			mapa.setOnClickListener(new OnClickListener()
			{				
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int id = v.getId();
					
					if(id == R.id.imgTitleMapDetalle)
					{
						double latitud;
						double longitud;
						latitud = gps.getLatitude();
			            longitud = gps.getLongitude();
			            
			            Intent iMapa = new Intent(getActivity(), MostrarMapaActivity.class);
			            iMapa.putExtra("Latitud", String.valueOf(latitud));
			            iMapa.putExtra("Longitud", String.valueOf(longitud));
			            startActivity(iMapa);
						
					}
					
				}
			});				
		return view;
    }
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	public static DetalleActivityFragment newInstance(int sectionNumber)
	{
		DetalleActivityFragment frag = new DetalleActivityFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		frag.setArguments(args);
		
		return frag;
		
		
	}


	public LocationManager getSystemService(String locationService) {
		// TODO Auto-generated method stub
		return null;
	}

}