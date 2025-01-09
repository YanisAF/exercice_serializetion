package org.example;

import java.io.Serializable;

public class Characters implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int strength;
    private int health;

    public Characters(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Characters [name=" + name + ", force=" + strength + ", health=" + health + "]";
    }
}
