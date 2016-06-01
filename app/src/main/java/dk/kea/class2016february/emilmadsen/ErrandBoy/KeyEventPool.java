package dk.kea.class2016february.emilmadsen.ErrandBoy;

public class KeyEventPool extends Pool<MyKeyEvent>
{
    @Override
    protected MyKeyEvent newItem()
    {
        return new MyKeyEvent();
    }
}
