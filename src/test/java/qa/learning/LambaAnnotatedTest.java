package qa.learning;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambaAnnotatedTest {

    private final String REPOSITORY = "annihilator98/files-tests";
    private final Integer ISSUE_NUMBER = 1;

    private final String ISSUE_NAME = "Hanna created a new issue! Send hi to Ерошенко";


    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий по имени " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText("annihilator98/files-tests")).click();
        });
        step("Открываем таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });gi
        step("Проверяем что существует Issue c номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        });

        step("Проверяем что Issue c название " + ISSUE_NAME, () -> {
            $("#issue_1_link").shouldHave(text(ISSUE_NAME));
        });
    }


    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
        steps.shouldSeeIssueWithName(ISSUE_NAME);
    }


}