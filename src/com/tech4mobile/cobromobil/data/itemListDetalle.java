package com.tech4mobile.cobromobil.data;

public class itemListDetalle {
	
	String sOrden;
	String sID_Contrato; // esto es igual al id que viene del JSon
    String sNombre;   
    String sTelefono;
    String sDireccion;
    
    //int iPosicion;
    
    
    
    // Getters    
    public String get_orden()
    {
    	return sOrden;
    }
    
    public String getid_Contrato()
    {
        return sID_Contrato;
    }
     
    
    public String getNombre()
    {
        return sNombre;
    } 
    
    public String getTelefono()
    {
        return sTelefono;
    }
    
    public String getDireccion()
    {
        return sDireccion;
    }
    
      
    
    
    // Setters    
    
    public void set_Orden(String data)
    {
    	this.sOrden = data;
    }
    
    public void setid_Contrato(String data)
    {
        this.sID_Contrato = data;
    }
       
    public void setNombre(String data)
    {
    	this.sNombre = data;
    }
    
    public void setTelefono(String data)
    {
    	this.sTelefono = data;
    }   
        
    public void setDireccion(String data)
    {
        this.sDireccion = data;
    }
    
    
   
   
}