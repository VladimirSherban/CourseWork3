package com.example.coursework3.service;

import com.example.coursework3.model.Color;
import com.example.coursework3.model.Size;
import com.example.coursework3.model.SocksBatch;

public interface ValidationService {
    boolean validate(SocksBatch socksBatch);

    boolean validate(Color color, Size size, Integer cottonMin, Integer cottonMax);
}
