package vue;

import java.util.Observable;
import java.util.Observer;

public class ConsoleView implements Observer {
    @Override
    public void update(Observable object, Object argument) {
        System.out.println((String)argument);
    }   
}
