package com.tech4mobile.cobromovil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class SplashActivity extends Activity 
{
	
	/* Creado por Axel Sarceño Para Tech4Mobile Octubre 2015
	 */
	
	public void onAttachedToWindow() 
	{
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	
	private static String TAG = SplashActivity.class.getName();
	private static long SLEEP_TIME = 5;
	

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        StartAnimations();
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
        
    }
    
	private void StartAnimations() {
	    	
	    	Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
	    	anim.reset();
	    	LinearLayout l=(LinearLayout) findViewById(R.id.la_Main);
	    	l.clearAnimation();
	    	l.startAnimation(anim);
	    	
	    	anim = AnimationUtils.loadAnimation(this, R.anim.translate);
	    	anim.reset();
	    	ImageView iv = (ImageView) findViewById(R.id.logo);
	    	iv.clearAnimation();
	    	iv.startAnimation(anim);
	    	
	    }
	
	private class IntentLauncher extends Thread {
	    	
	    	@Override
	    	public void run() {
	    		try {
	    			
	    			Thread.sleep(SLEEP_TIME*1000);
	    			
	    		} catch (Exception e) {
	    			
	    			Log.e(TAG, e.getMessage());
	    			
	    		}
	    		
	    		Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
	    		SplashActivity.this.startActivity(intent);
	    		SplashActivity.this.finish();
	    	}
	    }
	        
	}

