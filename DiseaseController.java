package com.example.cropdisease.controller;

import com.example.cropdisease.entity.DiseasePrediction;
import com.example.cropdisease.service.DiseasePredictionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/disease")
@CrossOrigin(origins = "http://localhost:5173")
public class DiseaseController {

    private final DiseasePredictionService service;

    public DiseaseController(DiseasePredictionService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "message", "Crop Disease API is running"
        ));
    }

    @PostMapping(value = "/predict", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> predict(
            @RequestParam("image") MultipartFile image,
            @RequestParam("cropType") String cropType,
            @RequestParam(value = "location", required = false) String location) {

        try {
            DiseasePrediction result =
                    service.predict(image, cropType, location);

            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage())
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("message", "Unable to process the prediction.")
            );
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<DiseasePrediction>> history() {
        return ResponseEntity.ok(service.getHistory());
    }
}
