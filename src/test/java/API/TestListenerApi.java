package API;

import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestListenerApi implements TestWatcher {
    APITest apiTest;

    @SneakyThrows
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        apiTest = new APITest();
    }

    @SneakyThrows
    @Override
    public void testSuccessful(ExtensionContext context) {
        apiTest = new APITest();
    }
}
