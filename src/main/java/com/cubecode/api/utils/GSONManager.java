package com.cubecode.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class GSONManager {
    protected final File FILE;
    protected final Gson GSON;

    public GSONManager(File file) {
        this.FILE = file;

        try {
            this.FILE.createNewFile();
        } catch (IOException ignored) {}

        this.GSON = new GsonBuilder().setPrettyPrinting().create();
    }

    public File getFile() {
        return this.FILE;
    }

    public void writeJSON() {
        try (FileWriter writer = new FileWriter(this.FILE)) {
            this.GSON.toJson(this, writer);
        } catch (IOException ignored) {}
    }
}
