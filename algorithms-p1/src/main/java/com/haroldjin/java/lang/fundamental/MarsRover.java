package com.haroldjin.java.lang.fundamental;

import java.util.HashMap;
import java.util.Map;

public class MarsRover {
    private Coordinate plateauGrid;
    private Map<String, Position> roverPositions = new HashMap<>();

    class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Direction {
        N, E, S, W;
        private static Direction[] values = values();

        Direction rotateLeft90Degree() {
            if (this.ordinal() == 0){
                return values[values.length-1];
            }
            return values[(this.ordinal() - 1) % values.length];
        }

        Direction rotateRight90Degree() {
            return values[(this.ordinal() + 1) % values.length];
        }

    }

    class Position {
        Coordinate coordinate;
        Direction direction;

        public Position(Direction direction, Coordinate coordinate) {
            this.direction = direction;
            this.coordinate = coordinate;
        }

        @Override
        public String toString() {
            return String.format("%d %d %s", coordinate.x, coordinate.y, direction.toString());
        }
    }

    public MarsRover(int x, int y) {
        plateauGrid = new Coordinate(x, y);
    }

    public void placeRover(String id, int x, int y, Direction direction) {
        checkIllegalPosition(x, y);
        Coordinate coordinate = new Coordinate(x, y);
        roverPositions.put(id, new Position(direction, coordinate));
    }

    public void processInstructions(String id, String instructions) {
        Position position = roverPositions.get(id);
        for (char character : instructions.toCharArray()) {
            processInstruction(position, character);
        }
    }

    private void processInstruction(Position position, char instruction) {
        switch (instruction) {
            case 'L':
                position.direction = position.direction.rotateLeft90Degree();
                break;
            case 'R':
                position.direction = position.direction.rotateRight90Degree();
                break;
            case 'M':
                moveForward(position);
                break;
            default:
                break;
        }
    }

    private void moveForward(Position position) {
        Coordinate coordinate = position.coordinate;
        switch(position.direction){
            case N:
                int y = coordinate.y + 1;
                checkIllegalPosition(coordinate.x, y);
                coordinate.y = y;
                break;
            case E:
                int x = coordinate.x + 1;
                checkIllegalPosition(x, coordinate.y);
                coordinate.x = x;
                break;
            case S:
                y = coordinate.y - 1;
                checkIllegalPosition(coordinate.x, y);
                coordinate.y = y;
                break;
            case W:
                x = coordinate.x - 1;
                checkIllegalPosition(x, coordinate.y);
                coordinate.x = x;
                break;
            default:
                break;
        }
    }


    public String getPositionString(String id) {
        return roverPositions.get(id).toString();
    }

    private void checkIllegalPosition(int x, int y) {
        if (x > plateauGrid.x || y > plateauGrid.y) {
            String message = String.format("Invalid %d, %d coordinate, must be within %d x %d", x, y, plateauGrid.x, plateauGrid.y);
            throw new IllegalArgumentException(message);
        }
        if (x < 0 || y < 0) {
            String message = String.format("Invalid %d, %d coordinate, must not be less than zero", x, y);
            throw new IllegalArgumentException(message);
        }
    }
}
