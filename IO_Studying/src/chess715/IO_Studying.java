/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess715;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ayrat
 */
public class IO_Studying {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try (BufferedReader bufInput = new BufferedReader(new FileReader("src.txt"));
                BufferedWriter bufOutput = new BufferedWriter(new FileWriter("dest.txt"))) {

            String line = "";
            while ((line = bufInput.readLine()) != null) {
                bufOutput.write(line);
                bufOutput.newLine();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found: " + f);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

}
