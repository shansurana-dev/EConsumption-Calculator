package org.exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

class EnergyConsumptionTest {
    @Test
    public void testSimpleCase() throws Exception {
        List<EnergyConsumption.Message> messages = Arrays.asList(
                new EnergyConsumption.Message(1544206562L, "TurnOff", 0),
                new EnergyConsumption.Message(1544206563L, "Delta", 0.5F),
                new EnergyConsumption.Message(1544210163L, "TurnOff", 0)
        );
        float energy = EnergyConsumption.getEnergyConsumed(messages);
        assertEquals(2.5, energy, 0.001);
    }

    @Test
    public void testComplexCase() throws Exception {
        List<EnergyConsumption.Message> messages = Arrays.asList(
                new EnergyConsumption.Message(1544206562L, "TurnOff", 0),
                new EnergyConsumption.Message(1544206563L, "Delta", 0.5F),
                new EnergyConsumption.Message(1544210163L, "Delta",  -0.25F),
                new EnergyConsumption.Message(1544211963L, "Delta", 0.75F),
                new EnergyConsumption.Message(1544211963L, "Delta", 0.75F),  // Duplicate
                new EnergyConsumption.Message(1544213763L, "TurnOff", 0)
        );
        float energy = EnergyConsumption.getEnergyConsumed(messages);
        assertEquals(5.625, energy, 0.001);
    }

    @Test
    public void testOutOfOrderMessages() throws Exception {
        List<EnergyConsumption.Message> messages = Arrays.asList(
                new EnergyConsumption.Message(1544206562L, "TurnOff", 0),
                new EnergyConsumption.Message(1544210163L, "Delta",  -0.25F),
                new EnergyConsumption.Message(1544206563L, "Delta", 0.5F),
                new EnergyConsumption.Message(1544213763L, "TurnOff", 0)
        );
        float energy = EnergyConsumption.getEnergyConsumed(messages);
        assertEquals(3.75, energy, 0.001);
    }

    @Test
    public void testMissingMessages() throws Exception {
        List<EnergyConsumption.Message> messages = Arrays.asList(
                new EnergyConsumption.Message(1544206562L, "TurnOff", 0),
                new EnergyConsumption.Message(1544210163L, "Delta",  -0.25F),
                new EnergyConsumption.Message(1544213763L, "TurnOff", 0)
        );
        float energy = EnergyConsumption.getEnergyConsumed(messages);
        assertEquals(1.25, energy, 0.001);
    }

    @Test
    public void testEdgeCaseAllOff() throws Exception {
        List<EnergyConsumption.Message> messages = Arrays.asList(
                new EnergyConsumption.Message(1544206562L, "TurnOff", 0),
                new EnergyConsumption.Message(1544213763L, "TurnOff", 0)
        );
        float energy = EnergyConsumption.getEnergyConsumed(messages);
        assertEquals(0.0, energy, 0.001);
    }
}