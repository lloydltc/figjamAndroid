package com.example.figjam.Views.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "GoogleActivity";

    private static final int RC_SIGN_IN = 9001;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private TextView mPleaseWait,txtvTologin;
    private CardView mLoadingCardView;
    private boolean isAllFieldsChecked;

    private TextInputEditText txtfemail;
    private TextInputEditText txtpass;
    private TextInputEditText txtcfpass;
    private ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    private Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnreg = findViewById(R.id.register);
        progressBar = findViewById(R.id.rprogressBar);

        mAuth = FirebaseAuth.getInstance();
        mPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
        mLoadingCardView = (CardView) findViewById(R.id.card_view_loading);
        txtfemail = findViewById(R.id.txtemail);
        txtpass = findViewById(R.id.txtpassword);
        txtcfpass = findViewById(R.id.txtcfpassword);
        txtvTologin = findViewById(R.id.txtvlog);



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
        txtvTologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));
            }
        });
        btnreg.setOnClickListener(new View.OnClickListener() {
            private static final String TAG = "error";



            @Override
            public void onClick(final View view) {
                String email = txtfemail.getText().toString().trim();
                String  pass = txtpass.getText().toString().trim();
                String cfpass = txtcfpass.getText().toString().trim();


                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass) || !TextUtils.isEmpty(cfpass)) {

                    isAllFieldsChecked = CheckAllFields();
                    if (isAllFieldsChecked) {
                        if (pass.equals(cfpass)) {



                            progressBar.setVisibility(View.VISIBLE);
                            mPleaseWait.setVisibility(View.VISIBLE);
                            mLoadingCardView.setVisibility(View.VISIBLE);

                            mAuth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                Log.w(TAG, "signInWithEmail:failed", task.getException());

                                                Toast.makeText(getApplicationContext(), "Confirm ", Toast.LENGTH_LONG).show();

                                                sendtoMain();
                                            } else {
                                                String error = task.getException().getMessage();
                                                Toast.makeText(getApplicationContext(), "Error :" + error, Toast.LENGTH_LONG).show();
                                            }

                                            progressBar.setVisibility(View.INVISIBLE);
                                            mPleaseWait.setVisibility(View.INVISIBLE);
                                            mLoadingCardView.setVisibility(View.INVISIBLE);
                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Confirm password and password field doesn't Match", Toast.LENGTH_LONG).show();

                        }


                    }}

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
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
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
                            Toast.makeText(RegisterActivity.this, "Authentication done.",
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
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null){
            sendtoMain();

        }

    }

    private void sendtoMain(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
    private boolean CheckAllFields() {

        if (txtpass.length() == 0) {
            txtpass.setError("Password is required");
            return false;
        } else if (txtpass.length() < 6) {
            txtpass.setError("Password must be minimum 8 characters");
            return false;
        }

        // after all validation return true.
        return true;
    }
}