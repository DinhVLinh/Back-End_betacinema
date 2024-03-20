package com.example.beta.Repository;

import com.example.beta.Model.MovieTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesTypeRepository extends JpaRepository<MovieTypes,Integer> {
}
