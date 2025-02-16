package Methods;

import java.io.*;

public class DeleteCustomer {
    public static void deleteCustomer(String customerId) {
        File inputFile = new File("Customer.txt");
        File tempFile = new File("temp1.txt");

        if (!inputFile.exists()) {
            System.out.println("Customer record file not found.");
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
                if (!currentLine.startsWith(customerId + "::")) {
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
                System.out.println("Customer deleted successfully.");
            }
        } else {
            tempFile.delete(); // Clean up temp file if no flight was found
            System.out.println("Customer ID not found.");
        }
    }

}
