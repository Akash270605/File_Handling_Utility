/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package file_handling;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class File_Handling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Use a dedicated folder 
        Path dirDemo = Paths.get("fileFolder");
        Path filePath = dirDemo.resolve("file1.txt");
        
        try{
            ensureDirectoryExists(dirDemo);
        }catch(IOException e){
            System.err.println("Unable to create folder: " + e.getMessage());
            return;
        }
        
        System.out.println("File Operations");
        System.out.println("Default file: " + filePath.toAbsolutePath());
        System.out.println();
        
        try(Scanner scanner = new Scanner(System.in)){
            boolean running = true;
            while(running){
                printMenu();
                String choice = scanner.nextLine().trim();
                
                switch(choice){
                    case "1":
                        handleRead(filePath);
                        break;
                        
                    case "2": 
                        handleWrite(scanner, filePath);
                        break;
                        
                    case "3":
                        handleAppend(scanner, filePath);
                        break;
                        
                    case "4":
                        handleModify(scanner, filePath);
                        break;
                        
                    case "5":
                        filePath = handleChangeFile(scanner, dirDemo, filePath);
                        break;
                        
                    case "6":
                        filePath = handleLocateExistingFile(scanner, dirDemo, filePath);
                        break;
                        
                    case "0":
                        running = false;
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                System.out.println();
            }
        }
    }
    
    // Ensure a directory exists; creates if missing
    private static void ensureDirectoryExists(Path dir) throws IOException{
        if(Files.notExists(dir)){
            Files.createDirectories(dir);
        }
    }
    
    // Writes the given lines to a text fiile, overwritese if already exists
    private static void writeTextFile(Path path, List<String> lines) throws IOException{
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    
    // Reads the full text content of a file
    private static String readTextFile(Path path) throws IOException{
        return Files.readString(path, StandardCharsets.UTF_8);
    }
    
    // Replaces all occurences of searchText with replacement text
    private static void replaceTextInFile(Path path, String searchText, String replacementText) throws IOException{
        String content = readTextFile(path);
        String updated = content.replace(searchText, replacementText);
        Files.writeString(path, updated, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    // Append a new line to the end of a file
    private static void appendLineToFile(Path path, String line) throws IOException{
        Files.writeString(path, line + System.lineSeparator(), 
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND);
    }
    
    // menu
    private static void printMenu(){
        System.out.println("Choose an option:");
        System.out.println("1. Read file");
        System.out.println("2. Write file (overwrites)");
        System.out.println("3. Append line");
        System.out.println("4. Modify text (replace)");
        System.out.println("5. Change target file");
        System.out.println("6. Locate existing file");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }
    
    private static void handleRead(Path filePath){
        try{
            if(Files.notExists(filePath)){
                System.out.println("File doesn't exist yet: " + filePath.toAbsolutePath());
                return;
            }
            
            System.out.println("=== File Contents ===");
            System.out.println(readTextFile(filePath));
        }catch(IOException e){
            System.err.println("Read failed: " + e.getMessage());
        }
    }
    
    private static void handleWrite(Scanner scanner, Path filePath){
        try{
            List<String> lines = readMultiLineInput(scanner, "Enter text (blank line to finish): ");
            writeTextFile(filePath, lines);
            System.out.println("File written: " + filePath.toAbsolutePath());
        }catch(IOException e){
            System.err.println("Write failed: " + e.getMessage());
        }
    }
    
    private static void handleAppend(Scanner scanner, Path filePath){
        try{
            System.out.print("Enter line to append: ");
            String line = scanner.nextLine();
            if(Files.notExists(filePath)){
                System.out.println("File does not exist yer. Creating a new one.");
                writeTextFile(filePath, List.of());
            }
            
            appendLineToFile(filePath, line);
            System.out.println("Line appended.");
        }catch(IOException e){
            System.err.println("Append failed: " + e.getMessage());
        }
    }
    
    private static void handleModify(Scanner scanner, Path filePath){
        try{
            if(Files.notExists(filePath)){
                System.out.println("File does not exits yet: " + filePath.toAbsolutePath());
                return;
            } 
            
            System.out.print("Find text: ");
            String searchText = scanner.nextLine();
            System.out.print("Replace with: ");
            String replacementText = scanner.nextLine();
            replaceTextInFile(filePath, searchText, replacementText);
            System.out.println("Text replaced..");
        }catch(IOException e){
            System.err.println("Modify failed: " + e.getMessage());
        }
    }
    
    private static Path handleChangeFile(Scanner scanner, Path dirDemo, Path currentFile){
        System.out.println("Current file: " + currentFile.toAbsolutePath());
        System.out.print("Enter a new file name (inside fileFolder): ");
        String filename = scanner.nextLine().trim();
        
        if(filename.isEmpty()){
            System.out.println("No change made.");
            return currentFile;
        }
        
        Path newPath = dirDemo.resolve(filename);
        System.out.println("Target file set to: " + newPath.toAbsolutePath());
        return newPath;
    }
    
    // Locates an existingfile
    private static Path handleLocateExistingFile(Scanner scanner, Path dirDemo, Path currentFile){
        System.out.println("Current file: " + currentFile.toAbsolutePath());
        System.out.print("Enter existing file name or full path: ");
        String input = scanner.nextLine().trim();
        
        if(input.isEmpty()){
            System.out.println("No change made..");
            return currentFile;
        }
        
        Path candidate = Paths.get(input);
        if(!candidate.isAbsolute()){
            candidate = dirDemo.resolve(input);
        }
        
        if(Files.exists(candidate) && Files.isRegularFile(candidate)){
            System.out.println("Found file: " + candidate.toAbsolutePath());
            return candidate;
        }
        
        System.out.println("File not found: " + candidate.toAbsolutePath());
        return currentFile;
    }
    
    // Reads multiple lines from the usr until a blank line is entered
    private static List<String> readMultiLineInput(Scanner scanner, String prompt){
        System.out.println(prompt);
        List<String> lines = new ArrayList<>();
        while(true){
            String line = scanner.nextLine();
            if(line.isEmpty()){
                break;
            }
            lines.add(line);
        }
        
        return lines;
    }
}
