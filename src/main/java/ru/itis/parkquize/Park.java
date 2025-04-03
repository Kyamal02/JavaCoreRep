package ru.itis.parkquize;

import ru.itis.parkquize.annotations.FieldName;
import ru.itis.parkquize.annotations.MaxLength;
import ru.itis.parkquize.annotations.NotBlank;

import java.time.LocalDate;

//Вносить изменения в этот класс не нужно!
final class Park {

    @FieldName("title")
    private String legalName;

    @NotBlank
    @MaxLength(13)
    private String ownerOrganizationInn;

    @NotBlank
    private LocalDate foundationYear;

    private Park() {
    }

    @Override
    public String toString() {
        return "Park{" +
                "legalName='" + legalName + '\'' +
                ", ownerOrganizationInn='" + ownerOrganizationInn + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }
}
