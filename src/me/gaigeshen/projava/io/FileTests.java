package me.gaigeshen.projava.io;

import org.junit.Test;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @author gaigeshen
 */
public class FileTests {

  @Test
  public void createFile() throws IOException {
    // Using createNewFile() method
    // This method creates an empty file,
    // if the file doesn't exists at the specified location and returns true.
    // If the file is already present then this method returns false.
    File file = new File("c:/tmp/file-created-by-java.txt");
    boolean created = file.createNewFile();
    if (created) {
      System.out.println("Created because the file doesn't exists");
    } else {
      System.out.println("File already present");
    }
  }

  @Test
  public void readFileUsingBufferedInputStream() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileInputStream fin = new FileInputStream(file);
         BufferedInputStream bin = new BufferedInputStream(fin)) {
      while (bin.available() > 0) {
        System.out.println((char) bin.read());
      }
    }
  }

  @Test
  public void readFileUsingBufferedReader() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileReader reader = new FileReader(file);
         BufferedReader bReader = new BufferedReader(reader)) {
      String line = bReader.readLine();
      while (line != null) {
        System.out.println(line);
        line = bReader.readLine();
      }
    }
  }

  @Test
  public void writeFileUsingFileOutputStream() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileOutputStream out = new FileOutputStream(file)) {
      if (!file.exists()) {
        file.createNewFile();
      }
      // Replace the file content
      out.write("This is the fourth line".getBytes());
      out.flush();
    }
  }

  @Test
  public void writeFileUsingBufferedWriter() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileWriter fwriter = new FileWriter(file);
         BufferedWriter writer = new BufferedWriter(fwriter)) {
      if (!file.exists()) {
        file.createNewFile();
      }
      // Replace the file content
      writer.write("This is the another fourth line");
      writer.flush();
    }
  }

  @Test
  public void appendFileUsingBufferedWriter() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileWriter fwriter = new FileWriter(file, true);
         BufferedWriter writer = new BufferedWriter(fwriter)) {
      if (!file.exists()) {
        file.createNewFile();
      }
      // Append
      writer.write("\r\nThis is the new line appended");
      writer.flush();
    }
  }

  @Test
  public void appendFileUsingPrintWriter() throws IOException {
    File file = new File("c:/tmp/file-created-by-java.txt");
    try (FileWriter fw = new FileWriter(file, true);
         BufferedWriter writer = new BufferedWriter(fw);
         PrintWriter pw = new PrintWriter(writer)) {
      pw.println("");
      pw.println("This is the first line");
      pw.println("This is the second line");
      pw.println("This is the third line");
    }
  }

  @Test
  public void renameFile() {
    File file = new File("c:/tmp/file-created-by-java.txt");
    File newFile = new File("c:/tmp/file-created-by-java-renamed.txt");
    if (file.renameTo(newFile)) {
      System.out.println("File renamed");
    } else {
      System.out.println("Could not rename file");
    }
  }

  @Test
  public void compressFileInGZIP() throws IOException {
    try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream("c:/tmp/file-created-by-java-renamed.txt.gz"));
         FileInputStream in = new FileInputStream("c:/tmp/file-created-by-java-renamed.txt")) {
      byte[] buffer = new byte[1024];
      int length;
      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
      out.finish();
    }
  }

  @Test
  public void deleteFile() {
    if (new File("c:/tmp/file-created-by-java-renamed.txt").delete()) {
      System.out.println("Deleted");
    } else {
      System.out.println("Un deleted");
    }
  }
}
