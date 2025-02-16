package Methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CancelFlight {

	public static void deleteFlight(String flightId) {
        File inputFile = new File("Bookings.txt");
        File tempFile = new File("temp.txt");

        if (!inputFile.exists()) {
            System.out.println("Flight record file not found.");
            return;
        }

        boolean found = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line contains the flight ID
                if (!currentLine.startsWith(flightId + "::")) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    found = true; // Mark that the flight was found and removed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (found) {
            if (!inputFile.delete()) {
                System.out.println("Could not delete original file");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename temp file");
            } else {
                System.out.println("Flight deleted successfully.");
            }
        } else {
            tempFile.delete(); // Clean up temp file if no flight was found
            System.out.println("Flight ID not found.");
        }
    }

}
