package calculator;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SmokingCostModelTest {

    @Test
    public void testCalculateDailyCost() {
        SmokingCostModel model = new SmokingCostModel(10, 20, 1000);
        assertEquals(500.0, model.calculateDailyCost(), 0.001);
    }

    @Test
    public void testCalculateMonthlyCost() {
        SmokingCostModel model = new SmokingCostModel(10, 20, 1000);
        assertEquals(15000.0, model.calculateMonthlyCost(), 0.001);
    }

    @Test
    public void testCalculateYearlyCost() {
        SmokingCostModel model = new SmokingCostModel(10, 20, 1000);
        assertEquals(182500.0, model.calculateYearlyCost(), 0.001);
    }

    @Test
    public void testJsonSerialization() throws IOException {
        SmokingCostModel originalModel = new SmokingCostModel(10, 20, 1000);

        originalModel.saveToJson("smoking_cost_test.json");
        SmokingCostModel loadedModel = SmokingCostModel.loadFromJson("smoking_cost_test.json");

        assertEquals(originalModel.getCigarettesPerDay(), loadedModel.getCigarettesPerDay());
        assertEquals(originalModel.getCigarettesPerPack(), loadedModel.getCigarettesPerPack());
        assertEquals(originalModel.getPackPrice(), loadedModel.getPackPrice(), 0.001);

        new java.io.File("smoking_cost_test.json").delete();
        }
    }




