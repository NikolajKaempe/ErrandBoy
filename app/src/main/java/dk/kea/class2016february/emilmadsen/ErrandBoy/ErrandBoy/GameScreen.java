package dk.kea.class2016february.emilmadsen.ErrandBoy.ErrandBoy;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import dk.kea.class2016february.emilmadsen.ErrandBoy.Game;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Music;
import dk.kea.class2016february.emilmadsen.ErrandBoy.Screen;
import dk.kea.class2016february.emilmadsen.ErrandBoy.TouchEvent;

public class GameScreen extends Screen
{
    private MainMenuScreen mainMenuScreen; //TODO to use when gameOver to not make a new MainMenuScreen
    private Bitmap background, soundIcon;
    private World world;
    private WorldRenderer renderer;
    private Music music;
    private List<Statistic> statistics = new ArrayList<>();
    private GameState gameState = GameState.Running;
    private int touchX, touchY, transparency = 5;
    private float textTimeReset = 0.05f, textTimeCount = textTimeReset;
    private boolean drawNiveau = false, niveauFadeIn = true;

    enum GameState
    {
        Running,
        Paused,
        gameOver
    }
    public GameScreen(Game game, MainMenuScreen mainMenuScreen)
    {
        super(game);
        world = new World(game);
        renderer = new WorldRenderer(world,game);
        this.mainMenuScreen = mainMenuScreen;
        background = game.loadBitmap("background.png");
        soundIcon = game.loadBitmap("soundIcon.png");
        music = game.loadMusic("mainmenu.mp3");
        music.setLooping(true);
        statistics.add(new Statistic("Emil", 25, 50.5f));
        statistics.add(new Statistic("Nikolaj",55,100.9f));
    }

    @Override
    public void update(float deltaTime)
    {
        touchX =-1;
        touchY =-1;
        System.out.println("FFFFFFFFFPPPPPPPSSSSSSSS: " + game.getFrameRate());

        if (gameState == GameState.Paused && isTouchReleased())
        {
            gameState = GameState.Running;
        }
        if (gameState == GameState.gameOver && isTouchReleased())
        {
            if (game.getTouchX(0) >= 100 && game.getTouchX(0) <= 620
                    && game.getTouchY(0) >= 170 && game.getTouchY(0) <= 255)
            {
                game.setScreen(new GameScreen(game,mainMenuScreen));
            }
            if (game.getTouchX(0) >= 110 && game.getTouchX(0) <= 610
                    && game.getTouchY(0) >= 150 && game.getTouchY(0) <= 230)
            {
                game.setScreen(mainMenuScreen);

            }
        }

        if (gameState == GameState.Running)
        {
            if (!music.isPlaying() && !game.isMuted())
            {
                music.play();
            }

            if (game.isTouchDown(0) && world.errandBoy.movingState == MovingStates.Still){
                touchX = game.getTouchX(0);
                touchY = game.getTouchY(0);
                tryMove();
            }

            if (isTouchReleased()) //Toggle mute
            {
                if (game.getTouchX(0) >= 720-100 && game.getTouchY(0) <= 70)
                {
                    if (game.isMuted())
                    {
                        game.toggleMuted();
                        music.play();
                    }
                    else
                    {
                        game.toggleMuted();
                        music.dispose();
                        music = game.loadMusic("mainmenu.mp3");
                    }
                }
            }
            world.update(deltaTime);

            if (world.gameOver)
            {
                gameState = GameState.gameOver;
                statistics.add(new Statistic("PLAYER NAME",world.coins,world.timeAlive));
                world.gameOver = false;
            }
        }

        //TODO ALWAYS DRAW Following??
        game.drawBitmap(background, 0, 0);
        renderer.render();

        game.drawText(game.loadFont("game-font.ttf"), "Coins: " + world.coins, 30, 10, Color.BLACK, 50, 255);
        drawNiveauIfChanged(deltaTime);
        drawMutedIcon();

        if (gameState == GameState.Paused)
        {
            //TODO WHAT TO DO WHEN PAUSED??
        }
        if (gameState == GameState.gameOver)
        {
            if (!game.isMuted()){ music.stop();}
            game.drawText(game.loadFont("outlinePixelFont.ttf"),"Game Over",70,50, Color.BLACK,100,255);
            game.drawText(game.loadFont("outlinePixelFont.ttf"),"Main Menu",120,150, Color.BLACK,70,255);
            game.drawText(game.loadFont("outlinePixelFont.ttf"),"Play Again",110,205, Color.BLACK,70,255);

        }
    }

