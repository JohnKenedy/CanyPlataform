package com.canytech.cany.main.home.presentation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canytech.cany.R;
import com.canytech.cany.model.Classes;
import com.canytech.cany.model.ClassesDetail;
import com.canytech.cany.util.ImageDownloaderTask;
import com.canytech.cany.util.ClassesDetailTask;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity implements ClassesDetailTask.ClassesDetailLoader {

  private TextView txtTitle;
  private TextView txtDesc;
  private TextView txtCast;
  private ClassesAdapter classesAdapter;
  private ImageView imgCover;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_classes);

    txtTitle = findViewById(R.id.text_view_item_title);
    txtDesc = findViewById(R.id.text_view_desc);
    txtCast = findViewById(R.id.text_view_cast);
    RecyclerView recyclerView = findViewById(R.id.recycler_view_similar);
    imgCover = findViewById(R.id.image_view_cover);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
      getSupportActionBar().setTitle(null);
    }

    List<Classes> movies = new ArrayList<>();
    classesAdapter = new ClassesAdapter(movies);
    recyclerView.setAdapter(classesAdapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      int id = extras.getInt("id");
      ClassesDetailTask classesDetailTask = new ClassesDetailTask(this);
      classesDetailTask.setMovieDetailLoader(this);
      classesDetailTask.execute("https://tiagoaguiar.co/api/netflix/" + id);
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home)
      finish();

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onResult(ClassesDetail classesDetail) {
    txtTitle.setText(classesDetail.getMovie().getTitle());
    txtDesc.setText(classesDetail.getMovie().getDesc());
    txtCast.setText(classesDetail.getMovie().getCast());

    ImageDownloaderTask imageDownloaderTask = new ImageDownloaderTask(imgCover);
    imageDownloaderTask.setShadowEnabled(true);
    imageDownloaderTask.execute(classesDetail.getMovie().getCoverUrl());

    classesAdapter.setClasses(classesDetail.getMoviesSimilar());
    classesAdapter.notifyDataSetChanged();
  }

  private static class ClassesHolder extends RecyclerView.ViewHolder {

    final ImageView imageViewCover;

    ClassesHolder(@NonNull View itemView) {
      super(itemView);
      imageViewCover = itemView.findViewById(R.id.image_view_cover);
    }

  }


  private class ClassesAdapter extends RecyclerView.Adapter<ClassesHolder> {

    private List<Classes> classes;

    private ClassesAdapter(List<Classes> classes) {
      this.classes = classes;
    }

    void setClasses(List<Classes> classes) {
      this.classes.clear();
      this.classes.addAll(classes);
    }

    @NonNull
    @Override
    public ClassesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new ClassesHolder(getLayoutInflater()
              .inflate(R.layout.classes_item_similar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassesHolder holder, int position) {
      Classes classes = this.classes.get(position);
      new ImageDownloaderTask(holder.imageViewCover).execute(classes.getCoverUrl());
    }

    @Override
    public int getItemCount() {
      return classes.size();
    }

  }

}
