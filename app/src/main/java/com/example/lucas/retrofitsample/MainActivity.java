package com.example.lucas.retrofitsample;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.example.lucas.retrofitsample.Controller.UdacityService;
import com.example.lucas.retrofitsample.Model.Instructor;
import com.example.lucas.retrofitsample.Model.UdacityCatalog;
import com.example.lucas.retrofitsample.Model.Course;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG  = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog = service.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG, "Error: " + response.code());
                }else{
                    //Sucesso na requisicao
                    UdacityCatalog catalog = response.body(); //converte objeto em json para a classe

                    for(Course c : catalog.courses){
                        Log.i(TAG, String.format("%s: %s", c.title, c.subtitle));

                        for(Instructor i : c.instructors){
                            Log.i(TAG, String.format(i.name));
                        }
                        Log.i(TAG, "---------------------------------------------");
                    }
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.e(TAG, "ERROR" + t.getMessage());
            }
        });

    }
}
