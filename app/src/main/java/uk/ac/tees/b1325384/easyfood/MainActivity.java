package uk.ac.tees.b1325384.easyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUp = new Intent(MainActivity.this, uk.ac.tees.b1325384.easyfood.SignUp.class);
                startActivity(SignUp);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn = new Intent(MainActivity.this, uk.ac.tees.b1325384.easyfood.SignIn.class);
                startActivity(SignIn);
            }
        });
    }
}