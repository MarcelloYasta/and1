package com.example.quran;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.quran.rest.ApiClient;
        import com.example.quran.rest.ApiInterface;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;
        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;


public class SignUp extends AppCompatActivity {
    @BindView(R.id.username)
    EditText etUsername;

    @BindView(R.id.pw)
    EditText etpassword;

    ApiInterface apiService;
    ProgressDialog pd;

    private static final String TAG = SignUp.class.getSimpleName();


    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();

            }
        });

    }
    //@OnClick(R.id.btnsave)

    public void signup() {
        Log.d(TAG,"Menjalankan method Signup");
        pd = ProgressDialog.show(this,null,"Sedang mendaftarkan account",true,false);
        String strUsername = etUsername.getText().toString();
        String strPassword = etpassword.getText().toString();

        Log.d(TAG,"mendapatkan data dari edit text");

        apiService.signup(strUsername,strPassword).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"berhasil memanggil api");
                if (response.isSuccessful()){
                    pd.dismiss();
                    Log.d(TAG,"succes mendapatkan api");
                    Intent i = new Intent(SignUp.this,LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                pd.dismiss();
                Log.e(TAG,t.getLocalizedMessage());
                Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(SignUp.this,LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }
}