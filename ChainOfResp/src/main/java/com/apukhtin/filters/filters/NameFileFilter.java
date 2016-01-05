package com.apukhtin.filters.filters;

import com.google.common.io.Files;

import java.io.File;


/**
 * Created by Vladyslav Apukhtin on 02.01.2016.
 * Enjoy.
 */
public class NameFileFilter extends FileFilter {

    protected String filenameTemplate;

    public NameFileFilter() {
        super(null);
        setFilterEnabled(false);
    }

    public NameFileFilter(FileFilter next) {
        super(next);
        setFilterEnabled(false);
    }

    public NameFileFilter(FileFilter next, String filenameTemplate) {
        super(next);
        this.filenameTemplate = Files.getNameWithoutExtension(filenameTemplate);
    }

    @Override
    public boolean checkCurrentFile(File file) {
        // if filter is disabled then it always returns true
        // in this way I skip files;
        return !isFilterEnabled() || filenameTemplate.equals(file.getName());
    }
}
