package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Helpers extends TestBase {

    final static String DOWNLOAD_DIR = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "downloadedFiles";

    public static int countFilesInDownloadDirectory() {
        int count = 0;
        try (Stream<Path> files = Files.list(Paths.get(DOWNLOAD_DIR))) {
            count = (int) files.count();

        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public static List<String> listFilesInDownloadDirectory() {
        return Stream.of(Objects.requireNonNull(new File(DOWNLOAD_DIR).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public static void moveElementBy(WebDriver driver, WebElement element, int x, int y) {
        Actions action = new Actions(driver);
        action.clickAndHold(element)
                .moveByOffset(x, y)
                .perform();
    }


}
