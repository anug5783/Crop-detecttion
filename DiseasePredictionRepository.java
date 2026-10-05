package com.example.cropdisease.repository;

import com.example.cropdisease.entity.DiseasePrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseasePredictionRepository extends JpaRepository<DiseasePrediction, Long> {
}
