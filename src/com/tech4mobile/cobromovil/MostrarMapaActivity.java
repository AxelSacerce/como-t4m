package com.tech4mobile.cobromovil;

import com.tech4mobile.cobromovil.fragment.PlacesFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MostrarMapaActivity extends FragmentActivity {
	double dLat;
	double dLong;
	String NombreCliente;
	
	
	// verifica si existe conexion a internet
	 public static boolean verificaConexion(Context ctx) {
		    boolean bConectado = false;
		    ConnectivityManager connec = (ConnectivityManager) ctx
		            .getSystemService(Context.CONNECTIVITY_SERVICE);
		    // No s�lo wifi, tambi�n GPRS
		    NetworkInfo[] redes = connec.getAllNetworkInfo();
		    // este bucle deber�a no ser tan �apa
		    for (int i = 0; i < redes.length; i++) {
		        // �Tenemos conexi�n? ponemos a true
		        if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
		            bConectado = true;
		        }
		    }
		    return bConectado;
		}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_mapa);
		
		if(verificaConexion(this) == false) 
		{
		
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
			
			
			
		}
		else
		{
			Intent iUbicaCliente = getIntent();
			dLat = Double.parseDouble(iUbicaCliente.getStringExtra("Latitud"));
			dLong = Double.parseDouble(iUbicaCliente.getStringExtra("Longitud"));
			//NombreCliente = iUbicaCliente.getStringExtra("NombreC");
			
			Log.i("DATOS RECIBIDOS", "LAT: " + String.valueOf(dLat) + " LONG " + String.valueOf(dLong) );
			//Log.i("DATOS CLIENTE MAPA MOSTRAR", NombreCliente);
			
				PlacesFragment places = new PlacesFragment();
				places.setLocation(dLat, dLong);
				//places.set_NombreC(NombreCliente);
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa_mostrar, menu);
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.mnMapa) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}