package Methods;

import java.io.*;

/**
 * The {@code DeleteCustomer} class is responsible for deleting a customer record from the customer data file.
 * It searches for a customer with the specified customer ID and deletes the matching entry if found.
 * If the customer is successfully deleted, the file is updated; otherwise, a message is shown indicating the failure.
 */
public class DeleteCustomer {
    
    /**
     * Deletes a customer record from the {@code Customer.txt} file based on the provided customer ID.
     * The method reads through the customer data and removes the matching record if found.
     * After deletion, the file is updated with the remaining records.
     *
     * @param customerId the customer ID of the record to be deleted
     */
    public static void deleteCustomer(String customerId) {
        File inputFile = new File("Customer.txt");
        File tempFile = new File("temp1.txt");

        // Check if the customer record file exists
        if (!inputFile.exists()) {
            System.out.println("Customer record file not found.");
            return;
        }

        boolean found = false;

        // Read the file and write to a temporary file, excluding the customer to be deleted
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // If the current line does not match the customer ID, write it to the temp file
                if (!currentLine.startsWith(customerId + "::")) {
                    writer.write(currentLine);
                    writer.newLine();
                } else {
                    found = true; // Mark that the customer was found and removed
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the customer was found and deleted, update the file
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
            tempFile.delete(); // Clean up temp file if customer was not found
            System.out.println("Customer ID not found.");
        }
    }
}
