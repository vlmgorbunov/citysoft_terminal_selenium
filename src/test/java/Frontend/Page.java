package Frontend;
public enum Page {

    AUTH("https://devcs.avtodoria.ru/"),
    SECOND("second"),
    HOTKEYS("hotkeys"),
    ALERT("alert"),
    FRAMES("frames"),
    DRAG("drag");

    private final String name;

    Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}