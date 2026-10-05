package com.example.cropdisease.service;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MockPredictionService {

    public PredictionResult predict(String cropType, String imageName) {
        String crop = cropType.toLowerCase(Locale.ROOT);

        if (crop.contains("tomato")) {
            return new PredictionResult(
                    "DISEASE DETECTED",
                    91.5,
                    "Possible Early Blight. Brown/black lesions may indicate a fungal leaf disease.",
                    "Remove badly affected leaves, avoid overhead watering, improve air circulation and consult a local agriculture expert before applying any treatment."
            );
        }

        if (crop.contains("rice")) {
            return new PredictionResult(
                    "DISEASE DETECTED",
                    88.0,
                    "Possible Rice Blast. Leaf symptoms can be associated with fungal infection.",
                    "Monitor nearby plants, maintain balanced nutrition and consult an agriculture officer for an appropriate treatment."
            );
        }

        if (crop.contains("potato")) {
            return new PredictionResult(
                    "DISEASE DETECTED",
                    86.7,
                    "Possible Late Blight.",
                    "Separate severely affected material, reduce leaf wetness and seek expert advice for suitable disease management."
            );
        }

        return new PredictionResult(
                "HEALTHY",
                93.2,
                "No major disease pattern detected by the demonstration model.",
                "Continue regular field monitoring, proper irrigation and balanced crop nutrition."
        );
    }

    public record PredictionResult(
            String status,
            Double confidence,
            String diagnosis,
            String recommendedAction
    ) {}
}
