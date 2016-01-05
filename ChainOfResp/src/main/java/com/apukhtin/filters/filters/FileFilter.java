package com.apukhtin.filters.filters;

import java.io.File;

/**
 * Created by Vladyslav Apukhtin on 26.12.2015.
 * Enjoy.
 */
abstract public class FileFilter {

    private FileFilter next;
    private boolean isFilterEnabled = true;

    public FileFilter(FileFilter next) {
        this.next = next;
    }

    public boolean checkFile(File file) {
        if (next != null && checkCurrentFile(file)) {
            next.checkCurrentFile(file);
            return true;
        } else return false;
    }

    abstract public boolean checkCurrentFile(File file);

    public boolean isFilterEnabled() {
        return isFilterEnabled;
    }

    public void setFilterEnabled(boolean isFilterEnabled) {
        this.isFilterEnabled = isFilterEnabled;
    }
}