    @Override
    public void pause()
    {
        if (gameState == GameState.Running)
        {
            if (!game.isMuted()) {music.stop();}
            gameState = GameState.Paused;
        }
    }

    @Override
    public void resume()
    {
        if (gameState == GameState.Paused)
        {
            gameState = GameState.Running;
        }
    }

    @Override
    public void dispose()
    {

    }

    private void tryMove()
    {
        float fixedX = world.errandBoy.x + (world.errandBoy.currentBitmap.width / 2), // Middle of ErrandBoy picture
                fixedY = world.errandBoy.y + (world.errandBoy.currentBitmap.height / 2); // Middle of ErrandBoy picture

        if (touchY <= fixedY )// Touch is over ErrandBoy
        {
            if (touchX <= fixedX) // Touch is Left for ErrandBoy
            {
                if (fixedX - touchX <= fixedY - touchY) // TouchY distance is greater than TouchX - aka move up
                {
                    world.errandBoy.move(MovingStates.Up);
                }
                else // TouchX is grater distance from ErrandBoy than TouchY - aka move left
                {
                    world.errandBoy.move(MovingStates.Left);
                }
            }
            else // Touch is Right for ErrandBoy
            {
                if (touchX - fixedX <= fixedY - touchY ) // TouchY distance is greater than TouchX - aka move up
                {
                    world.errandBoy.move(MovingStates.Up);
                }
                else // TouchX is grater distance from ErrandBoy than TouchY - aka move right
                {
                    world.errandBoy.move(MovingStates.Right);
                }
            }
        }
        else // Touch is under ErrandBoy
        {
            if (touchX <= fixedX) // Touch is Left for ErrandBoy
            {
                if (fixedX - touchX <= touchY - fixedY) // TouchY distance is greater than TouchX - aka move down
                {
                    world.errandBoy.move(MovingStates.Down);
                }
                else // TouchX is grater distance from ErrandBoy than TouchY - aka move left
                {
                    world.errandBoy.move(MovingStates.Left);
                }
            }
            else // Touch is Right for ErrandBoy
            {
                if (touchX - fixedX <= touchY - fixedY ) // TouchY distance is greater than TouchX - aka move down
                {
                    world.errandBoy.move(MovingStates.Down);
                }
                else // TouchX is grater distance from ErrandBoy than TouchY - aka move right
                {
                    world.errandBoy.move(MovingStates.Right);
                }
            }
        }
    }

    private void drawNiveauIfChanged(float deltaTime)
    {
        if (world.niveau == 1 && world.niveauChanged ||
                world.niveau == 2 && world.niveauChanged  ||
                world.niveau == 3 && world.niveauChanged )// TODO Change Coins and niveau
        {
            if (!drawNiveau){ drawNiveau = true;}
        }

        if (drawNiveau)
        {
            textTimeCount = textTimeCount -deltaTime;
            if (textTimeCount <= 0)
            {
                textTimeCount = textTimeReset;
                if (niveauFadeIn)
                {
                    transparency = transparency + 10;
                    if (transparency == 255){ niveauFadeIn = false; }
                }else
                {
                    transparency = transparency -10;
                    if (transparency == 5) { drawNiveau = false;niveauFadeIn = true; }
                }
            }
            game.drawText(game.loadFont("game-font.ttf"),"Niveau " + world.niveau,50,20, Color.BLACK,100,transparency);
        }
    }

    private boolean isTouchReleased()
    {
        List<TouchEvent> events = game.getTouchEvents();
        int stop = events.size();
        for (int i = 0; i < stop; i++)
        {
            if(events.get(i).type == TouchEvent.TouchEventType.Up)
            {
                return true;
            }
        }
        return false;
    }

    private void drawMutedIcon()
    {
        if (game.isMuted())
        {
            game.drawBitmap(soundIcon,720-80,1,0,43,58,43);
        }
        else
        {
            game.drawBitmap(soundIcon,720-80,1,0,0,58,43);

        }
    }
}
