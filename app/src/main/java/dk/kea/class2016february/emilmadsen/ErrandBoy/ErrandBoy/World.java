package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Sound;

/**
 * Created by Nikol_000 on 28-04-2016.
 */
public class World
{
    private Game game;
    public static final float MIN_X = 0,MAX_X = 710,MIN_Y = 25,MAX_Y = 375;
    ErrandBoy errandBoy;
    List<Car> lane1,lane2,lane3,lane4;
    private Sound wallImpactSound;
    float soundReset = 0;

    public World(Game game)
    {
        this.game = game;
        errandBoy = new ErrandBoy(game.loadBitmap("walkingMan.png"));
        lane1 = new ArrayList<>();
        lane2 = new ArrayList<>();
        lane3 = new ArrayList<>();
        lane4 = new ArrayList<>();
        wallImpactSound = game.loadSound("impactWall.wav");
    }

    public void update(float deltaTime)
    {
        for (Car c : lane1)
        {
            c.update(deltaTime);
        }
        for (Car c : lane2)
        {
            c.update(deltaTime);
        }
        for (Car c : lane3)
        {
            c.update(deltaTime);
        }
        for (Car c : lane4)
        {
            c.update(deltaTime);
        }
        if (soundReset > 0) { soundReset = soundReset - deltaTime; }

        spawnCars();
        deSpawnCars();

        errandBoy.update(deltaTime);
        collideWalls(deltaTime);
        collideCars();

    }

    private void collideCars()
    {

    }

    private void collideWalls(float deltaTime)
    {
        //Right Wall
        if (errandBoy.x + errandBoy.currentBitmap.getWidth() > MAX_X)
        {
            errandBoy.x = MAX_X - errandBoy.currentBitmap.getWidth();
            errandBoy.currentBitmap = errandBoy.moveLeft.getBitmap(1);
        }
        // Left Wall
        else if (errandBoy.x < 0)
        {
            errandBoy.x = 0;
            errandBoy.currentBitmap = errandBoy.moveRight.getBitmap(1);
        }
        // Lower Wall
        else if (errandBoy.y + errandBoy.currentBitmap.getHeight() > MAX_Y)
        {
            errandBoy.y = MAX_Y - errandBoy.currentBitmap.getHeight();
            errandBoy.currentBitmap = errandBoy.moveUp.getBitmap(1);
        }
        //Upper wall
        else if (errandBoy.y < MIN_Y)
        {
            errandBoy.y = MIN_Y;
            errandBoy.currentBitmap = errandBoy.moveDown.getBitmap(1);
        }
        else { return; }

        errandBoy.move(MovingStates.Still);

        if (soundReset <= 0){
            soundReset = 0.5f;
            wallImpactSound.play(1);
        }
    }

    private void spawnCars()
    {
        Car curCar;
        if (lane1.size() > 0)
        {
            curCar = lane1.get(lane1.size()-1);
            if (curCar.x > MIN_X + (2 * errandBoy.currentBitmap.getWidth()))
            {
                spawnCar(1,lane1);
            }
        }else
        {
            spawnCar(1,lane1);
        }
        if (lane2.size() > 0)
        {
            curCar = lane2.get(lane2.size()-1);
            if (curCar.x > MIN_X + (2 * errandBoy.currentBitmap.getWidth()))
            {
                spawnCar(2,lane2);
            }
        }else
        {
            spawnCar(2,lane2);
        }
        if (lane3.size() > 0)
        {
            curCar = lane3.get(lane3.size()-1);
            if (curCar.x + curCar.currentBitmap.getWidth() < MAX_X - (2* errandBoy.currentBitmap.getWidth()))
            {
                spawnCar(3,lane3);
            }
        }else
        {
            spawnCar(3,lane3);
        }
        if (lane4.size() > 0)
        {
            curCar = lane4.get(lane4.size()-1);
            if (curCar.x + curCar.currentBitmap.getWidth() < MAX_X - (2* errandBoy.currentBitmap.getWidth()))
            {
                spawnCar(4,lane4);
            }
        }else
        {
            spawnCar(4,lane4);
        }
    }

    private void spawnCar(int carType, List<Car> lane)
    {
        int spawnValue = 5;
        int spawnRate;

        if (carType == 2 || carType == 3)
        {
            spawnRate = 750;
        }
        else
        {
            spawnRate = 1000;
        }

        int value = (int)(Math.random() * spawnRate);

        if (value <= spawnValue)
        {
            lane.add(new Car(carType));
        }
    }

    private void deSpawnCars()
    {
        Car curCar;
        if (lane1.size() > 0)
        {
            curCar = lane1.get(0);
            if (curCar.x > MAX_X){
                lane1.remove(0);
            }
        }
        if (lane2.size() > 0)
        {
            curCar = lane2.get(0);
            if (curCar.x > MAX_X){
                lane2.remove(0);
            }
        }
        if (lane3.size() > 0)
        {
            curCar = lane3.get(0);
            if (curCar.x <= MIN_X - curCar.currentBitmap.getWidth()){
                lane3.remove(0);
            }
        }
        if (lane4.size() > 0)
        {
            curCar = lane4.get(0);
            if (curCar.x <= MIN_X - curCar.currentBitmap.getWidth()){
                lane4.remove(0);
            }
        }

    }
}
