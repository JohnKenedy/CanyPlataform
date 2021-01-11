package com.canytech.cany.model;

import java.util.List;

public class ClassesDetail {

  private final Classes movie;
  private final List<Classes> moviesSimilar;

  public ClassesDetail(Classes movie, List<Classes> moviesSimilar) {
    this.movie = movie;
    this.moviesSimilar = moviesSimilar;
  }

  public Classes getMovie() {
    return movie;
  }

  public List<Classes> getMoviesSimilar() {
    return moviesSimilar;
  }

}
