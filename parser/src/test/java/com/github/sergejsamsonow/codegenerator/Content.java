package com.github.sergejsamsonow.codegenerator;

import static org.junit.Assert.fail;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Simple helper for load test resources.
 */
public class Content {

    public static String of(String resource) {
        try {
            File file = new File(Content.class.getResource(resource).toURI());
            return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
        }
        catch (IOException | URISyntaxException e) {
            fail(String.format("Unable to load resource: [%s] Error: %s", resource, e.getMessage()));
            return "";
        }
    }
}
