package com.haroldjin.java.lang.fundamental;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class MarsRoverTest {

    @Test
    public void marsRoverTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MarsRoverTest.class.getClassLoader().getResourceAsStream("instructions")));
        String line = bufferedReader.readLine();
        String[] coordinates = line.split(" ");
        MarsRover marsRover = new MarsRover(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1]));

        // Read line for each operations
        while((line = bufferedReader.readLine()) != null){
            String[] position = line.split(" ");
            String id = line;
            marsRover.placeRover(id, Integer.parseInt(position[0]), Integer.parseInt(position[1]), MarsRover.Direction.valueOf(position[2]));

            // Just be cautious
            if ((line = bufferedReader.readLine()) == null){
                throw new RuntimeException("File ends unexpectedly");
            }
            marsRover.processInstructions(id, line);
            System.out.println(marsRover.getPositionString(id));
        }
        bufferedReader.close();
    }
}
