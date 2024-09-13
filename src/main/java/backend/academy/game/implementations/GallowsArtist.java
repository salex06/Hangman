package backend.academy.game.implementations;

import java.util.List;

public class GallowsArtist {
    private final List<String> steps;
    @SuppressWarnings({"all"})
    private final int NUMBER_OF_ATTEMPTS;

    public GallowsArtist(int numberOfAttempts) {
        steps = List.of(
            "|\n",
            "|\t о\n",
            "|\t о\n|\t |\n",
            "|\t o\n|\t/|\n",
            "|\t o\n|\t/|\\\n",
            "|\t o\n|\t/|\\\n| \t/\n",
            "|\t o\n|\t/|\\\n| \t/ \\\n");
        this.NUMBER_OF_ATTEMPTS = numberOfAttempts;
    }

    public String getCurrGallowsState(int currentAttemptNumber) {
        //TODO: make implementation if the NUMBER_OF_ATTEMPTS != steps.size()
        if (currentAttemptNumber >= steps.size()) {
            return "";
        }
        return "______\n|/\t |\n" + steps.get(currentAttemptNumber);
    }

}
