package model.buildings.threads;

import lombok.*;
import model.buildings.Floor;
import model.buildings.Space;
import model.utilities.Semaphore;

@AllArgsConstructor
public class SequentialCleaner implements Runnable {

    @NonNull
    @Getter
    @Setter
    private Floor floor;
    @NonNull
    @Getter
    @Setter
    private Semaphore semaphore;

    @SneakyThrows
    @Override
    public void run() {
        int i = 0;
        for (Space space : floor) {
            semaphore.startClean();
            System.out.println("Cleaning room number " + i + " with total area " + space.getSpace() + " square meters");
            semaphore.stopClean();
        }
        System.out.println("Done cleaning");
    }
}
