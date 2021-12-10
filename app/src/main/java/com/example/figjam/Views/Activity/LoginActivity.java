package com.example.figjam.Views.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.figjam.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "GoogleActivity";

    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;

    private TextInputEditText txtemail;
    private TextInputEditText txtpass;
    private Button btnlog;

    private ProgressBar progressBar;
    private TextView mPleaseWait, mBtn_signup;
    private CardView mLoadingCardView;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpassword);
        btnlog =  findViewById(R.id.login);
        progressBar = findViewById(R.id.register_progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
        mLoadingCardView = (CardView) findViewById(R.id.card_view_loading);
        mBtn_signup = (TextView) findViewById(R.id.btn_signup);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
        mBtn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String loginEmail = loginEmailtext.getText().toString();
//                String loginpass = loginPasswordtext.getText().toString();
                String email = txtemail.getText().toString();
                String  pass = txtpass.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)){
                    progressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);
                    mLoadingCardView.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }else {
                                String error = task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),"Error :" + error,Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                mPleaseWait.setVisibility(View.INVISIBLE);
                                mLoadingCardView.setVisibility(View.INVISIBLE);
                            }
                        }
                    });



                }

            }
        });
    }

    ActivityResultLauncher<Intent> googleActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
//                        Intent data = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        try {
                            // Google Sign In was successful, authenticate with Firebase
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                            firebaseAuthWithGoogle(account.getIdToken());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } catch (ApiException e) {
                            // Google Sign In failed, update UI appropriately
                            Log.w(TAG, "Google sign in failed", e);
                        }
                    }
                }
            });




    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication done.",
                            Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
       googleActivityResultLauncher.launch(signInIntent);

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }

    }
}