package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class KerjaanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private SQLiteDatabase mDatabase;
    private KerjaanAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerjaan);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new KerjaanAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem ((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KerjaanActivity.this, FormInputKerjaan.class));
            }
        });

        mAdapter.swapCursor(getAllItems());
    }

    private void removeItem(long id) {
        mDatabase.delete(KerjaanContract.EntryKerjaan.TABLE_NAME,
                KerjaanContract.EntryKerjaan._ID + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return mDatabase.query(KerjaanContract.EntryKerjaan.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                KerjaanContract.EntryKerjaan.COLUMN_TIMESTAMP + " DESC"
        );
    }

}
