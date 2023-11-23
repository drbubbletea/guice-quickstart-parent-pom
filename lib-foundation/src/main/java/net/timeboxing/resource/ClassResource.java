package net.timeboxing.resource;


import java.io.IOException;
import java.io.InputStream;

public class ClassResource {

    private ClassResource() {
        /* NOOP */
    }

    public static String get(String filename) {
        try  (InputStream is = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName()).getResourceAsStream(filename)) {
            return new String(is.readAllBytes());
        } catch (ClassNotFoundException | IOException | NullPointerException e) {
            throw new ClassResourceNotFoundException("Unable to find class resource file with name: " + filename, e);
        }
    }
}
