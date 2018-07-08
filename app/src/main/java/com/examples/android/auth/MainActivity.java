package com.examples.android.auth;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //Login screen



    // to declare variable of firebaseAuth
    FirebaseAuth mAuth;

    //to declare variables for email and password
    EditText edittextEmail, edittextPassword;

    //to declare variable for progress bar
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);


        // to get instance for firebaseAuth
        mAuth = FirebaseAuth.getInstance ();

        // to find textView Signup
        findViewById (R.id.textViewSignup).setOnClickListener (this);

        // to find login Button
        findViewById (R.id.buttonLogin).setOnClickListener (this);

        //for email address
        edittextEmail = (EditText) findViewById (R.id.editTextEmail);

        //for password
        edittextPassword = (EditText) findViewById (R.id.editTextPassword);

        //for progressBar
        progressBar =  (ProgressBar) findViewById (R.id.progressbar);

    }

    //method for user Login
    private void userlogin()
    {
        // to get email from user and store it in variable called email
        String email = edittextEmail.getText ().toString ().trim ();

        // to get Password from user and store it in variable called Password
        String PassWord = edittextPassword.getText ().toString ().trim ();

        //to check editText of email should not be empty
        //if it is empty then
        if(email.isEmpty ())
        {
            //set an error
            edittextEmail.setError ("Email is required");
            //and highlight that box
            edittextEmail.requestFocus ();
            return;
        }

        //to match the pattern of email address
        //if it not matches then
        if (!Patterns.EMAIL_ADDRESS.matcher (email).matches ())
        {
            //set an error
            edittextEmail.setError ("Please enter a valid email address");
            // and highlight that box
            edittextEmail.requestFocus ();
            return;
        }


        //to check editText of password that should not be empty
        //if it is empty then

        if(PassWord.isEmpty ())
        {
            //set an error
            edittextPassword.setError ("Password is required");
            //it will focus on password
            edittextPassword.requestFocus ();
            return;
        }

        //length of password should be under six characters
        if(PassWord.length ()<6)
        {
            edittextPassword.setError ("Minimum length of password required is 6");
            edittextPassword.requestFocus ();
            return;
        }

        //for login
        progressBar.setVisibility (View.VISIBLE);

        //for sign in with valid email address and Password
        mAuth.signInWithEmailAndPassword (email,PassWord).addOnCompleteListener (new OnCompleteListener<AuthResult> ( ) {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility (View.GONE);
                if (task.isSuccessful ())
                {
                    //if login is successful then

                    //Intent intent = new Intent (MainActivity.this, ProfileMain3Activity.class);
                    //intent .addFlags (intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //startActivity (intent);
                    Toast.makeText (getApplicationContext (),"log in",Toast.LENGTH_SHORT).show ();

                }else
                {
                    //else
                    Toast.makeText (getApplicationContext (), task.getException ().getMessage (),Toast.LENGTH_SHORT).show ();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.textViewSignup:

                startActivity (new Intent (this, Main2Activity.class));

                break;
            case R.id.buttonLogin:
                userlogin();
                break;

        }

    }
}
