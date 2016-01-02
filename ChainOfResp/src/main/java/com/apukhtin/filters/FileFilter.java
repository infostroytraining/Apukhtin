package com.apukhtin.filters;

import java.io.File;

/**
 * Created by Vladyslav Apukhtin on 26.12.2015.
 * Enjoy.
 */
abstract public class FileFilter<T> {

    private FileFilter next;

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

}
