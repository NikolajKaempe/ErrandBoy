package dk.kea.class2016february.emilmadsen.ErrandBoy;

public class TouchEventPool extends Pool<TouchEvent>
{
    protected TouchEvent newItem()
    {
        return new TouchEvent();
    }
}
