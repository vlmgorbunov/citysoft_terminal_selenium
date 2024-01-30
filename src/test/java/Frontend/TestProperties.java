package Frontend;

import ru.qatools.properties.DefaultValue;
import ru.qatools.properties.Property;

interface TestProperties {

    @Property("pages.base.url")
    @DefaultValue("")
    String getBaseUrl();

    @Property("grid.connection.url")
    @DefaultValue("")
    String getConnectionUrl();

    @Property("grid.browser.name")
    @DefaultValue("firefox")
    String getBrowserName();

    @Property("grid.browser.version")
    @DefaultValue("52.0")
    String getBrowserVersion();
}