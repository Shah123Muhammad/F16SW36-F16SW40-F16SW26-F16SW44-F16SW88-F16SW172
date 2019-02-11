package com.example.hp.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ElementListAdapter extends RecyclerView.Adapter<ElementListAdapter.ElementListViewHolder> {
    private List<String> data;
    private Context context;

    public ElementListAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }
    @Override
    public ElementListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new ElementListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ElementListViewHolder elementListViewHolder, final int i) {
        String title = data.get(i);
        elementListViewHolder.textView.setText(title);
        elementListViewHolder.setOnClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(context, data.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ElementListViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        private ItemClickListener itemClickListener;
        public ElementListViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }
        public void setOnClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}