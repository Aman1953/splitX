package com.example.splitx.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitx.R;
import com.example.splitx.models.Ower;
import com.example.splitx.models.OwerList;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ListInputAdapter extends RecyclerView.Adapter<ListInputAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_input_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = OwerList.getValue(position).getName();
        float amount = OwerList.getValue(position).getAmount();
        holder.getNameText().setText(name);
        if(amount > 0)
            holder.getAmountText().setText(String.valueOf(amount));
        else
            holder.getAmountText().setText("");
        holder.getNameText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                OwerList.getValue(holder.getAdapterPosition()).setName(s.toString());
            }
        });

        holder.getAmountText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals(""))
                    OwerList.getValue(holder.getAdapterPosition()).setAmount(Float.parseFloat(s.toString()));
            }
        });

        holder.getRemoveBtn().setOnClickListener(v -> {
            OwerList.removeElement(holder.getAdapterPosition());
            this.notifyItemRemoved(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return OwerList.getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final Button removeBtn;
        private final TextInputEditText nameText;
        private final TextInputEditText amountText;

        public TextInputEditText getNameText() {
            return nameText;
        }

        public TextInputEditText getAmountText() {
            return amountText;
        }

        public Button getRemoveBtn() {
            return removeBtn;
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.list_name_text);
            amountText = itemView.findViewById(R.id.list_amount_text);
            removeBtn = itemView.findViewById(R.id.list_remove_btn);
        }
    }

}
