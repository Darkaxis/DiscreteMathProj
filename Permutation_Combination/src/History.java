import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class History {
    public static class HistoryComponent {
        public char mode;
        public String n;
        public int r;

        HistoryComponent(char mode, String n, int r) {
            this.mode = mode;
            this.n = n;
            this.r = r;

        }
    }

    static ArrayList<HistoryComponent> Load() {
        ArrayList<HistoryComponent> historyComponents = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("history.dat"))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    char mode = parts[0].charAt(0);
                    String n = parts[1];
                    int r = Integer.parseInt(parts[2]);
                    HistoryComponent component = new HistoryComponent(mode, n, r);
                    historyComponents.add(component);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyComponents;

    }

    static void Save(char mode, String n, int r) {
        // Append a new HistoryComponent to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.dat", true))) {
            writer.write(mode + "," + n + "," + r + "\n");
            // No need to explicitly close the writer here as try-with-resources handles it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void clearHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("history.dat"))) {
            writer.write(""); // Clear the file by writing an empty string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}