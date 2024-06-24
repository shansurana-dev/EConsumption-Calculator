package org.exercise;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
    private static float getEnergyConsumed(List<Message> messages) throws Exception{
        if (messages.isEmpty()) {
            throw new Exception("No messages to process.");
        }
        float dimmerValue = 0.0f;
        long lastTimestamp = messages.get(0).timestamp;
        float energyConsumed = 0.0f;
        for (Message message : messages) {
            System.out.println(message.timestamp + " " + message.messageType + " " + message.deltaValue);
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
        System.out.println("Please enter the messages in the format: timestamp messageType [deltaValue]");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("EOF")) {
                    System.out.println("Thank you for using the Energy consumption tool");
                    break;
                }
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split(" ");
                    long timestamp = Long.parseLong(parts[0]);
                    String type = parts[1];
                    float value = 0.0f;
                    if (!(type.equals("TurnOff") || type.equals("Delta"))) {
                        System.err.println("Invalid message type: " + type);
                    }
                    else {
                        if (type.equals("Delta")) {
                            value = Float.parseFloat(parts[2]);
                        }
                        messages.add(new Message(timestamp, type, value));
                    }
                }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Invalid message format: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Sort the messages based on timestamp to handle out-of-order and duplicate messages
        Collections.sort(messages);
        try {
            float energyConsumed = getEnergyConsumed(messages);
            System.out.printf("Estimated energy used: %.3f Wh\n", energyConsumed);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}