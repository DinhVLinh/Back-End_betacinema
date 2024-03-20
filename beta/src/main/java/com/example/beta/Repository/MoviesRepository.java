package com.example.beta.Repository;

import com.example.beta.Model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Integer> {
}
