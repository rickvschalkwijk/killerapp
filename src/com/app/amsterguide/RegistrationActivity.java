package com.app.amsterguide;

import com.app.killerapp.R;

import core.connection.DataException;
import core.connection.https.HttpsConnector;
import core.connection.https.HttpsRequest;
import core.connection.https.HttpsRequestType;
import util.KillerboneUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class RegistrationActivity extends Activity {
	
	final Context context = this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        Button register = (Button) findViewById(R.id.btnRegister);
		register.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText name = (EditText) findViewById(R.id.reg_fullname);
		        String userName = name.getText().toString();
				EditText email = (EditText) findViewById(R.id.reg_email);
		        String userEmail = email.getText().toString();
		        EditText pass0 = (EditText) findViewById(R.id.reg_password);
		        String userPassword0 = pass0.getText().toString();
		        EditText pass1 = (EditText) findViewById(R.id.reg_password2);
		        String userPassword1 = pass1.getText().toString();
		        if(userName.equals(""))
		        	SetMessage("Error", "Please fill in a valid username.");
		        else if(userEmail.equals(""))
		        	SetMessage("Error", "Please fill in a valid email.");
		        else if(userPassword0.equals(""))
		        	SetMessage("Error", "Please fill in a valid password.");
		        else if(!userPassword0.equals(userPassword1))
				{
		        	SetMessage("Error", "Inserted passwords don't match.");
				}
				else if(userPassword0.equals(userPassword1))
				{
					Register(userName, userEmail, userPassword0);
					
				}
			}
		});

    }
    private void SetMessage(String titel, String message)
    {
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(titel);
		alertDialogBuilder
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
    }
    
    private void Register(String name, String email, String password)
    {
    	KillerboneUtils utils = new KillerboneUtils();
    	HttpsRequestType requestType = HttpsRequestType.POST;
    	String requestUrl = utils.postUserCreateUrl();
    	String requestBody = utils.composeUserCreateXml(name, email, password);
    	HttpsRequest request = new HttpsRequest(requestType, requestUrl, requestBody);
    	request.setHeader("Content-Type", "text/xml");
    	HttpsConnector httpsConnector = new HttpsConnector(context.getApplicationContext());
    	try {   		
			httpsConnector.performHttpsRequest(request);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setTitle("Succes");
			alertDialogBuilder
					.setMessage("Account created succesfully! We've send you a activation e-mail")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialog, int id) {
									dialog.cancel();
									Intent i = new Intent(getApplicationContext(),
											PreLoginActivity.class);
									startActivity(i);
								}
							});

			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		} 
    	catch (Exception e) {
			e.printStackTrace();
			SetMessage("Failed", "Failed to create account, You're name or email is already in use");
		}
    }
}