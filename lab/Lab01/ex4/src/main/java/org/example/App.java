package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print("Please specify an URL to a file");
            return;
        }
        String inputURL = args[0];
        if (UrlValidator.getInstance().isValid(inputURL)) {
            try {
                URL url = new URL(inputURL);
                String filename = FilenameUtils.getName(url.getPath());
                File destination = new File(System.getProperty("user.dir") + "\\" + filename);
                if (!destination.exists()) {
                    destination.createNewFile();
                }
                FileUtils.copyURLToFile(url, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("This is not a valid URL");
        }
    }
}
