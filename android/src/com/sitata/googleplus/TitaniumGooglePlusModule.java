/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package com.sitata.googleplus;

import org.appcelerator.kroll.KrollEventCallback;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import android.app.Activity;
import android.os.Bundle;
import android.content.IntentSender.SendIntentException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.plus.Plus;

import com.google.android.gms.plus.PlusClient;



@Kroll.module(name="TitaniumGooglePlus", id="com.sitata.googleplus")
public class TitaniumGooglePlusModule extends KrollModule implements
	ConnectionCallbacks, OnConnectionFailedListener
{

	// Standard Debugging variables
	private static final String LCAT = "TitaniumGooglePlusModule";
	private static final boolean DBG = TiConfig.LOGD;
	
	private static final int RC_SIGN_IN = 0;
	private String mClientId;
	private Object[] mScopes;
	private KrollEventCallback successCallback;
	private KrollEventCallback errorCallback;
	
	/* Client used to interact with Google APIs. */
	private static GoogleApiClient mGoogleApiClient;
	
	private Boolean mIntentInProgress;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public TitaniumGooglePlusModule()
	{
		super();
		
		mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(Plus.API)
        .addScope(Plus.SCOPE_PLUS_LOGIN)
        .build();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is created
		

	}
	
	@Override
	public void onStart(Activity activity) {
		super.onStart(activity);
		mGoogleApiClient.connect();
	}
	
	@Override
	public void onStop(Activity activity) {
		super.onStop(activity);
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}
	
	
	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// Called when there was an error connecting the client to the service.
	  if (!mIntentInProgress && result.hasResolution()) {
	    try {
	      mIntentInProgress = true;
	      startIntentSenderForResult(result.getResolution().getIntentSender(),
	          RC_SIGN_IN, null, 0, 0, 0);
	    } catch (SendIntentException e) {
	      // The intent was canceled before it was sent.  Return to the default
	      // state and attempt to connect to get an updated ConnectionResult.
	      mIntentInProgress = false;
	      mGoogleApiClient.connect();
	    }
	  }
	}
	
	@Override
	public void onConnected(Bundle connectionHint) {
	  // We've resolved any connection errors.  mGoogleApiClient can be used to
	  // access Google APIs on behalf of the user.
		
		// After calling connect(), this method will be invoked asynchronously 
		// when the connect request has successfully completed.
	}
	
	@Override
	public void onConnectionSuspended(int cause) {
		
	}
	
	
	
	
	
	// Methods
	@Kroll.method
	public void signin(KrollDict props)
	{
		if (props.containsKey("success")) {
			successCallback = (KrollEventCallback) props.get("success");
		}
		if (props.containsKey("error")) {
			errorCallback = (KrollEventCallback) props.get("error");
		}
		
		// TODO
	}
	
	@Kroll.method
	public void signout() {
		// TODO
	}
	
	@Kroll.method
	public void disconnect() {
		// TODO
	}
	
	@Kroll.method
	public Boolean isLoggedIn() {
		return false; // TODO
	}

	// Properties
	@Kroll.getProperty @Kroll.method
	public void setClientId(String value)
	{
		mClientId = value;
	}
	
	@Kroll.getProperty @Kroll.method
	public String getClientId() {
		return mClientId;
	}

	@Kroll.getProperty @Kroll.method
	public void setScopes(Object[] value)
	{
		mScopes = value;
	}
	
	@Kroll.getProperty @Kroll.method
	public Object[] getScopes() {
		return mScopes;
	}

}
