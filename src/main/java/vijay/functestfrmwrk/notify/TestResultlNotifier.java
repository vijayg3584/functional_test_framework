/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.notify;

import java.util.Collection;
import vijay.functestfrmwrk.config.AFTConfigPropertyInfo;
import static vijay.functestfrmwrk.config.AFTConfigPropertyInfo.RESULT_NOTIFY_RECIPIENT_EMAIL;
import static vijay.functestfrmwrk.config.AFTConfigPropertyInfo.RESULT_NOTIFY_SENDER_EMAIL;
import vijay.functestfrmwrk.config.AFTConfiguration;
import vijay.functestfrmwrk.config.ConfigException;
import vijay.functestfrmwrk.testdata.TestCaseInfo;
import vijay.functestfrmwrk.testdata.TestSuiteInfo;
import vijay.functestfrmwrk.utility.SimpleMailSender;

/**
 *
 *
 */
public class TestResultlNotifier {

    public void notifyResult(TestSuiteInfo suiteInfo) throws NotificationException {
        if (suiteInfo.isSendMail()) {
            String subject = prepareSubject(suiteInfo.getModule(), suiteInfo.getScenario(), suiteInfo.getResult(), suiteInfo.getBuildVersion());
            String body = prepareBody(suiteInfo.getTestCases());
            sendEmail(subject, body);
        }
    }

    private String prepareBody(Collection<TestCaseInfo> testCases) {
        StringBuilder body = new StringBuilder();
        body.append("<html><body><head>"
                + "<style>"
                + "table,th,td"
                + "{"
                + "border:1px solid black;"
                + "border-collapse:collapse;"
                + "}"
                + "</style>"
                + "</head>");
        body.append("<br> Hi, <br><br>");
        body.append("<table><tr><th>Scenario</th><th>Result</th></tr>");

        for (TestCaseInfo testCase : testCases) {
            body.append("<tr>");
            body.append("<td>");
            body.append(testCase.getScenario());
            body.append("</td>");
            body.append("<td>");
            body.append(testCase.getResult());
            body.append("</td>");
            body.append("</tr>");
        }
        body.append("</table>");
        body.append("</body></html>");
        body.append(REGARDS_TAIL);
        return body.toString();
    }

    private String prepareSubject(String application, String scenario, String result, String version) {
        StringBuilder subject = new StringBuilder("Auto Func Test:: ");
        subject.append(application).append("|").append(scenario).append("|").append(result.toUpperCase()).append("|").append("version:").append(version);
        return subject.toString();
    }

    private void sendEmail(String subject, String body) {
        mailSender.send(senderEmailId, recipientEmailId, smtpMailHost, smtpMailPort, smtpMailUser, smtpMailPassword, subject, body);
    }

    public TestResultlNotifier() throws NotificationException {
        try {
            aftConfig = AFTConfiguration.getInstance();
        } catch (ConfigException ex) {
            throw new NotificationException("Unable to send notification", ex);
        }
        senderEmailId = aftConfig.getConfigValue(RESULT_NOTIFY_SENDER_EMAIL);
        recipientEmailId = aftConfig.getConfigValue(RESULT_NOTIFY_RECIPIENT_EMAIL);
        smtpMailHost = aftConfig.getConfigValue(AFTConfigPropertyInfo.SMTP_MAIL_HOST);
        smtpMailPort = aftConfig.getConfigValue(AFTConfigPropertyInfo.SMTP_MAIL_PORT);
        smtpMailUser = aftConfig.getConfigValue(AFTConfigPropertyInfo.SMTP_MAIL_USER);
        smtpMailPassword = aftConfig.getConfigValue(AFTConfigPropertyInfo.SMTP_MAIL_PASSWORD);
        mailSender = SimpleMailSender.getInstance();
    }
    private final SimpleMailSender mailSender;
    private final String recipientEmailId;
    private final String senderEmailId;
    private final String smtpMailHost;
    private final String smtpMailPort;
    private final String smtpMailUser;
    private final String smtpMailPassword;
    private final AFTConfiguration aftConfig;
    private final String REGARDS_TAIL = "<br><br>Regards, <br>Auto Functional Test Framework";
}
