package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Input: abcccbad
 * Output:
 * -> abbbad, ccc is replaced by b
 * -> aaad, bbb is replaced by a
 * -> d
 */
public class AdvanceReplace implements Replace {

    public List<String> process(String raw) {
        valid(raw);
        var oldLength = raw.length();
        var replaced = raw;
        var list = new ArrayList<String>();

        while (true) {
            var matches = new ArrayList<String>();
            replaced = Pattern.compile(replaceRegex).matcher(replaced).replaceAll((r) -> {
                var before = r.group();
                var letter = before.charAt(0);
                if (letter == 'a') {
                    return "";
                }
                var after = String.valueOf((char) (letter - 1));
                matches.add(before + " is replaced by " + after);
                return after;
            });
            if (replaced.length() == oldLength) {
                // if not matches, return raw data
                if (replaced.length() == raw.length()) {
                    list.add(replaced);
                }
                break;
            }
            var row = replaced;
            if (!matches.isEmpty()) {
                row += ", " + String.join(", ", matches);
            }
            list.add(row);

            oldLength = replaced.length();
        }
        return list;
    }
}
