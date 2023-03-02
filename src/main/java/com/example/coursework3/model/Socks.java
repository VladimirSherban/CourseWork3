package com.example.coursework3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Носки
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;   // Процент хлопка
}
