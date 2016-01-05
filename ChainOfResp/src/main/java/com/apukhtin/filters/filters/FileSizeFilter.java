package com.apukhtin.filters.filters;

import java.io.File;
import java.nio.file.Files;

/**
 * Created by Vladyslav Apukhtin on 05.01.2016.
 * Enjoy.
 */
public class FileSizeFilter extends FileFilter {

    private long upperSize;
    private long downSize;

    public FileSizeFilter() {
        super(null);
        setFilterEnabled(false);
    }

    public FileSizeFilter(FileFilter next) {
        super(next);
        setFilterEnabled(false);
    }

    public FileSizeFilter(FileFilter next, long upperSize, long downSize) {
        super(next);
        this.upperSize = upperSize;
        this.downSize = downSize;
    }

    public void setUpperSize(long upperSize) {

        this.upperSize = upperSize;
    }

    public void setDownSize(long downSize) {
        this.downSize = downSize;
    }

    @Override
    public boolean checkCurrentFile(File file) {
        long size = Files.size(file.toPath());

        return !isFilterEnabled() ||
                downSize <= size && size <= upperSize;
    }
}
