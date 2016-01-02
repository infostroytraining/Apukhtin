package com.apukhtin.filters;

import com.google.common.io.Files;

import java.io.File;


/**
 * Created by Vladyslav Apukhtin on 02.01.2016.
 * Enjoy.
 */
public class NameFileFilter extends FileFilter {

    protected String filenameTemplate;

    public NameFileFilter(FileFilter next, String filenameTemplate) {
        super(next);
        this.filenameTemplate = Files.getNameWithoutExtension(filenameTemplate);
    }

    @Override
    public boolean checkCurrentFile(File file) {
        return filenameTemplate.equals(file.getName());
    }
}
