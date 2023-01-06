package uk.ac.tees.b1325384.easyfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import uk.ac.tees.b1325384.easyfood.Common.Common;
import uk.ac.tees.b1325384.easyfood.Model.User;

public class MainActivity extends AppCompatActivity {


    AuthViewState viewState = AuthViewState.DEFAULT;
    TextView edtPhone;
    TextView edtPassword;
    EditText edtSignUpPhone, edtSignUpName, edtSignUpPassword;
    Button btnLaunchSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout signUpLayout = (LinearLayout) this.findViewById(R.id.signUpLayout);
        LinearLayout signInLayout = (LinearLayout) this.findViewById(R.id.signInLayout);

        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        Button btnGoBack = (Button) findViewById(R.id.btnGoBack);
        Button btnLaunchSignIn = (Button) findViewById(R.id.btnLaunchSignIn);

        edtSignUpName = (EditText) signUpLayout.findViewById(R.id.edtSignUpName);
        edtSignUpPhone = (EditText) signUpLayout.findViewById(R.id.edtSignUpPhone);
        edtSignUpPassword = (EditText) signUpLayout.findViewById(R.id.edtSignUpPassword);
        btnLaunchSignUp = (Button) signUpLayout.findViewById(R.id.btnLaunchSignUp);




        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        if (Common.currentUser != null) {
            //Navigate to homescreen
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent SignUp = new Intent(MainActivity.this, uk.ac.tees.b1325384.easyfood.SignUp.class);
//                startActivity(SignUp);
                signUpLayout.setVisibility(View.VISIBLE);
                signInLayout.setVisibility(View.GONE);
                viewState = AuthViewState.SIGNUP_VISIBLE;
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent SignIn = new Intent(MainActivity.this, uk.ac.tees.b1325384.easyfood.SignIn.class);
//                startActivity(SignIn);
                signUpLayout.setVisibility(View.GONE);
                signInLayout.setVisibility(View.VISIBLE);
                viewState = AuthViewState.SIGNIN_VISIBLE;
            }
        });

        edtPassword = (TextView) signInLayout.findViewById(R.id.edtPassword);
        edtPhone = (TextView) signInLayout.findViewById(R.id.edtPhone);

        //initiating firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        //sign in
        btnLaunchSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //This is to check if user exists in Database
                        // DataSnapshot dataSnapshot = null;
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {

                            //Get User information
                            mDialog.dismiss();
                            //dataSnapshot = null;
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString());//Here we set Phone
                            if (user != null) {
                                if (user.getPassword().equals(edtPassword.getText().toString())) {
                                    //Toast.makeText(SignIn.this, "Sign in successful !", Toast.LENGTH_SHORT).show();
                                    {
                                        Intent homeIntent = new Intent(MainActivity.this, HomeScreen.class);
                                        Common.currentUser = user;
                                        startActivity(homeIntent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "Wrong Password !!!", Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        //sign up
        btnLaunchSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //This will now check if the user trying to sign up already exists on the database
                        //using their provided phone number
                        if (snapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Phone Number Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            mDialog.dismiss();
                            User user = new User(edtSignUpName.getText().toString(), edtSignUpPassword.getText().toString(),edtSignUpPhone.getText().toString());
                            table_user.child(edtSignUpPhone.getText().toString()).setValue(user);
                            Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(MainActivity.this, HomeScreen.class);
                            Common.currentUser = user;
                            startActivity(homeIntent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        btnGoBack.setOnClickListener(v -> {
            if (viewState == AuthViewState.SIGNUP_VISIBLE) {
                signUpLayout.setVisibility(View.GONE);
            }
            if (viewState == AuthViewState.SIGNIN_VISIBLE) {
                signInLayout.setVisibility(View.GONE);

            }
        });
    }
}

enum AuthViewState {
    SIGNUP_VISIBLE, SIGNIN_VISIBLE, DEFAULT
}