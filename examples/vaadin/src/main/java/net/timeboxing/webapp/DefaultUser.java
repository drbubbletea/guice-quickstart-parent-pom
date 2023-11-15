package net.timeboxing.webapp;

public class DefaultUser implements User {
    private final int id;
    public DefaultUser(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
