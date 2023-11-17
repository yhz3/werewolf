package entity;

public abstract class Player {
    private String name;
    private String role;
    private boolean alive;

    public Player(String name, String role) {
        this.name = name;
        this.alive = true;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}