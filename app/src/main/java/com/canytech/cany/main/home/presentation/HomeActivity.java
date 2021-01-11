package com.canytech.cany.main.home.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canytech.cany.R;
import com.canytech.cany.model.Category;
import com.canytech.cany.model.Classes;
import com.canytech.cany.util.CategoryTask;
import com.canytech.cany.util.ImageDownloaderTask;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CategoryTask.CategoryLoader {

    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_main);

        List<Category> categories = new ArrayList<>();

        mainAdapter = new MainAdapter(categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(mainAdapter);

        CategoryTask categoryTask = new CategoryTask(this);
        categoryTask.setCategoryLoader(this);
        categoryTask.execute("https://tiagoaguiar.co/api/netflix/home");
    }

    @Override
    public void onResult(List<Category> categories) {
        mainAdapter.setCategories(categories);
        mainAdapter.notifyDataSetChanged();
    }

    private static class MovieHolder extends RecyclerView.ViewHolder {

        final ImageView imageViewCover;
        final TextView textViewClass;

        public MovieHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener, TextView textViewClass) {
            super(itemView);
            imageViewCover = itemView.findViewById(R.id.image_view_cover);
            this.textViewClass = textViewClass;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }

    }

    private static class CategoryHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        RecyclerView recyclerViewClasses;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            recyclerViewClasses = itemView.findViewById(R.id.recycler_view_classes);
        }

    }

    private class MainAdapter extends RecyclerView.Adapter<CategoryHolder> {

        private List<Category> categories;

        private MainAdapter(List<Category> categories) {
            this.categories = categories;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CategoryHolder(getLayoutInflater().inflate(R.layout.category_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            Category category = categories.get(position);
            holder.textViewTitle.setText(category.getName());
            holder.recyclerViewClasses.setAdapter(new MovieAdapter(category.getMovies()));
            holder.recyclerViewClasses.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.HORIZONTAL, false));
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        void setCategories(List<Category> categories) {
            this.categories.clear();
            this.categories.addAll(categories);
        }

    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> implements OnItemClickListener {

        private final List<Classes> classes;

        private MovieAdapter(List<Classes> classes) {
            this.classes = classes;
        }

        @Override
        public void onClick(int position) {
            if (classes.get(position).getId() <= 3) {
                Intent intent = new Intent(HomeActivity.this, ClassesActivity.class);
                intent.putExtra("id", classes.get(position).getId());
                startActivity(intent);
            }
        }

        @NonNull
        @Override
        public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.classes_item, parent, false);
            TextView textViewClass = findViewById(R.id.text_view_item_title_home);
            return new MovieHolder(view, this, textViewClass);
        }

        @Override
        public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
            Classes movie = classes.get(position);
            new ImageDownloaderTask(holder.imageViewCover).execute(movie.getCoverUrl());
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }

    }

    interface OnItemClickListener {
        void onClick(int position);
    }

}