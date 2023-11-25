/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package removeduplicatefiles;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hp
 */
public class RemoveDuplicateFiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String dir = "src\\ujicoba";

        deleteDuplicates(dir);

        // Path file1 = Paths.get("D:\\Documents\\ITS\\SEMESTER1\\PBO\\TUGAS\\ujicoba\\JP_5_2_sg.pdf");
        // Path file2 = Paths.get("D:\\Documents\\ITS\\SEMESTER1\\PBO\\TUGAS\\ujicoba\\JP_5_2_sg - Copy.pdf");
        // String path1 = "D:\\Documents\\ITS\\SEMESTER1\\PBO\\TUGAS\\ujicoba\\JP_5_2_sg.pdf";
        // String path2 = "D:\\Documents\\ITS\\SEMESTER1\\PBO\\TUGAS\\ujicoba\\JP_5_2_sg - Copy.pdf";
        // System.out.println(checkDuplicates(path1, path2));
    }

    private static void deleteDuplicates(String dir) throws IOException {
        Path dirPath = Paths.get(dir);

        List<Path> arrFiles = Files.list(dirPath).toList();
        arrFiles = new ArrayList<>(arrFiles);

        for (int i = 0; i < arrFiles.size() - 1; i++) {
            int j = i + 1;
            Path pathFile1 = arrFiles.get(i);
            while (j <= arrFiles.size() - 1) {
                boolean isDuplicate = checkDuplicates(pathFile1, arrFiles.get(j));
                if (isDuplicate) {
                    Files.deleteIfExists(arrFiles.get(j));
                    arrFiles.remove(j);
                } else {
                    j++;
                }
            }
        }
    }

    private static boolean checkDuplicates(Path file1, Path file2) throws IOException {
        String bytes1 = Arrays.toString(Files.readAllBytes(file1));
        String bytes2 = Arrays.toString(Files.readAllBytes(file2));

        return bytes1.equals(bytes2);
    }

    private static boolean checkDuplicates(String file1, String file2) throws FileNotFoundException, IOException {
        DataInputStream reader1 = new DataInputStream(new FileInputStream(file1));
        DataInputStream reader2 = new DataInputStream(new FileInputStream(file2));
        int nbread1 = reader1.available();
        int nbread2 = reader2.available();
        if (nbread1 != nbread2) {
            return false;
        }
        if (nbread1 > 0) {
            String strfile1 = Arrays.toString(reader1.readAllBytes());
            reader1.close();
            String strfile2 = Arrays.toString(reader2.readAllBytes());
            reader2.close();
            return strfile1.equals(strfile2);
        }
        return false;
    }

}
