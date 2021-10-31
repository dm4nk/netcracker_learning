package model.buildings.threads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.buildings.Floor;
import model.buildings.Space;

@AllArgsConstructor
public class Cleaner extends Thread {
    @NonNull
    @Getter
    @Setter
    private Floor floor;

    @Override
    public void run() {
        int i = 0;
        for (Space space : floor) {
            System.out.println("Cleaning room number " + i + " with total area " + space.getSpace() + " square meters");
        }
        System.out.println("Done cleaning");
    }

    @Override
    public void interrupt() {
        super.interrupt();
        System.out.println("Done cleaning");
    }
}
