

import org.junit.Assert;
import org.junit.Test;

import java.util.ResourceBundle;

/**
 * The Class PropertiesTest.
 */
public class PropertiesTest {

    /**
     * Database properties exist.
     */
    @Test
    public void databasePropertiesExist() {
        Assert.assertNotNull(ResourceBundle.getBundle("config"));
    }

    /**
     * PageContent properties exist.
     */
    @Test
    public void pageContentPropertiesExist() {
        Assert.assertNotNull(ResourceBundle.getBundle("messages"));
    }
}