package net.timeboxing.resource.test;

import net.timeboxing.resource.ClassResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Intentionally in a package different from ClassResource to ensure
 * we get the package of this class and not the package of classpath resource.
 */
class ClassResourceTest {

    @Test
    void canGetByPackageResourceFolder() {
        String value = ClassResource.get("test.txt");
        Assertions.assertEquals("lorem ipsum", value);
    }
}
