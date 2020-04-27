package com.example.exempleokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResponse = findViewById(R.id.txtResponse);

        Button btnServiceWeb = findViewById(R.id.btnServiceWeb);

        btnServiceWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel du service web via une tache asynchrone (appel d'un thread en tache de fond)
                AsyncCallWS asyncCallWS = new AsyncCallWS();
                asyncCallWS.execute();
            }
        });
    }

    //Notre classe qui fait l'appel en tache de fond
    private class AsyncCallWS extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            callServiceWeb();
            return null;
        }

        @Override
        protected void onPostExecute(String retourServiceWeb) {
            super.onPostExecute(retourServiceWeb);

            txtResponse.setText("retourServiceWeb");
        }
    }

    private String callServiceWeb()
    {
        String url ="http.//www.claudehenry.fr/serviceweb/bonjour";

        //On instancie notre objet OkHttp
        OkHttpClient client = new OkHttpClient();

        //Ce quye nous renverra le service web, qu'on initialise à vide
        String retourServiceWeb = "";

        //On crée la demande à partir de l'url
        Request request = new Request.Builder()
                .url(url)
                .build();

        try
        {
            //On obtient la réponse
                Response response = client.newCall(request).execute();

                if(response.isSuccessful())
                {
                    retourServiceWeb = response.body().string();
                }

        }

        catch (Exception ex)
        {
            retourServiceWeb = ex.getMessage();
        }

        return retourServiceWeb;


    }
}
