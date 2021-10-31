package model.buildings.threads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import model.buildings.Floor;
import model.buildings.Space;

@AllArgsConstructor
public class Repairer extends Thread {
    @NonNull
    @Getter
    @Setter
    private Floor floor;

    @Override
    public void run() {
        int i = 0;
        for (Space space : floor) {
            System.out.println("Repairing space number " + i + " with total area " + space.getSpace() + " square meters");
        }
        System.out.println("Done repairing");
    }

    @Override
    public void interrupt() {
        super.interrupt();
        System.out.println("Done repairing");
    }


}
