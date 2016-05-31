package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;

/**
 * Created by Nikol_000 on 28-04-2016.
 */
public class WorldRenderer
{
    World world;
    Game game;
    Bitmap cars;
    public WorldRenderer(World world, Game game)
    {
        this.world = world;
        this.game = game;
        cars = game.loadBitmap("cars.png");
    }

    public void render()
    {
        for (Car c : world.lane1){
            game.drawBitmap(cars,(int)c.x,(int)c.y,
                    c.currentBitmap.srcX,c.currentBitmap.srcY,
                    c.currentBitmap.width,c.currentBitmap.height);
        }
        for (Car c : world.lane2){
            game.drawBitmap(cars, (int) c.x, (int) c.y,
                    c.currentBitmap.srcX, c.currentBitmap.srcY,
                    c.currentBitmap.width, c.currentBitmap.height);
        }
        for (Car c : world.lane3){
            game.drawBitmap(cars, (int) c.x, (int) c.y,
                    c.currentBitmap.srcX, c.currentBitmap.srcY,
                    c.currentBitmap.width, c.currentBitmap.height);
        }
        for (Car c : world.lane4){
            game.drawBitmap(cars, (int) c.x, (int) c.y,
                    c.currentBitmap.srcX, c.currentBitmap.srcY,
                    c.currentBitmap.width, c.currentBitmap.height);
        }

        game.drawBitmap(world.coin.picture,world.coin.x + ((40-world.coin.currentBitmap.width)/2),world.coin.y,
                world.coin.currentBitmap.srcX,world.coin.currentBitmap.srcY,
                world.coin.currentBitmap.width,world.coin.currentBitmap.height);

        game.drawBitmap(world.errandBoy.picture,(int)world.errandBoy.x,(int)world.errandBoy.y,
                world.errandBoy.currentBitmap.srcX,world.errandBoy.currentBitmap.srcY,
                world.errandBoy.currentBitmap.width,world.errandBoy.currentBitmap.height);
    }
}
