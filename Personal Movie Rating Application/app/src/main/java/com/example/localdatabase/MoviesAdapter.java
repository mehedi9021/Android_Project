package com.example.localdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localdatabase.model.ModelImdb;
import com.example.localdatabase.repo.ImdbRepository;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    OnButtonClickListener listener;

    Context context;
    List<ModelImdb> movies;
    ImdbRepository repository;
    public MoviesAdapter(Context context, List<ModelImdb> movies, ImdbRepository repository) {
        this.context = context;
        this.movies = movies;
        this.repository = repository;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.name.setText(movies.get(position).getName());
        holder.year.setText(movies.get(position).getYear() + "");
        holder.rating.setRating(movies.get(position).getRating());

        holder.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository.deleteSingleData(Double.parseDouble(movies.get(position).getId()+""));
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.main_layout.setVisibility(View.GONE);
                holder.edit_layout.setVisibility(View.VISIBLE);

                holder.edit_name.setText(movies.get(position).getName());
                holder.edit_year.setText(movies.get(position).getYear()+"");
                holder.edit_rating.setText(movies.get(position).getRating()+"");

                listener.onClick(position);
            }
        });

        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelImdb imdb = new ModelImdb(holder.edit_name.getText().toString()
                        ,Float.parseFloat(holder.edit_rating.getText().toString()),Integer.parseInt(holder.edit_year.getText().toString()));

                imdb.setId(movies.get(position).getId());


                repository.updateData(imdb);

                holder.main_layout.setVisibility(View.VISIBLE);
                holder.edit_layout.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, year;
        RatingBar rating;
        ImageButton delete,edit;
        LinearLayout edit_layout;
        EditText edit_name,edit_year,edit_rating;
        RelativeLayout main_layout;
        Button submit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            delete = itemView.findViewById(R.id.bt_delete);
            rating = itemView.findViewById(R.id.item_rb_rating);
            name = itemView.findViewById(R.id.item_tv_name);
            year = itemView.findViewById(R.id.item_tv_year);

            edit_layout = itemView.findViewById(R.id.edit_layout);

            edit_name = itemView.findViewById(R.id.edit_name);
            edit_year = itemView.findViewById(R.id.edit_year);
            edit_rating = itemView.findViewById(R.id.edit_rating);

            edit = itemView.findViewById(R.id.update);

            main_layout = itemView.findViewById(R.id.main_layout);

            submit = itemView.findViewById(R.id.submit);

        }
    }


    //Access from MainActivity
    public interface OnButtonClickListener
    {
        void onClick(int position);
    }

    public void setOnButtonClick(OnButtonClickListener listener)
    {
        this.listener = listener;
    }

}
