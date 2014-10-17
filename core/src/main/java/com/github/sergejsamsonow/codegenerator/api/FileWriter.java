package com.github.sergejsamsonow.codegenerator.api;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class FileWriter implements WriterAccess {

    private String rootFolder;

    public FileWriter(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    @Override
    public void write(String subpath, String code) {
        String file = FilenameUtils.concat(rootFolder, subpath);
        File directory = new File(FilenameUtils.getFullPathNoEndSeparator(file));
        directory.mkdirs();
        try {
            FileUtils.writeStringToFile(new File(file), code);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
