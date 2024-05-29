package calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class SmokingCostController {

    @FXML
    private TextField cigarettesPerDayField;
    @FXML
    private TextField cigarettesPerPackField;
    @FXML
    private TextField packPriceField;
    @FXML
    private Label resultLabel;

    private SmokingCostModel model = new SmokingCostModel();

    @FXML
    public void handleCalculate() {
        if (!isInputValid()) {
            return;
        }

        double monthlyCost = model.calculateMonthlyCost();
        double yearlyCost = model.calculateYearlyCost();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("hu", "HU"));
        String monthlyCostString = currencyFormat.format(monthlyCost);
        String yearlyCostString = currencyFormat.format(yearlyCost);

        resultLabel.setText(String.format("Results: \n Monthly Cost: %s\n Yearly Cost: %s", monthlyCostString, yearlyCostString));
    }

    @FXML
    public void handleSave() {
        if (!isInputValid()) {
            resultLabel.setText("Cannot save data:\n Please just enter valid numbers!");
            return;
        }

        try {
            model.saveToJson("smoking_cost.json");
            resultLabel.setText("Data saved to smoking_cost.json");
        } catch (IOException e) {
            resultLabel.setText("Failed to save data: " + e.getMessage());
        }
    }

    @FXML
    public void handleLoad() {
        try {
            SmokingCostModel loadedModel = SmokingCostModel.loadFromJson("smoking_cost.json");

            model = loadedModel;
            cigarettesPerDayField.setText(String.valueOf(model.getCigarettesPerDay()));
            cigarettesPerPackField.setText(String.valueOf(model.getCigarettesPerPack()));
            packPriceField.setText(String.valueOf(model.getPackPrice()));
            resultLabel.setText("Data loaded from smoking_cost.json");
        } catch (IOException e) {
            resultLabel.setText("Failed to load data: " + e.getMessage());
        }
    }

    private boolean isInputValid() {
        try {
            int cigarettesPerDay = Integer.parseInt(cigarettesPerDayField.getText());
            int cigarettesPerPack = Integer.parseInt(cigarettesPerPackField.getText());
            int packPrice = Integer.parseInt(packPriceField.getText());

            if (cigarettesPerDay <= 0 || cigarettesPerPack <= 0 || packPrice <= 0) {
                resultLabel.setText("All values must be greater than zero.");
                return false;
            }

            model.setCigarettesPerDay(cigarettesPerDay);
            model.setCigarettesPerPack(cigarettesPerPack);
            model.setPackPrice(packPrice);

            return true;
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter just numbers!");
            return false;
        }
    }
}
