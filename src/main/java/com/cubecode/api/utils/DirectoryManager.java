package com.cubecode.api.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Set;

public abstract class DirectoryManager {
    protected final File directory;

    public DirectoryManager(File directory) {
        this.directory = directory;
        this.directory.mkdirs();
    }

    public File getFile(String name) {
        return new File(directory, name);
    }

    public Set<File> getFiles() {
        return Set.of(Objects.requireNonNull(this.directory.listFiles()));
    }

    public String readFileString(String name) {
        try {
            return FileUtils.readFileToString(this.getFile(name), Charset.defaultCharset());
        } catch (IOException ignored) {
            return "";
        }
    }

    public boolean deleteFile(String name) {
        return this.getFile(name).delete();
    }

    public boolean fileExist(String name) {
        return this.getFile(name).exists();
    }

    public boolean renameFile(String name, String newName) {
        File file = this.getFile(name);

        return file.renameTo(this.getFile(newName));
    }

    public File getDirectory() {
        return this.directory;
    }
}