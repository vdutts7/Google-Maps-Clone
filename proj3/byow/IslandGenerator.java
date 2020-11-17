package byow;

import byow.Core.RandomUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class IslandGenerator {
    private int islandW;
    private int islandH;

    private static HashSet<Position> positionTracker;

    public IslandGenerator(int inputWidth, int inputHeight, int oceanWidth, int oceanHeight, Random random) {
        islandW = inputWidth;
        islandH = inputHeight;
        positionTracker = new HashSet<>();

        int startRanX;
        int startRanY;
        int endRanX;
        int endRanY;

        int condition = 0;

        while(true) {
            startRanX = (RandomUtils.uniform(random,oceanWidth - inputWidth));
            startRanY = (RandomUtils.uniform(random, oceanHeight - inputHeight));
            endRanX = startRanX + islandW;
            endRanY = startRanY + islandH;
            condition = 0;
            for(int i = startRanX; i < endRanX; i++) {
                for(int j = endRanX; j < endRanY; j++) {
                    Position testInput = new Position(i, j);
                    if(positionTracker.contains(testInput)) {
                        condition = 1;
                        i = endRanX;
                        j= endRanY;
                    }
                }
            }
            if (condition == 0) {
                break;
            }
        }

        for(int i = startRanX; i <endRanX; i++) {
            for(int j = startRanY; j < endRanY; j++) {
                Position occupiedBlock = new Position(i, j);
                positionTracker.add(occupiedBlock);
            }
        }

    }

    public HashSet<Position> getPositionTracker() {
        return positionTracker;
    }
}
