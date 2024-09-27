package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @org.junit.jupiter.api.Test
  public void testThreeRemove() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      correct.addLast(1);
      correct.addLast(2);
      correct.addLast(3);

      broken.addLast(1);
      broken.addLast(2);
      broken.addLast(3);

      assertEquals(correct.size(), broken.size());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
  }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 1000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int unsureSize = BL.size();
                assertEquals(size, unsureSize);
            } else if(L.size() > 0 && operationNumber == 2) {
                // getLast
                int last = L.getLast();
                int unsureLast = BL.getLast();
                assertEquals(last, unsureLast);
            } else if(L.size() > 0 && operationNumber == 3) {
                // removeLast
                L.removeLast();
                BL.removeLast();
                int nowSize = L.size();
                int unsureSize = BL.size();
                assertEquals(nowSize, unsureSize);
            }
        }
    }
}
