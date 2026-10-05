package com.example.cropdisease.service;

import com.example.cropdisease.entity.DiseasePrediction;
import com.example.cropdisease.repository.DiseasePredictionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DiseasePredictionService {

    private final DiseasePredictionRepository repository;
    private final MockPredictionService mockPredictionService;

    public DiseasePredictionService(
            DiseasePredictionRepository repository,
            MockPredictionService mockPredictionService) {
        this.repository = repository;
        this.mockPredictionService = mockPredictionService;
    }

    public DiseasePrediction predict(
            MultipartFile image,
            String cropType,
            String location) throws IOException {

        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Please upload a crop or leaf image.");
        }

        if (cropType == null || cropType.isBlank()) {
            throw new IllegalArgumentException("Crop type is required.");
        }

        MockPredictionService.PredictionResult result =
                mockPredictionService.predict(cropType, image.getOriginalFilename());

        DiseasePrediction prediction = new DiseasePrediction();
        prediction.setCropType(cropType.trim());
        prediction.setLocation(location == null ? "" : location.trim());
        prediction.setImageName(image.getOriginalFilename());
        prediction.setStatus(result.status());
        prediction.setConfidence(result.confidence());
        prediction.setDiagnosis(result.diagnosis());
        prediction.setRecommendedAction(result.recommendedAction());

        return repository.save(prediction);
    }

    public List<DiseasePrediction> getHistory() {
        return repository.findAll();
    }
}
