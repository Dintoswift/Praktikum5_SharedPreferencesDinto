package com.example.praktikum5_sharedpreferencesdinto;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	private EditText mEmailView;
	private EditText mPasswordView;
	
	private CheckBox checkIngatkanSaya;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        mEmailView = (EditText)findViewById (R.id.email);
        EditText mPasswordView = (EditText) findViewById (R.id.password);
        mPasswordView.setOnClickListener(new TextView.OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				int id;
				if (id == R.id.login || id == EditorInfo.IME_NULL){
					attemptLogin();
					return true;
				}
				// TODO Auto-generated method stub
				return false;
			}
		});
        
        checkIngatkanSaya = (CheckBox) findViewById(R.id.checkIngatkanSaya);
        if (!new PrefManager(this).isUserLogedOut()){
        	startHomeActivity();
        }
    }
    
    private void attemptLogin(){
    	mEmailView.setError(null);
    	mPasswordView.setError(null);
    	
    	String email = mEmailView.getText().toString();
    	String password = mPasswordView.getText().toString();
    	
    	boolean cancel = false;
    	View focusView = null;
    	
    	if (!TextUtils.isEmpty(password)&&!isPasswordValid(password)){
    		mPasswordView.setError(getStrinf(R.string.error_invalid_password));
    		focusView = mPasswordView;
    		cancel = true;
    	}
    	
    	if (TextUtils.isEmpty(email)){
    		mEmailView.setError(getString(R.string.error_fie;d_required));
    		focusView = mEmailView;
    		cancel = true;
    	}else if (!isEmailValid(email)){
    		mEmailView.setError(getStrinf(R.string.error_invalid_email));
    		focusView = mEmailView;
    		cancel = true;
    	}
    	if (cancel){
    		focusView.requestFocus();
    	}else{
    		if(checkIngatkanSaya.isChecked())
    			saveLoginDetails(email,password);
    		startHomeActivity();
    	} 	
    }
    
    private void startHomeActivity(){
    	Intent intent = new Intent(this, homeactivity.class);
    	startActivity(intent);
    	finish();
    }
    private void saveLoginDetails(String email, String password) {
        new PrefManager(this).saveLoginDetails(email, password);
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    protected void attemptLogin() {
		// TODO Auto-generated method stub
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
}
