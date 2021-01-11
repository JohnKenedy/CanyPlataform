package com.canytech.cany.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.canytech.cany.model.Classes;
import com.canytech.cany.model.ClassesDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ClassesDetailTask extends AsyncTask<String, Void, ClassesDetail> {

  private final WeakReference<Context> context;
  private ProgressDialog dialog;
  private ClassesDetailLoader classesDetailLoader;

  public ClassesDetailTask(Context context) {
    this.context = new WeakReference<>(context);
  }

  public void setMovieDetailLoader(ClassesDetailLoader classesDetailLoader) {
    this.classesDetailLoader = classesDetailLoader;
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    Context context = this.context.get();

    if (context != null)
      dialog = ProgressDialog.show(context, "Carregando", "", true);
  }

  @Override
  protected ClassesDetail doInBackground(String... params) {
    String url = params[0];

    try {
      URL requestUrl = new URL(url);

      HttpsURLConnection urlConnection = (HttpsURLConnection) requestUrl.openConnection();
      urlConnection.setReadTimeout(2000);
      urlConnection.setConnectTimeout(2000);

      int responseCode = urlConnection.getResponseCode();
      if (responseCode > 400) {
        throw new IOException("Server Error");
      }

      InputStream inputStream = urlConnection.getInputStream();

      BufferedInputStream in = new BufferedInputStream(inputStream);

      String jsonAsString = toString(in);

      ClassesDetail classesDetail = getMovieDetail(new JSONObject(jsonAsString));
      in.close();

      return classesDetail;
    } catch (IOException | JSONException e) {
      e.printStackTrace();
    }

    return null;
  }

  private ClassesDetail getMovieDetail(JSONObject json) throws JSONException {
    int id = json.getInt("id");
    String title = json.getString("title");
    String desc = json.getString("desc");
    String cast = json.getString("cast");
    String coverUrl = json.getString("cover_url");

    List<Classes> classes = new ArrayList<>();
    JSONArray movieArray = json.getJSONArray("movie");
    for (int i = 0; i < movieArray.length(); i++) {
      JSONObject classs = movieArray.getJSONObject(i);
      String c = classs.getString("cover_url");
      int idSimilar = classs.getInt("id");

      Classes similar = new Classes();
      similar.setId(idSimilar);
      similar.setCoverUrl(c);

      classes.add(similar);
    }

    Classes classes1 = new Classes();
    classes1.setId(id);
    classes1.setCoverUrl(coverUrl);
    classes1.setTitle(title);
    classes1.setDesc(desc);
    classes1.setCast(cast);

    return new ClassesDetail(classes1, classes);
  }

  @Override
  protected void onPostExecute(ClassesDetail classesDetail) {
    super.onPostExecute(classesDetail);
    dialog.dismiss();

    if (classesDetailLoader != null)
      classesDetailLoader.onResult(classesDetail);
  }

  private String toString(InputStream is) throws IOException {
    byte[] bytes = new byte[1024];
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int lidos;
    while ((lidos = is.read(bytes)) > 0) {
      baos.write(bytes, 0, lidos);
    }

    return new String(baos.toByteArray());
  }

  public interface ClassesDetailLoader {
    void onResult(ClassesDetail classesDetail);
  }
}
