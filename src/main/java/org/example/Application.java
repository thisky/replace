package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        var reader = new BufferedReader(new InputStreamReader(System.in));

        var tips = """
                Example:
                Input: aabcccbbad
                Output:
                -> aabbbad
                -> aaad
                -> d
                """;
        System.out.println(tips);
        process(reader, new SimpleReplace());

        tips = """
                                
                                
                Example:
                 ccc -> b
                 bbb -> a
                 Input: abcccbad
                 Output:
                 -> abbbad, ccc is replaced by b
                 -> aaad, bbb is replaced by a
                 -> d
                """;
        System.out.println(tips);
        process(reader, new AdvanceReplace());

    }

    /**
     * process input output
     *
     * @param reader  console input
     * @param replace replace handler
     */
    public static void process(BufferedReader reader, Replace replace) {
        while (true) {
            try {
                String line = reader.readLine();
                if ("".equals(line)) {
                    break;
                }
                System.out.println("Input:");
                var output = replace.process(line.strip());
                System.out.println("Output:");
                output.forEach(row -> System.out.println("-> " + row));
                System.out.println("Press Enter to end");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
