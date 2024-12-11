package com.adaptionsoft.games.trivia;


import org.junit.jupiter.api.Test;

import com.adaptionsoft.games.uglytrivia.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SomeTest {

    private static boolean notAWinner = true;

    @Test
    public void keepSameOutputWithSameinput() throws IOException {
        Game aGame = new Game();

        int[] rolls = {6,6,6,1,3,1,6,4,1,2,4,6,1,2,3,4,2,3,1,1,6,1,6,6,3,4,6,4,4,2,6,4,2,5,5,4,6,4,1,6,5,5,5,5,1,2,3,3,4,4};
        int[] answers = {6,9,2,4,7,0,5,1,0,2,8,4,2,3,8,1,2,5,1,7,8,4,5,7,8,1,3,1,9,2,9,6,2,2,1,0,2,6,1,2,8,9,7,1,3,5,2,5,1,3};
        int i = 0;
        // Read the expected output from a file
        String expectedOutput = new String(Files.readAllBytes(Paths.get("/home/arnaud/efrei/M2-Softwaretesting/Aldebaran-kata-trivia/reference/result.txt")), StandardCharsets.UTF_8);

        // Capture the actual output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        while (notAWinner) {
            aGame.roll(rolls[i]);
            if (answers[i] == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
            i++;
        };

        System.out.flush();
        System.setOut(old);

        String actualOutput = baos.toString(StandardCharsets.UTF_8.name());

        // Compare the actual output with the expected output
        assertEquals(expectedOutput, actualOutput);
        
    }
}
