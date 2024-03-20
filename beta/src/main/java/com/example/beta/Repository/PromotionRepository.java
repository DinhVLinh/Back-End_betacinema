package com.example.beta.Repository;

import com.example.beta.Model.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions,Integer> {
}
