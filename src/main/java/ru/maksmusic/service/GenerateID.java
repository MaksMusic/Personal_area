package ru.maksmusic.service;

public class GenerateID {
    private static Long id=0L;

    public static Long getId() {
        id++;
        return id;
    }
}
