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
import com.example.figjam.R;
import com.example.figjam.Views.Activity.MainActivity;

import java.util.ArrayList;

public class TypecodeAdapter extends RecyclerView.Adapter<TypecodeAdapter.TypecodeViewHolder> {
    Context context;
    ArrayList<TypecodeUsers> typecodeUsersList;

    public TypecodeAdapter(Context context, ArrayList<TypecodeUsers> typecodeUsersList) {
        this.context = context;
        this.typecodeUsersList = typecodeUsersList;
    }

    @NonNull
    @Override
    public TypecodeAdapter.TypecodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.typecode_user_recyclerview, parent, false);
        return new TypecodeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TypecodeAdapter.TypecodeViewHolder holder, int position) {
        holder.txtName.setText(typecodeUsersList.get(position).getName());
        holder.txtvEmail.setText(typecodeUsersList.get(position).getEmail());
        holder.txtvUsername.setText(typecodeUsersList.get(position).getUsername());
        holder.txtvWebsite.setText(typecodeUsersList.get(position).getWebsite());
        holder.txtvPhone.setText(typecodeUsersList.get(position).getPhone());


    }


    @Override
    public int getItemCount() {
        return typecodeUsersList.size();
    }

    public class TypecodeViewHolder extends RecyclerView.ViewHolder {
        TextView  txtName, txtvEmail, txtvPhone, txtvUsername, txtvWebsite;
        Button detailsbtn ,deletebtn;

        public TypecodeViewHolder(View itemView) {
            super(itemView);
            txtName =itemView.findViewById(R.id.txtv_name);
            txtvEmail = itemView.findViewById(R.id.txtv_email);
            txtvPhone = itemView.findViewById(R.id.txtv_phone_number);
            txtvWebsite = itemView.findViewById(R.id.txtv_website);
            txtvUsername = itemView.findViewById(R.id.txtv_username);
            deletebtn = itemView.findViewById(R.id.deletebtn);
            detailsbtn = itemView.findViewById(R.id.detailshbtn);

            detailsbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ((MainActivity) context).viewDetails(getAdapterPosition());
                }
            });
        }

    }
}
