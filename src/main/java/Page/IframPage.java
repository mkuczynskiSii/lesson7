package Page;

import java.util.concurrent.ThreadLocalRandom;

public class IframPage {

    int randomNumber = ThreadLocalRandom.current().nextInt(1, 7);

    public String iframeSurnameId = "inputSurname3";
    public int formContinentsIndex = randomNumber;
    public String iframesLoginId = "inputLogin";
    public String iframesPasswordId = "inputPassword";
    public String iframesSelectContinentsId = "inlineFormCustomSelectPref";
    public String iframesSelectYOEId = "gridRadios" + randomNumber;
    public String iframe1Name = "iframe1";
    public String iframe2Name = "iframe2";
}
