package org.exercise;
import java.util.*;

public class EnergyConsumption {
    static class Message implements Comparable<Message> {
        long timestamp;
        String messageType;
        float deltaValue;
        //Constructor to initialize the values of timestamp, messageType and deltaValue
        Message(long timestamp, String messageType, float deltaValue) {
            this.timestamp = timestamp;
            this.messageType = messageType;
            this.deltaValue = deltaValue;
        }
        
        @Override
        public int compareTo(Message message) {
            return Long.compare(this.timestamp, message.timestamp);
        }
    }
    //Method to calculate the energy consumed
    private static float getEnergyConsumed(List<Message> messages) {
        float dimmerValue = 0.0f;
        long lastTimestamp = messages.get(0).timestamp;
        float energyConsumed = 0.0f;
        for (Message message : messages) {
            if (!message.messageType.equals("TurnOff")) {
                float duration = (message.timestamp - lastTimestamp) / 3600.0f; // in hours
                dimmerValue = Math.max(0.0f, Math.min(1.0f, message.deltaValue));
                energyConsumed += 5.0f * dimmerValue * duration;
            }
            lastTimestamp = message.timestamp;
            if (message.messageType.equals("TurnOff")) {
                dimmerValue = 0.0f;
            } else if (message.messageType.equals("Delta")) {
                dimmerValue += message.deltaValue;
                dimmerValue = Math.max(0.0f, Math.min(1.0f, dimmerValue));
            }
        }
        return energyConsumed;
    }

    public static void main(String[] args) {
        List<Message> messages = new ArrayList<>();
        //Read the messages from the input stream
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("EOF")) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(" ");
            long timestamp = Long.parseLong(parts[0]);
            String type = parts[1];
            float value = 0.0f;
            if (type.equals("Delta")) {
                value = Float.parseFloat(parts[2]);
            }
            messages.add(new Message(timestamp, type, value));
        }
        Collections.sort(messages);
        float energyConsumed = getEnergyConsumed(messages);
        System.out.printf("Estimated energy used: %.3f Wh\n", energyConsumed);
    }
}