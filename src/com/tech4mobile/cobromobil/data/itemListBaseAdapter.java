package com.tech4mobile.cobromobil.data;

import java.util.ArrayList;
import com.tech4mobile.cobromovil.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams") 
public class itemListBaseAdapter extends BaseAdapter 
{
	
	public static ArrayList<itemListDetalle> itemDetailsArrayList;
	
	
	private LayoutInflater l_Inflater;
	
	// Crea el contexto del adapter
	public itemListBaseAdapter(Context ctx, ArrayList<itemListDetalle> results)
	{
		itemDetailsArrayList = results;
		l_Inflater = LayoutInflater.from(ctx);
		//getFilter();
		
	}	

	public int getCount() 
	{
		
		return itemDetailsArrayList.size();
	}

	public Object getItem(int position) 
	{
		return itemDetailsArrayList.get(position);
	}

	public long getItemId(int position) {
		
		return position;
	}
	
	static class ViewHolder
	{
		TextView cobros_linea_l0;
		TextView cobros_linea_l1;		
		TextView cobros_linea_l2;
		TextView cobros_linea_l3;
		TextView cobros_linea_l4;
		
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if(convertView == null)
		{
			
			convertView = l_Inflater.inflate(R.layout.listado_detalle,null);
			
			holder = new ViewHolder();
			holder.cobros_linea_l0 = (TextView) convertView.findViewById(R.id.cobro_l0);
			holder.cobros_linea_l1 = (TextView) convertView.findViewById(R.id.cobro_l1);
			holder.cobros_linea_l2 = (TextView) convertView.findViewById(R.id.cobro_l2);
			holder.cobros_linea_l3 = (TextView) convertView.findViewById(R.id.cobro_l3);
			holder.cobros_linea_l4 = (TextView) convertView.findViewById(R.id.cobro_l4);
			//holder.ImagenCobrosIcono = (ImageView) convertView.findViewById(R.id.imagenCobro);
			convertView.setTag(holder);
		}else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		// Obtengo la numercion de cada posicion + 1, por ser vector el list se suma 1 para que la 
		// numerion con mience en 1 de lo contrario comineza en 0
		//holder.cobros_linea_l0.setText(itemDetailsArrayList.get(position).get_orden());
		
		holder.cobros_linea_l1.setText("Contrato: " + itemDetailsArrayList.get(position).getid_Contrato());
		holder.cobros_linea_l2.setText("Nombre: " + itemDetailsArrayList.get(position).getNombre());
		holder.cobros_linea_l3.setText("Teléfono: " + itemDetailsArrayList.get(position).getTelefono());
		holder.cobros_linea_l4.setText("Direccion: " + itemDetailsArrayList.get(position).getDireccion());
		//holder.ImagenCobrosIcono.setImageResource(R.drawable.cobros);
				
		return convertView;
	}
	
	
		
}