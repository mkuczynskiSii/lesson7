package Page;

import java.util.concurrent.ThreadLocalRandom;

public class FormPage {
    int randomNumber = ThreadLocalRandom.current().nextInt(1, 7);

    public String formFirstNameId = "inputFirstName3";
    public String formLastNameId = "inputLastName3";
    public String formEmailId = "inputEmail3";
    public String formSexId = "gridRadiosMale";
    public String formAgeId = "inputAge3";
    public String formRandomYOEId = "gridRadios" + randomNumber;
    public String formProfessionId = "gridCheckAutomationTester";
    public String formContinentsId = "selectContinents";
    public int formRandomContinentsIndex = randomNumber;
    public String formSeleniumCommandsId = "selectSeleniumCommands";
    public String formFileId = "chooseFile";
    public String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\uploadFile.txt";
    public String signInButtonXpath = "//button[text()='Sign in']";
    public String testFileToDownloadButtonCss = "[role=\"button\"]";
    public String validatorMessageId = "validator-message";
}
