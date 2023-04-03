package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SignalStrengthUpdateRequest;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quran.model.user;
import com.example.quran.response.ResponseLogin;
import com.example.quran.rest.ApiClient;
import com.example.quran.rest.ApiInterface;
import com.example.quran.utils.SessionManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn2)
    Button login;

    @BindView(R.id.username)
    EditText etusername;

    @BindView(R.id.pw)
    EditText etpassword;

    @BindView(R.id.btn3)
    Button signup;

    ApiInterface apiservice;
    SessionManager sessionManager;

    private static final String TAG ="LoginActivity";


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.login);
        ButterKnife.bind(this);

        apiservice = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user();
            }
        });

    }

    //handle pengubahan bahasa


    private void register_user() {
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
    }

    private void loginUser() {
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        apiservice.login(username,password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful())
                {
                    user userLoggedIn = response.body().getuser();
                    sessionManager.createLoginSession(userLoggedIn);

                    Intent intent = new Intent(LoginActivity.this,ActivityMain.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }
                else if (!response.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"User tidak ditemukan",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e(TAG,"onFailure:"+t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,"Gagal terhubung ke server",Toast.LENGTH_SHORT).show();


            }
        });
    }
}