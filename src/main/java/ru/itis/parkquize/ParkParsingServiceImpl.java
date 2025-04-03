package ru.itis.parkquize;

import ru.itis.parkquize.annotations.FieldName;
import ru.itis.parkquize.annotations.MaxLength;
import ru.itis.parkquize.annotations.NotBlank;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkParsingServiceImpl implements ParkParsingService {

    @Override
    public Park parseParkData(String parkDatafilePath) throws ParkParsingException {
        Map<String, String> dataMap = new HashMap<>();
        try (Reader reader = new FileReader(parkDatafilePath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("***")) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        String key = parts[0].replace("\"", "").trim();
                        String value = parts[1].replace("\"", "").trim();
                        dataMap.put(key, value.isEmpty() ? null : value);
                        System.out.println(dataMap);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Class<Park> parkClass = Park.class;
        try {
            Constructor<Park> constructor = parkClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Park park = constructor.newInstance();
            Field[] fields = parkClass.getDeclaredFields();

            setFields(fields, park, parkClass, dataMap);
            return park;

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setFields(Field[] fields, Park park, Class<Park> parkClass, Map<String, String> dataMap) throws IllegalAccessException {

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(FieldName.class)) {
                FieldName fieldAnnotation = field.getAnnotation(FieldName.class);
                String fieldName = fieldAnnotation.value();
                String name = dataMap.get(fieldName);
                field.set(park, name);
            }
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                String fieldName = field.getName();
                String name = dataMap.get(fieldName);
                field.set(park, name);
            }
            if (field.isAnnotationPresent(NotBlank.class)) {
                String fieldName = field.getName();
                String name = dataMap.get(fieldName);
                field.set(park, name);
            }

        }
    }
}
