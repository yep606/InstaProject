import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BotTest {

    @Test
    public void botShouldFindLikeButton(){

        String url = "";
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.instagram.com/explore/tags/newyork/");

        for (WebElement elem : driver.findElements(By.tagName("a"))) {
            String href = elem.getAttribute("href");
            if(href.contains(".com/p/")){
                url = href;
                break;
            }

        }

        driver.get(url);
        String s = driver.findElement(By.xpath("//*[@aria-label='Like']")).getTagName();

        assertEquals("svg", s);

    }

}
