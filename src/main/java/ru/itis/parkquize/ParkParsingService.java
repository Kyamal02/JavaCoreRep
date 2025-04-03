package ru.itis.parkquize;

interface ParkParsingService {

    Park parseParkData(String parkDatafilePath) throws ParkParsingException;

}
