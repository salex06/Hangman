package backend.academy.util;

import java.util.List;

/**
 * The GallowsArtist class works with a graphical representation of the gallows
 * according to the maximum number of attempts and the current one
 */
public class GallowsArtist {
    private static final int STEPS_NUMBER_OF_DRAWING_A_MAN = 6;
    private final List<String> steps;

    /**
     * Default constructor. Defines the list of gallows "states"
     * (the stages of drawing the gallows)
     */
    public GallowsArtist() {
        steps = List.of(
            "|   \n",
            "|  o\n",
            "|  o\n|  |\n",
            "|  o\n| /|\n",
            "|  o\n| /|\\\n",
            "|  o\n| /|\\\n| /\n",
            "|  o\n| /|\\\n| / \\\n");
    }

    /**
     * Calculates the current gallows representation according to the maximum number of errors
     * and the current attempt
     *
     * @param numberOfAttempts     the maximum possible number of attempts/mistakes
     * @param currentAttemptNumber the number of the current attempt (in other words, the number of mistakes)
     * @return representation of gallows in the form of a String type object
     */
    public String getCurrGallowsState(int numberOfAttempts, int currentAttemptNumber) {
        //The number of additional pieces of rope that are needed to draw the current state of the gallows
        int countExtraPiecesOfRope = Math.max(0, numberOfAttempts - STEPS_NUMBER_OF_DRAWING_A_MAN);
        StringBuilder currentGallowsState = new StringBuilder("______\n|/ |\n");
        //The height of the gallows
        int countHeightOfGallows = 0;

        //Draw a rope before drawing a person (if necessary)
        int neededPieces = Math.min(currentAttemptNumber, countExtraPiecesOfRope);
        while (neededPieces > 0) {
            currentGallowsState.append("|  |\n");
            neededPieces--;
            countHeightOfGallows++;
        }
        if (currentAttemptNumber >= countExtraPiecesOfRope) {
            //Drawing a person
            String currentStepOfDrawingMan = steps.get(currentAttemptNumber - countExtraPiecesOfRope);
            currentGallowsState.append(currentStepOfDrawingMan);
            for (int i = 0; i < currentStepOfDrawingMan.length(); i++) {
                if (currentStepOfDrawingMan.charAt(i) == '\n') {
                    countHeightOfGallows++;
                }
            }
        }
        //At the end add the remaining parts of the gallows
        while (countHeightOfGallows != numberOfAttempts) {
            currentGallowsState.append(steps.getFirst());
            countHeightOfGallows++;
        }
        return currentGallowsState.toString();
    }

}
