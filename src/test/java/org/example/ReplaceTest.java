package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReplaceTest {

    @Test
    void testInvalid() {
        Replace replace = raw -> null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("1"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("$"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("A"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid(" "));
        Assertions.assertThrows(IllegalArgumentException.class, () -> replace.valid("aA"));
    }

    @Test
    void simpleReplace() {
        Replace replace = new SimpleReplace();

        Assertions.assertLinesMatch(replace.process("a"), List.of("a"));
        Assertions.assertLinesMatch(replace.process("aa"), List.of("aa"));
        Assertions.assertLinesMatch(replace.process("aaa"), List.of(""));
        Assertions.assertLinesMatch(replace.process("aaaa"), List.of(""));

        Assertions.assertLinesMatch(replace.process("abc"), List.of("abc"));
        Assertions.assertLinesMatch(replace.process("aabbcc"), List.of("aabbcc"));


        Assertions.assertLinesMatch(replace.process("aaabbbccc"), List.of(""));
        Assertions.assertLinesMatch(replace.process("aaaabbbbcccc"), List.of(""));

        Assertions.assertLinesMatch(replace.process("aabcccbbad"), List.of("aabbbad", "aaad", "d"));
    }

    @Test
    void advanceReplace() {
        Replace replace = new AdvanceReplace();

        Assertions.assertLinesMatch(replace.process("a"), List.of("a"));
        Assertions.assertLinesMatch(replace.process("aa"), List.of("aa"));
        Assertions.assertLinesMatch(replace.process("aaa"), List.of(""));
        Assertions.assertLinesMatch(replace.process("aaaa"), List.of(""));

        Assertions.assertLinesMatch(replace.process("abc"), List.of("abc"));
        Assertions.assertLinesMatch(replace.process("aabbcc"), List.of("aabbcc"));


        Assertions.assertLinesMatch(replace.process("aaabbbccc"), List.of("ab, bbb is replaced by a, ccc is replaced by b"));
        Assertions.assertLinesMatch(replace.process("aaaaddddyyyyzzzz"), List.of("cxy, dddd is replaced by c, yyyy is replaced by x, zzzz is replaced by y"));

        Assertions.assertLinesMatch(replace.process("abcccbad"), List.of(
                "abbbad, ccc is replaced by b",
                "aaad, bbb is replaced by a",
                "d"
        ));
    }
}