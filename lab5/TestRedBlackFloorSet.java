import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    static RedBlackFloorSet rbFloorSet = new RedBlackFloorSet();

    @Test
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        rbFloorSet.add(7);
        rbFloorSet.add(9);
        Assert.assertEquals(2,rbFloorSet.floor(7));
    }

}
