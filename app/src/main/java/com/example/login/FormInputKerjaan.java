package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FormInputKerjaan extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private KerjaanAdapter mAdapter;
    private EditText PelangganText;
    private EditText NopolText;
    private EditText motorText;
    private EditText catatanText;
    private Button tambahButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kerjaan);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        PelangganText = (EditText) findViewById(R.id.pelangganEditText);
        NopolText = (EditText) findViewById(R.id.nopolEditText);
        motorText = (EditText) findViewById(R.id.merkMEditText);
        catatanText = (EditText) findViewById(R.id.infoEditText);
        tambahButton = (Button) findViewById(R.id.tambahButton);

    tambahButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addItem();
        }
    });
    }
    private void addItem(){
        String pelanggan = PelangganText.getText().toString();
        String nopol = NopolText.getText().toString();
        String motor = motorText.getText().toString();
        String kerusakan = catatanText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(KerjaanContract.EntryKerjaan.COLUMN_PELANGGAN, pelanggan);
        cv.put(KerjaanContract.EntryKerjaan.COLUMN_NOPOL, nopol);
        cv.put(KerjaanContract.EntryKerjaan.COLUMN_MOTOR, motor);
        cv.put(KerjaanContract.EntryKerjaan.COLUMN_KERUSAKAN, kerusakan);

        mDatabase.insert(KerjaanContract.EntryKerjaan.TABLE_NAME,null,cv);
        PelangganText.getText().clear();
        NopolText.getText().clear();
        motorText.getText().clear();
        catatanText.getText().clear();

        startActivity(new Intent(FormInputKerjaan.this, KerjaanActivity.class));





    }
}
