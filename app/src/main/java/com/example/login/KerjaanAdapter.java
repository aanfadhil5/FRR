package com.example.login;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KerjaanAdapter extends RecyclerView.Adapter<KerjaanAdapter.KerjaanViewHolder> {

    private Context mContext;
    private Cursor mCursor;


    public KerjaanAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class KerjaanViewHolder extends RecyclerView.ViewHolder{
        public TextView nopolText;
        public TextView motorText;
        public TextView kerusakanText;

        public KerjaanViewHolder(@NonNull View itemView) {
            super(itemView);

            nopolText = itemView.findViewById(R.id.textview_nopol_item);
            motorText = itemView.findViewById(R.id.textview_motor_item);
            kerusakanText = itemView.findViewById(R.id.textview_kerusakan_item);

        }
    }

    @NonNull
    @Override
    public KerjaanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_kerjaan_layout, parent, false);
        return new KerjaanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KerjaanViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        String nopol = mCursor.getString(mCursor.getColumnIndex(KerjaanContract.EntryKerjaan.COLUMN_NOPOL));
        String motor = mCursor.getString(mCursor.getColumnIndex(KerjaanContract.EntryKerjaan.COLUMN_MOTOR));
        String kerusakan = mCursor.getString(mCursor.getColumnIndex(KerjaanContract.EntryKerjaan.COLUMN_KERUSAKAN));
        long id = mCursor.getLong(mCursor.getColumnIndex(KerjaanContract.EntryKerjaan._ID));

        holder.nopolText.setText(nopol);
        holder.motorText.setText(motor);
        holder.kerusakanText.setText(kerusakan);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
