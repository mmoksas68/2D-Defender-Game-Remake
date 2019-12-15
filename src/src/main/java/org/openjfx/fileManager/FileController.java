package org.openjfx.fileManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileController {
    public static FileInputStream getFileStream( String url) throws FileNotFoundException {
        return new FileInputStream(url);
    }
}
