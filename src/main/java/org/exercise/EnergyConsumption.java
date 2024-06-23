package org.exercise;
import java.util.*;

public class EnergyConsumption {

    static class Message {
        long timestamp;
        String messageType;
        float deltaValue;
        //Constructor to initialize the values of timestamp, messageType and deltaValue
        Message(long timestamp, String messageType, float deltaValue) {
            this.timestamp = timestamp;
            this.messageType = messageType;
            this.deltaValue = deltaValue;
        }
    }

    public static void main(String[] args) {

        //Read the messages from the input stream
        Scanner scanner = new Scanner(System.in);
        //Need a collection type to store the messages and take care of the order and the duplicates
        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        long timestamp = Long.parseLong(parts[0]);
        String type = parts[1];
        float value = 0.0f;
        if (type.equals("Delta")) {
            value = Float.parseFloat(parts[2]);
        }
        Message messages = new Message(timestamp, type, value);
        float energyConsumed = 0.0f;
        //Need logic to calculate the duration
        float duration;
        energyConsumed = 5.0f * messages.deltaValue * duration;
        System.out.printf("Estimated energy used: %.3f Wh\n", energyConsumed);
    }
}