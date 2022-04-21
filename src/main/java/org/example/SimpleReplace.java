package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Input: aabcccbbad
 * Output:
 * -> aabbbad
 * -> aaad
 * -> d
 */
public class SimpleReplace implements Replace {

    public List<String> process(String raw) {
        valid(raw);

        int oldLength = raw.length();
        var replaced = raw;
        var list = new ArrayList<String>();
        while (true) {
            replaced = replaced.replaceAll(replaceRegex, "");

            if (replaced.length() == oldLength) {
                // if not matches, return raw data
                if (replaced.length() == raw.length()) {
                    list.add(replaced);
                }
                break;
            }
            list.add(replaced);
            oldLength = replaced.length();
        }
        return list;
    }
}
