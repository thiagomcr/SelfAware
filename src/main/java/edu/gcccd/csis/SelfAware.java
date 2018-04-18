package edu.gcccd.csis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class SelfAware implements Language {

    public static void main(String[] args) throws Exception {

        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));
    }

    @Override
    public int occurrences(final String sourceFile) throws Exception {
        final String classCode = new String(Files.readAllBytes(Paths.get(sourceFile)));
        String[] classReservedWords = classCode.split("\\b");
        Language.sort();
        int counter = 0;
        for (int i = 0; i < ReservedWords.length; i++) {
            for (int j = 0; j < classReservedWords.length; j++) {
                if (classReservedWords[j].equals(ReservedWords[i])) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public void append(final String sourceFile, final String s) throws IOException {
        Files.write(Paths.get(sourceFile), s.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }

}
//Keyword occurrences: 34