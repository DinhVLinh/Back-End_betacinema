package com.example.beta.Repository;

import com.example.beta.Model.BillFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillFoodRepository extends JpaRepository<BillFood,Integer> {
}
