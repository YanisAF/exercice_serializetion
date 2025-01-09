package org.example;

import java.io.Serializable;

public class Monsters implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int strength;
    private int health;

    public Monsters(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Monsters [name=" + name + ", force=" + strength + ", health=" + health + "]";
    }
}
