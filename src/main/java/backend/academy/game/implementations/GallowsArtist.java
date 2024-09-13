package backend.academy.game.implementations;

import java.util.List;

public class GallowsArtist {
    private static final int STEPS_NUMBER_OF_DRAWING_A_MAN = 6;
    private final List<String> steps;
    @SuppressWarnings({"all"})
    private final int NUMBER_OF_ATTEMPTS;

    public GallowsArtist(int numberOfAttempts) {
        steps = List.of(
            "|   \n",
            "|  o\n",
            "|  o\n|  |\n",
            "|  o\n| /|\n",
            "|  o\n| /|\\\n",
            "|  o\n| /|\\\n| /\n",
            "|  o\n| /|\\\n| / \\\n");
        this.NUMBER_OF_ATTEMPTS = numberOfAttempts;
    }

    public String getCurrGallowsState(int currentAttemptNumber) {
        int countExtraPiecesOfRope = Math.max(0, NUMBER_OF_ATTEMPTS - STEPS_NUMBER_OF_DRAWING_A_MAN);
        StringBuilder currentGallowsState = new StringBuilder("______\n|/ |\n");
        int countHeightOfGallows = 0;
        int neededPieces = Math.min(currentAttemptNumber, countExtraPiecesOfRope);
        while (neededPieces > 0) {
            currentGallowsState.append("|  |\n");
            neededPieces--;
            countHeightOfGallows++;
        }
        if (currentAttemptNumber >= countExtraPiecesOfRope) {
            String currentStepOfDrawingMan = steps.get(currentAttemptNumber - countExtraPiecesOfRope);
            currentGallowsState.append(currentStepOfDrawingMan);
            for (int i = 0; i < currentStepOfDrawingMan.length(); i++) {
                if (currentStepOfDrawingMan.charAt(i) == '\n') {
                    countHeightOfGallows++;
                }
            }
        }
        while (countHeightOfGallows != NUMBER_OF_ATTEMPTS) {
            currentGallowsState.append(steps.getFirst());
            countHeightOfGallows++;
        }
        return currentGallowsState.toString();
    }

}
