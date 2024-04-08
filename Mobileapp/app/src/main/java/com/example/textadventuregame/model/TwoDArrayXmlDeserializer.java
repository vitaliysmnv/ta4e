package com.example.textadventuregame.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.deser.XmlReadContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TwoDArrayXmlDeserializer extends StdDeserializer<int[][]> {

    private static final int COLUMNS = 30; // Define the number of columns

    protected TwoDArrayXmlDeserializer() {
        super(int[][].class);
    }

    @Override
    public int[][] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String fullXmlContent = p.readValueAsTree().toString();

        int[][] array2d = stringToArray(fullXmlContent.split(":\"")[1].split("\"[}]")[0]);

        return array2d;
    }
    public static int[][] stringToArray(String arrayString) {
        String[] rows = arrayString.split("\\\\n");
        int[][] array = new int[30][30];

        for (int i = 0; i < 30; i++) {
            String[] elements = rows[i].trim().split(" ");
            for (int j = 0; j < 30; j++) {
                array[i][j] = Integer.parseInt(elements[j]);
            }
        }

        return array;
    }
}
