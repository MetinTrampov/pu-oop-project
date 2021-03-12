package game;

import java.awt.*;

public class Model {
    Color color ;
    int finalStatus = 0;

    public int attack;
    public int armor;
    public int health;
    public int attack_square;
    public int speed;
    public int team;

    public  Model()
    {}
    public Model(Color color)
    {
        this.color = color;
    }
    public Model(Color color, int state )
    {
        this.color = color;
    }
    public Model(int attack, int armor, int health, int attack_square, int speed )
    {
        this.attack = attack;
        this.armor=armor;
        this.health = health;
        this.attack_square = attack_square;
        this.speed =speed;
    }
    public Model (int team)
    {
        this.team = team;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack_square() {
        return attack_square;
    }
}
