package com.canytech.cany.model;

import java.util.List;

public class Category {

  private String name;
  private List<Classes> movies;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Classes> getMovies() {
    return movies;
  }

  public void setMovies(List<Classes> movies) {
    this.movies = movies;
  }

}
