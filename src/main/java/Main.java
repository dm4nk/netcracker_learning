import model.buildings.Floor;
import model.buildings.Space;
import model.buildings.dwelling.Flat;
import model.buildings.dwelling.hotel.HotelFloor;
import model.buildings.office.Office;
import model.buildings.threads.SequentialCleaner;
import model.buildings.threads.SequentialRepairer;
import model.utilities.Semaphore;

public class Main {
    public static void main(String[] args) {
        Floor floor = new HotelFloor(new Space[]{new Flat(), new Office()});
        Semaphore semaphore = new Semaphore();
        SequentialRepairer repairer = new SequentialRepairer(floor, semaphore);
        SequentialCleaner cleaner = new SequentialCleaner(floor, semaphore);

        Thread repair = new Thread(repairer);
        Thread clean = new Thread(cleaner);

        repair.start();
        clean.start();
    }
}
