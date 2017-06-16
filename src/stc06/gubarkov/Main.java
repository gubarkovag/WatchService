package stc06.gubarkov;

import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get("C:\\Users\\admin\\IdeaProjects\\FileReader");

            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            try {
                while ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        System.out.println(
                                "Event kind: " + event.kind()
                                + ". File affected: " + event.context() + ".");
                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
