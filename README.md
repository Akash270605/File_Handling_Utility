# File_Handling_Utility

Company: CODTECH IT SOLUTIONS

Name: Akash Kumar

Intern ID : CTIS7110

Domain: Java Programming

Duration: 4 Weeks

Mentor: Neela Santosh


**Project Description:**

This project demonstrates a robust and interactive implementation of file handling operations in Java using the modern java.nio package. It provides a menu-driven console application that allows users to perform essential file operations such as reading, writing, appending, modifying, and managing text files efficiently.

The application is designed with a modular and user-friendly approach, making it suitable for beginners learning file handling concepts as well as developers looking for a clean implementation of Java I/O operations.

🚀** Key Features:**

**1. Directory Management**

The application ensures that a dedicated working directory (fileFolder) exists before performing any file operations. If the directory is missing, it is automatically created using Files.createDirectories().

**2. File Reading**

Users can read the complete contents of a text file using a simple menu option. The program safely checks for file existence before attempting to read, preventing runtime errors.
File Writing (Overwrite Mode)
The system allows users to write multiple lines into a file. Existing content is overwritten, ensuring a fresh write operation using Files.write() with UTF-8 encoding.

**3. Appending Data**

Users can append new lines to an existing file without disturbing its current content. If the file does not exist, it is created automatically before appending.

**4. Text Modification (Search & Replace)**

A powerful feature that enables users to search for specific text within a file and replace it with new content. This is implemented by reading the file content, performing string replacement, and rewriting the updated content.

**5. Dynamic File Selection**

Users can change the target file within the working directory at runtime or locate an existing file using either a relative or absolute path.

**6. Multi-line Input Support**

The application supports multi-line input for writing operations, improving usability and flexibility.

🛠️ **Technical Highlights**

* Built using java.nio.file APIs (Path, Paths, Files) for efficient and modern file handling.
* Uses StandardCharsets.UTF_8 to ensure consistent character encoding.
* Implements StandardOpenOption for precise control over file operations such as appending and truncating.
* Follows modular programming practices with well-structured helper methods for each operation.
* Includes exception handling (try-catch) to gracefully manage I/O errors and invalid user inputs.

📚**Learning Outcomes**

**This project helps in understanding:**

* Difference between traditional java.io and modern java.nio APIs
* Safe file handling practices
* User input handling using Scanner
* Modular code design and separation of concerns
* Real-world implementation of CRUD operations on text files

**✅ Use Cases**
* Beginner-friendly project for learning Java file handling
* Base template for building CLI-based utilities
* Useful for text processing and file management tools
