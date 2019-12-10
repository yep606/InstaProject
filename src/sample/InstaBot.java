package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class InstaBot {

    private String username;
    private String password;
    private WebDriver driver;
    private JavascriptExecutor jExecutor;
    private List<String> pics;
    private List<String> hashtags;
    private final String BASE_URL = "https://www.instagram.com";


    public InstaBot(String username, String password) {

        this.username = username;
        this.password = password;

        loadDriver();
    }

    public void loadDriver() {

        System.setProperty("webdriver.chrome.driver", "/Users/tkhrn/IdeaProjects/seleniumTesting/chromedriver");
        driver = new ChromeDriver();
        jExecutor = (JavascriptExecutor) driver;

    }

    public void toLogin() {

        try {
            driver.get(String.format("%s/accounts/login/?hl=ru&source=auth_switcher", BASE_URL));
            Thread.sleep(1000);
            driver.findElement(By.name("username")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[contains(text(), 'Войти')]")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }

    }

    public void collectPhoto(List<String> hashtags) {

        String hashtag = hashtags.get((int) (Math.random() * hashtags.size() - 1));
        hashtags.remove(hashtag);

        pics = new ArrayList<>();
        driver.get(String.format("%s/explore/tagsController/%s/", BASE_URL, hashtag));

        for (int i = 0; i < 6; i++) {

            try {

                Thread.sleep(500);

                jExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                Thread.sleep(1000);

                List<WebElement> hrefs = driver.findElements(By.tagName("a"));

                Thread.sleep(100);

                String href;
                for (WebElement elem : hrefs) {

                    href = elem.getAttribute("href");

                    if (href.contains(".com/p/") && !pics.contains(href))
                        pics.add(elem.getAttribute("href"));
                }

            } catch (InterruptedException e) {

            }

        }

    }

    public void likePhoto() {

        collectPhoto(hashtags);
        int count = 0;
        int picsLength = pics.size();


        System.out.println("Будет пролайкано фото: " + picsLength);
        for (String pic : pics
        ) {

            driver.get(pic);

            try {

                Thread.sleep(1000);
                jExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);
                driver.findElement(By.xpath("//button/span[@aria-label='Нравится']")).click();

            } catch (Exception e) {
            }
            count++;
            System.out.println(String.format("Осталось %d", picsLength - count));
        }

    }

    public void writeComment(){



    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public WebDriver getDriver() {
        return driver;
    }


}