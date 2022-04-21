package org.example;

import java.util.List;
import java.util.regex.Pattern;

public interface Replace {
    String replaceRegex = "(\\w)\\1{2,}";

    // lowercase letters
    String validRegex = "^[a-z]+$";

    /**
     * @param raw input data
     * @return output list
     */
    List<String> process(String raw);

    /**
     * check input data
     */
    default void valid(String raw) {
        if (!Pattern.compile(validRegex).matcher(raw).find()) {
            throw new IllegalArgumentException("only need lowercaseletters for input");
        }
    }
}
