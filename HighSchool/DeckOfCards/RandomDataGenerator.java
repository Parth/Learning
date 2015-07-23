import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Author: parthmehrotra
 */
public class RandomDataGenerator {
    public static void main(String args[]) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(new File("Random.dat"));
        for (int i = 52; i > 0; i--) {
            printWriter.println((int) (Math.random() * i) + 1);
        }
        printWriter.close();
    }
}
