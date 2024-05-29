
package calculator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SmokingCostModel {
    private int cigarettesPerDay;
    private int cigarettesPerPack;
    private int packPrice;

    public SmokingCostModel() {
    }

    public SmokingCostModel(int cigarettesPerDay, int cigarettesPerPack, int packPrice) {
        if (cigarettesPerDay <= 0 || cigarettesPerPack <= 0 || packPrice <= 0) {
            throw new IllegalArgumentException("All values must be greater than zero.");
        }
        this.cigarettesPerDay = cigarettesPerDay;
        this.cigarettesPerPack = cigarettesPerPack;
        this.packPrice = packPrice;
    }

    public double calculateDailyCost() {
        return (cigarettesPerDay / (double) cigarettesPerPack) * packPrice;
    }

    public double calculateMonthlyCost() {
        return calculateDailyCost() * 30; 
    }

    public double calculateYearlyCost() {
        return calculateDailyCost() * 365;
    }


    public int getCigarettesPerDay() {
        return cigarettesPerDay;
    }

    public void setCigarettesPerDay(int cigarettesPerDay) {
        this.cigarettesPerDay = cigarettesPerDay;
    }

    public int getCigarettesPerPack() {
        return cigarettesPerPack;
    }

    public void setCigarettesPerPack(int cigarettesPerPack) {
        this.cigarettesPerPack = cigarettesPerPack;
    }

    public int getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(int packPrice) {
        this.packPrice = packPrice;
    }

    public void saveToJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), this);
    }

    public static SmokingCostModel loadFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), SmokingCostModel.class);
    }
}

