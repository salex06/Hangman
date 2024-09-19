package backend.academy.util;

/**
 * The IGallowsArtist interface provides an operation to obtain a gallows representation
 */
public interface IGallowsArtist {
    /**
     * Calculates the current gallows representation according to the maximum number of errors
     * and the current attempt
     *
     * @param numberOfAttempts     the maximum possible number of attempts/mistakes
     * @param currentAttemptNumber the number of the current attempt (in other words, the number of mistakes)
     * @return representation of gallows in the form of a String type object
     */
    String getCurrGallowsState(int numberOfAttempts, int currentAttemptNumber);
}
