package solutionz.professionhub.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import solutionz.professionhub.R;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    boolean loginState = true;
    Button btnLogIn;
    Button btnSignUp;
    EditText etUserName;
    EditText etPassword;
    TextView btnforgotpasssword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
    }

    public void setViews() {
        btnLogIn = (Button) findViewById(R.id.btn_Login);
        btnSignUp = (Button) findViewById(R.id.btn_Signup);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);

        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        /*btnforgotpasssword.setOnClickListener(this);
*/
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_Login:
                if (checkes())
                {
                    /*loginState=false;
                    String userName = etUserName.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();

                   // SharedPreferences sharedPreference = getDefaultSharedPreferences(getApplicationContext());

                    SharedPreferences sharedPreferences = getSharedPreferences("ss",MODE_PRIVATE);

                    SharedPreferences.Editor e = sharedPreferences.edit();
                    e.putString("name",userName);
                    e.putBoolean("state",loginState);
                    e.apply();
                    Intent i = new Intent(ActivityLogin.this,ActivityShowWorkerList.class);
                    startActivity(i);*/

                    Toast.makeText(getApplicationContext(),"user login successfully",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(ActivityLogin.this,ActivityShowWorkerList.class);
                    startActivity(i);
                }
                else {

                }
                break;

            case R.id.btn_Signup:

                break;

            case R.id.et_username:

                break;
            case R.id.et_password:

                break;


        }

    }

    public boolean checkes()
    {
        String getUserName = etUserName.getText().toString();
        String getPassword = etPassword.getText().toString();
        if (getUserName.isEmpty())
        {
            etUserName.setHint("User Name required");
            etUserName.setHintTextColor(Color.RED);
            return false;
        }
        if (getPassword.isEmpty())
        {
            etPassword.setHint("Password required");
            etPassword.setHintTextColor(Color.RED);
            return false;
        }
        if (getPassword.length()<5)
        {
            etPassword.setHint("Password must at least 6 digit");

            return false;
        }

        return true;
    }
}
