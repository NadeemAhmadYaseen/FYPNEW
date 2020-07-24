package com.example.fypwebhost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     RelativeLayout relativeLayoutsignup,loginlayout, centersignup;
     LinearLayout linearLayoutmain,linearLayoutcontent;
     ImageButton imageButton;
    EditText editTextName, editTextEmail, editTextPassword;
    Button buttonAdd, buttonGO;
    TextView textViewLogin,textViewEmail,textViewUser,textViewPassword,textviewlogin;
    ProgressBar progressBar;
    View view;
    RadioGroup radioGroupType;
    RadioButton radioButtonTeacher, radioButtonStudent;
    int user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////Image button casting/////

        imageButton=(ImageButton)findViewById(R.id.user_profile_photo);

        /////Relative layout casting/////

        relativeLayoutsignup=(RelativeLayout)findViewById(R.id.relativeLayoutsignup);
        loginlayout=(RelativeLayout)findViewById(R.id.loginLayout);
        centersignup=(RelativeLayout)findViewById(R.id.centersignup);

/////Linear layout casting/////
        linearLayoutmain=(LinearLayout) findViewById(R.id.linearlayoutmain);
        linearLayoutcontent=(LinearLayout) findViewById(R.id.linearlayoutcontent);


/////View casting/////
        view = (View) findViewById(R.id.Viewmain);

        /////Radio group  casting/////
        radioGroupType = (RadioGroup)findViewById(R.id.radioGroupType);
        getSupportActionBar().hide();
        radioButtonStudent = (RadioButton)findViewById(R.id.radio_students);
        radioButtonTeacher = (RadioButton)findViewById(R.id.radio_teacher) ;

        /////Progress bar casting/////

        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        /////text View casting/////
        textViewUser = (TextView) findViewById(R.id.TextUsername);
        textViewEmail = (TextView) findViewById(R.id.TextEmail);
        textViewPassword = (TextView) findViewById(R.id.TextPassword);

        textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        /////edit text casting  casting/////
        editTextName =(EditText) findViewById(R.id.txtName);
        editTextEmail =(EditText) findViewById(R.id.txtEmail);
        editTextPassword =(EditText) findViewById(R.id.txtPassword);


        buttonAdd = (Button) findViewById(R.id.btnAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpUser();
            }
        });
    }

    private void signUpUser() {

        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "fill all text" , Toast.LENGTH_SHORT).show();
        }
        else
        {


            StringRequest request = new StringRequest(Request.Method.POST, "https://temp321.000webhostapp.com/connect/Connect.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equalsIgnoreCase("Account Created"))
                            {
                                Toast.makeText(MainActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String > params = new HashMap<String, String>();

                    params.put("Name", name);
                    params.put("Email", email);
                    params.put("Password", password);
                    params.put("userType", String.valueOf(user_type));

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        user_type = -1;

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_teacher:
                if (checked)
                    user_type = 1;
                break;
            case R.id.radio_students:
                if (checked)
                    user_type = 0;
                break;
        }
//        Toast.makeText(MainActivity.this, user_type, Toast.LENGTH_SHORT).show();
    }

}