package edu.coursera.parallel;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @since Created in 7:29 PM 10/28/18.
 */

import static edu.rice.pcdp.PCDP.async;
import static edu.rice.pcdp.PCDP.finish;

/**
 * A simple class for testing compilation of an PCDP project.
 */
public final class Setup {

    /**
     * Default constructor.
     */
    private Setup() {
    }

    /**
     * A simple method for testing compilation of an PCDP project.
     * @param val Input value
     * @return Dummy value
     */
    public static int setup(final int val) {
        final int[] result = new int[1];
        finish(() -> {
            async(() -> {
                result[0] = val;
            });
        });
        return result[0];
    }
}
