package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String DEFAULT_FILE = "output.txt";

    private File dest = new File(HOME + File.separator + DEFAULT_FILE);

    public void setDestination(final File file){
        if(file.exists()){
            dest = file;
        }
        else{
            throw new IllegalArgumentException("This Folder doesn't exist");
        }
    }

    public File getCurrentFile(){
        return dest;
    }

    public String getCurrentFilePath(){
        return dest.getPath();
    }

    public void save(final String input) throws IOException{
        try (PrintStream ps = new PrintStream(dest, StandardCharsets.UTF_8)){
            ps.println(input);
        }

    }
}
