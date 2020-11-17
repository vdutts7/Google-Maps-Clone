package byow;

import byow.Core.RandomUtils;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import org.hamcrest.core.Is;

import java.util.Random;

public class Ocean {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private static final int MAX_ISLAND_WIDTH = WIDTH / 4;
    private static final int MAX_ISLAND_HEIGHT = WIDTH / 4;


    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        int islandWidth;
        int islandHeight;

        //create world
        TETile[][] ocean = new TETile[WIDTH][HEIGHT];


        // initialize tiles to water
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                ocean[x][y] = Tileset.WATER;
            }
        }

        islandWidth = (int) RandomUtils.uniform(RANDOM, MAX_ISLAND_WIDTH);
        islandHeight = (int) RandomUtils.uniform(RANDOM, MAX_ISLAND_HEIGHT);
        IslandGenerator islandGenerator = new IslandGenerator(islandWidth, islandHeight, WIDTH, HEIGHT, RANDOM);
        for(Position position : islandGenerator.getPositionTracker()) {
            ocean[position.getX()][position.getY()] = Tileset.SAND;
        }

        islandWidth = (int) RandomUtils.uniform(RANDOM, MAX_ISLAND_WIDTH);
        islandHeight = (int) RandomUtils.uniform(RANDOM, MAX_ISLAND_HEIGHT);
        IslandGenerator islandGenerator2 = new IslandGenerator(islandWidth, islandHeight, WIDTH, HEIGHT, RANDOM);
        for(Position position : islandGenerator2.getPositionTracker()) {
            ocean[position.getX()][position.getY()] = Tileset.SAND;
        }

        ter.renderFrame(ocean);
    }
}
