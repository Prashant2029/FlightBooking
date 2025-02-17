package Methods;

import java.io.*;

/**
 * The {@code DeleteFlight} class is responsible for deleting a flight record from the flight data file.
 * It searches for a flight with the specified flight ID and deletes the matching entry if found.
 * After deletion, the file is updated with the remaining records.
 */
public class DeleteFlight {
    
    /**
     * Deletes a flight record from the {@code Flight.txt} file based on the provided flight ID.
     * The method reads through the flight data and removes the matching flight if found.
     * After deletion, the file is updated with the remaining records.
     *
     * @param flightId the flight ID of the record to be deleted
     */
    public static void deleteFlight(String flightId) {
        File inputFile = new File("Flight.txt");
        File tempFile = new File("temp.txt");

        // Check if the flight record file exists
        if (!inputFile.exists()) {
            System.out.println("Flight record file not found.");
            return;
        }

        boolean found = false;

        // Read the file and write to a temporary file, excluding the flight to be deleted
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // If the current line does not match the flight ID, write it to the temp file
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

        // If the flight was found and deleted, update the file
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
            tempFile.delete(); // Clean up temp file if flight was not found
            System.out.println("Flight ID not found.");
        }
    }
}
