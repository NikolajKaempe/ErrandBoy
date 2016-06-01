package dk.kea.class2016february.emilmadsen.ErrandBoy;

public interface TouchHandler
{
    boolean isTouchDown(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
}
