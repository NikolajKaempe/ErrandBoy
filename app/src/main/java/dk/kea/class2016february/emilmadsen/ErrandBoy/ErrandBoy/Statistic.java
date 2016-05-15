package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import java.io.Serializable;

/**
 * Created by Nikol_000 on 11-05-2016.
 */
public class Statistic implements Serializable
{
    private String name;
    private int coins;
    private float timeAlive;

    public Statistic(String name, int coins, float timeAlive)
    {
        this.name = name;
        this.coins = coins;
        this.timeAlive = timeAlive;
    }

    public String getName()
    {
        return name;
    }

    public int getCoins()
    {
        return coins;
    }

    public float getTimeAlive()
    {
        return timeAlive;
    }
}
