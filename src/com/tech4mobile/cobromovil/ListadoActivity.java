package com.tech4mobile.cobromovil;

import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;



import com.tech4mobile.cobromobil.data.NetworkWs;
import com.tech4mobile.cobromobil.data.itemListBaseAdapter;
import com.tech4mobile.cobromobil.data.itemListDetalle;
import com.zebra.printer.MobilePrinter;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class ListadoActivity extends Fragment {
	
	// Variables ocupadas en toda la clase
	
	ListView ListadoLvl;
	String sIDCont; 
	String sNombre; 
	String sTelefono; 
	String sDireccion;
	
	// Data JSon
	ArrayList<itemListDetalle> lCobros = new ArrayList<itemListDetalle>();
	String sResultado;
	private static final String TAG_DATA = "data";
	
	
	
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ProgressDialog DialogoCargar;
	static MobilePrinter mMobilePrinter; // constante para manejo de imrpesora
	
	
	@SuppressLint("HandlerLeak") private final Handler mbHandler = new Handler() 
	{
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) 
			{					
				
			case MobilePrinter.MESSAGE_DEVICE_SET:
				if (msg.obj == null) {
					Toast.makeText(getActivity().getApplicationContext(), "No hay Impresora para conectarse...", Toast.LENGTH_SHORT).show();
				} else 
				{
					DialogosManager.showBluetoothDialog(getActivity().getApplicationContext(), (Set<BluetoothDevice>) msg.obj);
					Log.i("Conectado en", msg.obj.toString());
					Toast.makeText(getActivity().getApplicationContext(), "Impresora conectada...", Toast.LENGTH_SHORT).show();				
				}
				break;
								
			}
		}
	};
	
	
	//  Inicia Handler para recibir el listado
	
	@SuppressLint("HandlerLeak") public Handler workHnd = new Handler()
	{
				
		
		@SuppressWarnings("null")
		@Override
		public void handleMessage(Message msg) 
		{
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 1)
			{
				DialogoCargar.dismiss();
				//JSONArray ArrayCobros = null;
				//JSONObject jsCobros;
				
				try
				{
					// Si se recibe un JSON con arreglo el cual tiene nombre se recibe como objeto aplican las 
					// variables comentadas arriga  JSONArray, JSONObject
					
					JSONArray ArrCobrosL  = null;
					JSONObject jsCobros;
					
					
					try {
						
						jsCobros = new JSONObject(sResultado);
						ArrCobrosL = jsCobros.getJSONArray(TAG_DATA);
						Log.i("TAG_Datos", ArrCobrosL.toString());
					
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//JSONObject jsCobros = new JSONObject(sResultado);					
					
					//JSONArray  ArrayListCobros = new JSONArray(sResultado);
					
								
					if(ArrCobrosL == null)
					{
						Toast.makeText(getActivity(), "¡No hay Cobros disponibles para este día!", Toast.LENGTH_SHORT).show();
						
						//System.exit(0);
						
					}else
					{
					
						
						itemListDetalle itemDetails;
						for(int i = 0; i < ArrCobrosL.length(); i++)
						{
									JSONObject CobrosL = ArrCobrosL.getJSONObject(i);
									itemDetails = new itemListDetalle();
									//itemDetails.set_Orden(CobrosL.getString("ORDEN"));
									itemDetails.setid_Contrato(CobrosL.getString("id"));
									itemDetails.setNombre(CobrosL.getString("nombres") + " " +  CobrosL.getString("apellidos"));
									itemDetails.setTelefono(CobrosL.getString("telefonos"));
									itemDetails.setDireccion(CobrosL.getString("domicilio"));
									
									lCobros.add(itemDetails);
									
									//Log.i("LISTADO", lCobros.toString());
									//lCobros.add(arrayCobros.get(i).toString());
														
						}
						Toast.makeText(getActivity().getApplicationContext(), "Lista Cargada exitosamente...", Toast.LENGTH_SHORT).show();
					}
				}
				catch (JSONException e)
				{
					e.printStackTrace();
					
				}
								
				ListadoLvl.setAdapter(new itemListBaseAdapter(getActivity(), lCobros));	
				registerForContextMenu(ListadoLvl);
			}
							
		}
		
	};
	
	
	
	// Finaliza Handler para recibir el listado
	
	
	
	
	

	public ListadoActivity() {
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
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Comprueba existencia de conexión
		if(verificaConexion(getActivity()) == false) {
        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(getResources().getString(R.string.lblAccion).toString());
			builder.setMessage(getResources().getString(R.string.lblSeguroExit).toString());
			builder.setPositiveButton(getResources().getString(R.string.lblSi).toString(), new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int which) {
			    	
			    	System.exit(0);
			    				        
			    }
			
			});
			
			AlertDialog alert = builder.create();
			alert.show();
			
        }else
        {
        	
        	
        	mMobilePrinter = new MobilePrinter(getActivity().getApplicationContext(), mbHandler, null); //instacion la clase MobilePriter
        	
        	DialogoCargar = new ProgressDialog(getActivity());
			DialogoCargar.setMessage("Cargando Listado...");
			DialogoCargar.setCancelable(false);
			DialogoCargar.show();
        	
        	
        	
        	
        }
		
		
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		View view = inflater.inflate(R.layout.fragment_listado, container, false);
		ItemListener ItemList = new ItemListener();
		
		ListadoLvl = (ListView) view.findViewById(R.id.lstvCommun);
		ListadoLvl.setOnItemClickListener(ItemList);
		
		
		
		new Thread()
		{

			@Override
			public void run() {
				 
				super.run();
				sResultado = NetworkWs.ListadoPendientes();
				workHnd.sendEmptyMessage(1);
			}
			
			
		}.start();
		
		
				
		return view;
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
	
	
	class ItemListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
		{
			Object o = ListadoLvl.getItemAtPosition(position);
			
			itemListDetalle obj_itemDetails = (itemListDetalle)o;
			
			sIDCont = obj_itemDetails.getid_Contrato(); 	// obtiene el iD del contrato
			sNombre = obj_itemDetails.getNombre(); 			// Obtiene el nombre que aparece en el contrato 
			sTelefono = obj_itemDetails.getTelefono(); 		// Obtiene el teléfono que aparece en el contrato
			sDireccion =obj_itemDetails.getDireccion();	 	// Obtiene la dirección que aparece en el contrato		
			
			System.out.println(position + "-----texto");
			
			//Intent iCobros;	
			//iCobros = new Intent(getActivity().getApplicationContext(),DetalleActivityFragment.class);
			//iCobros.putExtra("Id", "30");
			//iCobros.putExtra("Action", "2");
			//iCobros.putExtra("CuotaId", sID);
			//iCobros.putExtra("Filter", Filtro);
			//startActivity(iCobros);
			
			Toast.makeText(getActivity(), "Contrato: " + sIDCont + " Nombre: " + sNombre + " Posición: " + position, Toast.LENGTH_SHORT).show();
			Log.i("SELECT DE LA LISTA ", sIDCont.toString()+ sNombre.toString() + sTelefono.toString() );
			mMobilePrinter.findBluetoothPrinters();   //Busca conexion a impresora
			
		}
		
	}
	

}
