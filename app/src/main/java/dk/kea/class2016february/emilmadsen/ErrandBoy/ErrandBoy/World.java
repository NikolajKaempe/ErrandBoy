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
    Coin coin;
    List<Car> lane1,lane2,lane3,lane4;
    private Sound wallImpactSound, carImpactSound, coinSound;
    float soundReset = 0;
    float timeCounter = 0.1f, timeAlive = 0;
    int coins = 0, niveau = 0;
    boolean niveauChanged = false, gameOver = false;

    public World(Game game)
    {
        this.game = game;
        errandBoy = new ErrandBoy(game.loadBitmap("walkingMan.png"));
        coin = new Coin(game.loadBitmap("coin.png"));
        lane1 = new ArrayList<>();
        lane2 = new ArrayList<>();
        lane3 = new ArrayList<>();
        lane4 = new ArrayList<>();
        wallImpactSound = game.loadSound("impactWall.wav");
        carImpactSound = game.loadSound("explosion.ogg");
        coinSound = game.loadSound("coin.wav");
        coin.respawnCoin(1);
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

        timeCounter = timeCounter + deltaTime;
        if (timeCounter >= 0.1)
        {
            timeAlive = timeAlive + 0.1f;
            timeCounter = 0.1f;
        }
        niveauChanged = false;
        if(niveau == 0) {  incrementNiveau();}
        coin.update(deltaTime);
        spawnCars();
        deSpawnCars();

        errandBoy.update(deltaTime);
        collideWalls(deltaTime);
        collideCars();
        collideCoin();

    }

    private void collideCoin()
    {
        if(collideRects(coin.x + ((40-coin.currentBitmap.width)/2), coin.y,
                coin.currentBitmap.width, coin.currentBitmap.height,
                errandBoy.x, errandBoy.y, errandBoy.currentBitmap.width, errandBoy.currentBitmap.height))
        {
            coin.respawnCoin(coins % 2);
            coins++;
            if (!game.isMuted()) { coinSound.play(1); }
            if (coins % 3 == 0)
            {
                incrementNiveau();
            }
        }
    }

    private void incrementNiveau()
    {
        if (niveau < 3)
        {
            niveau++;
            niveauChanged = true;
        }
    }

    private void collideCars()
    {
        for (Car car : lane1)
        {
            if(collideRects(car.x, car.y, car.currentBitmap.width, car.currentBitmap.height,
                    errandBoy.x+3, errandBoy.y+3, errandBoy.currentBitmap.width-6, errandBoy.currentBitmap.height-6))
            {
                gameOver = true;
                if (!game.isMuted()) { carImpactSound.play(1); }
            }
        }
        for (Car car : lane2)
        {
            if(collideRects(car.x, car.y, car.currentBitmap.width, car.currentBitmap.height,
                    errandBoy.x+3, errandBoy.y+3, errandBoy.currentBitmap.width-6, errandBoy.currentBitmap.height-6))
            {
                gameOver = true;
                if (!game.isMuted()) { carImpactSound.play(1); }
            }
        }
        for (Car car : lane3)
        {
            if (collideRects(car.x, car.y, car.currentBitmap.width, car.currentBitmap.height,
                    errandBoy.x+3, errandBoy.y+3, errandBoy.currentBitmap.width-6, errandBoy.currentBitmap.height-6))
            {
                gameOver = true;
                if (!game.isMuted()) { carImpactSound.play(1); }
            }
        }
        for (Car car : lane4)
        {
            if (collideRects(car.x, car.y, car.currentBitmap.width, car.currentBitmap.height,
                    errandBoy.x+3, errandBoy.y+3, errandBoy.currentBitmap.width-6, errandBoy.currentBitmap.height-6))
            {
                gameOver = true;
                if (!game.isMuted()) { carImpactSound.play(1); }
            }
        }

    }

    private boolean collideRects(float rect1X, float rect1Y, float rect1Width, float rect1Height,
                                 float rect2X, float rect2Y, float rect2Width, float rect2Height)
    {
        // Top and Bottom Collision
        if (rect1X < rect2X+rect2Width &&
                rect1X + rect1Width > rect2X &&
                rect1Y < rect2Y + rect2Height &&
                rect1Y + rect1Height > rect2Y)
        {
           return true;
        }
        return false;
    }

    private void collideWalls(float deltaTime)
    {
        //Right Wall
        if (errandBoy.x + errandBoy.currentBitmap.width > MAX_X)
        {
            errandBoy.x = MAX_X - errandBoy.currentBitmap.width;
            errandBoy.currentBitmap = errandBoy.moveLeft.getBitmap(0);
        }
        // Left Wall
        else if (errandBoy.x < 0)
        {
            errandBoy.x = 0;
            errandBoy.currentBitmap = errandBoy.moveRight.getBitmap(0);
        }
        // Lower Wall
        else if (errandBoy.y + errandBoy.currentBitmap.height > MAX_Y)
        {
            errandBoy.y = MAX_Y - errandBoy.currentBitmap.height;
            errandBoy.currentBitmap = errandBoy.moveUp.getBitmap(0);
        }
        //Upper wall
        else if (errandBoy.y < MIN_Y)
        {
            errandBoy.y = MIN_Y;
            errandBoy.currentBitmap = errandBoy.moveDown.getBitmap(0);
        }
        else { return; }

        errandBoy.move(MovingStates.Still);

        if (soundReset <= 0 && !game.isMuted()){
            soundReset = 0.5f;
            wallImpactSound.play(1);
        }
    }

    private void spawnCars() //could include deltaTime perhaps?
    {
        Car curCar;
        if (lane1.size() > 0)
        {
            curCar = lane1.get(lane1.size()-1); // last added car
            if (curCar.x > MIN_X + (3 * errandBoy.currentBitmap.width)) //makes min. distance between cars
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
            if (curCar.x > MIN_X + (3 * errandBoy.currentBitmap.width))
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
            if (curCar.x + curCar.currentBitmap.width < MAX_X - (3 * errandBoy.currentBitmap.width))
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
            if (curCar.x + curCar.currentBitmap.width < MAX_X - (3 * errandBoy.currentBitmap.width))
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
        int spawnValue = 10;
        int spawnRate;

        if (carType == 2 || carType == 3) // more cars in the fast lanes
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
            lane.add(new Car(carType,niveau));
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
            if (curCar.x <= MIN_X - curCar.currentBitmap.width){
                lane3.remove(0);
            }
        }
        if (lane4.size() > 0)
        {
            curCar = lane4.get(0);
            if (curCar.x <= MIN_X - curCar.currentBitmap.width){
                lane4.remove(0);
            }
        }
    }
}
