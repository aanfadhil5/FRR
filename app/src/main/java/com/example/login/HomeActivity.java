package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView kerjaanCard, sparepartCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        kerjaanCard = (CardView) findViewById(R.id.kerjaanCardID);
        sparepartCard = (CardView) findViewById(R.id.sparepartCardID);

        kerjaanCard.setOnClickListener(this);
        sparepartCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.kerjaanCardID : i = new Intent(this,KerjaanActivity.class); startActivity(i); break;
            case R.id.sparepartCardID : i = new Intent(this, sparepartActivity.class);startActivity(i); break;
        }
    }
}
