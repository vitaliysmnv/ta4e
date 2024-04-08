package com.example.textadventuregame.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class TwoDArrayXmlSerializer extends StdSerializer<int[][]> {

    public TwoDArrayXmlSerializer() {
        this(null);
    }

    public TwoDArrayXmlSerializer(Class<int[][]> t) {
        super(t);
    }

    @Override
    public void serialize(int[][] value, JsonGenerator gen, SerializerProvider provider) {
        try {
            ToXmlGenerator xmlGen = (ToXmlGenerator) gen;
            xmlGen.writeString(arrayToString(value));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String arrayToString(int[][] array) {
        StringBuilder sb = new StringBuilder();

        for (int[] row : array) {
            for (int element : row) {
                sb.append(element).append(" ");
            }
            sb.append("\n"); // New line after each row
        }

        return sb.toString();
    }
}