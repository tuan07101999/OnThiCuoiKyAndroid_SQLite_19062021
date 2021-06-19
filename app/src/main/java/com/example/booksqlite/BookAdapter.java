package com.example.booksqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    List<Book> list = new ArrayList<>();
    LayoutInflater layoutInflater;

    public BookAdapter(Context context, List<Book> list) {
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_lst,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        //holder.id.setText(list.get(position).getId());
        holder.name.setText(list.get(position).getName().toString());
        holder.author.setText(list.get(position).getAuthor().toString());
        holder.discrip.setText(list.get(position).getDiscription().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,author,discrip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtSTT);
            name = itemView.findViewById(R.id.txtBookName);
            author = itemView.findViewById(R.id.txtBookAuthor);
            discrip = itemView.findViewById(R.id.txtBookDiscription);
        }
    }
}
