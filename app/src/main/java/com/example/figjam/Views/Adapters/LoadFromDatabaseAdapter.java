package com.example.figjam.Views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.figjam.Models.TypecodeUsers;
import com.example.figjam.Models.TypecodeUsersModel;
import com.example.figjam.R;
import com.example.figjam.Views.Activity.LoadFromDatabaseActivity;

import java.util.ArrayList;

public class LoadFromDatabaseAdapter extends RecyclerView.Adapter<LoadFromDatabaseAdapter.LoadFromDbViewHolder>{
    Context context;
    ArrayList<TypecodeUsersModel> typecodeUserModelList;
    public LoadFromDatabaseAdapter(Context context, ArrayList<TypecodeUsersModel> typecodeUserModelList) {
        this.context = context;
        this.typecodeUserModelList = typecodeUserModelList;
    }

    @NonNull
    @Override
    public LoadFromDatabaseAdapter.LoadFromDbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.load_from_db_recyclerview, parent, false);
        return new LoadFromDatabaseAdapter.LoadFromDbViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LoadFromDatabaseAdapter.LoadFromDbViewHolder holder, int position) {
        holder.txtName.setText(typecodeUserModelList.get(position).getName());
        holder.txtvEmail.setText(typecodeUserModelList.get(position).getEmail());
        holder.txtvUsername.setText(typecodeUserModelList.get(position).getUsername());
        holder.txtvWebsite.setText(typecodeUserModelList.get(position).getWebsite());
        holder.txtvPhone.setText(typecodeUserModelList.get(position).getPhone());


    }
    public void removeAt(int position) {
        typecodeUserModelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, typecodeUserModelList.size());
    }

    @Override
    public int getItemCount() {
        return typecodeUserModelList.size();
    }

    public class LoadFromDbViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtvEmail, txtvPhone, txtvUsername, txtvWebsite;
        Button detailsbtn ,deletebtn,updatebtn;

        public LoadFromDbViewHolder(View itemView) {
            super(itemView);
            txtName =itemView.findViewById(R.id.txtv_name);
            txtvEmail = itemView.findViewById(R.id.txtv_email);
            txtvPhone = itemView.findViewById(R.id.txtv_phone_number);
            txtvWebsite = itemView.findViewById(R.id.txtv_website);
            txtvUsername = itemView.findViewById(R.id.txtv_username);
            deletebtn = itemView.findViewById(R.id.deletebtn);
            updatebtn = itemView.findViewById(R.id.updatebtn);
            detailsbtn = itemView.findViewById(R.id.detailshbtn);

            updatebtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((LoadFromDatabaseActivity) context).update(getAdapterPosition());
                }
            });

            deletebtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((LoadFromDatabaseActivity) context).delete(getAdapterPosition());
                    removeAt(getAdapterPosition());
                }
            });

            detailsbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((LoadFromDatabaseActivity) context).viewDetails(getAdapterPosition());
                }
            });
        }

    }
}
