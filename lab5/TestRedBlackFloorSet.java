import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        RedBlackFloorSet rbFloorSet = new RedBlackFloorSet();
        for(int i = 0; i < 20; i++) {
            rbFloorSet.add(StdRandom.uniform(0.0, 10.0));
        }
    }

}
