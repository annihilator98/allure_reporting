package qa.learning;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий по имени {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("В результатах поиска переходим по ссылке репозитория {repository}")
    public void openRepositoryLink(String repository) {
        $(linkText(repository)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssueTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Проверяем что существует Issue c номером {issueNumber}")
    public void shouldSeeIssueWithNumber(Integer issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }

    @Step("Проверяем что существует Issue c названием {issueName}")
    public void shouldSeeIssueWithName(String issueName) {
        $("#issue_1_link").shouldHave(text(issueName));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}