package backend.academy.words;

import backend.academy.words.enums.Category;
import backend.academy.words.enums.Level;

/**
 * The CategoryLevel record stores a category-level pair for
 * easier interaction with words
 * @param c word category
 * @param l word level
 */
public record CategoryLevel(Category c, Level l) { }
