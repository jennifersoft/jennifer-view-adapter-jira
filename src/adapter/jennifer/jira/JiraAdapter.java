package adapter.jennifer.jira;

import adapter.jennifer.jira.entity.Issue;
import adapter.jennifer.jira.entity.JiraProp;
import adapter.jennifer.jira.util.ConfUtil;
import adapter.jennifer.jira.util.JIRAClient;
import com.jennifersoft.view.adapter.JenniferAdapter;
import com.jennifersoft.view.adapter.JenniferModel;
import com.jennifersoft.view.adapter.model.JenniferEvent;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by khalid on 10/12/16.
 */
public class JiraAdapter implements JenniferAdapter{

    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public void on(JenniferModel[] jenniferModels) {
        JiraProp jiraProp = ConfUtil.getJiraProperites();

        for(int i = 0 ; i < jenniferModels.length; i++){
            JenniferEvent event = (JenniferEvent)jenniferModels[i];
            String issueTitle = event.getErrorType() + " was caught by JENNIFER";
            String issueDescription = buildIssueDescription(event);

            Issue createdIssue = JIRAClient.createIssue(jiraProp, issueTitle, issueDescription);
            if(createdIssue != null){
                System.out.format("%s [JIRA Plugin] Issue created, Issue ID: %s, Issue Key: %s\n",SDF.format(new Date()),createdIssue.getId(),createdIssue.getKey());
            }else{
                System.out.format("%s [JIRA Plugin] Failed to create JIRA issue\n",SDF.format(new Date()));
            }

        }
    }

    private String buildIssueDescription(JenniferEvent event) {
        StringBuilder issueDescription = new StringBuilder();
        issueDescription.append("The following event "+event.getErrorType()+" was caught by JENNIFER\n");
        issueDescription.append("Here are some additional details\n");
        issueDescription.append("Affected Domain [ID:NAME]: " + event.getDomainId() + ":" + event.getDomainName()+"\n");
        issueDescription.append("Affected Instance [ID:NAME]: " + event.getInstanceId() + ":" + event.getInstanceName()+"\n");
        String message = event.getMessage().length() == 0 ? "None" :event.getMessage();
        issueDescription.append("Message : " + message + "\n");
        String detailedMessage = event.getDetailMessage().length() == 0 ? "None" :event.getDetailMessage();
        issueDescription.append("Detailed Message : " + detailedMessage + "\n");
        String serviceName = event.getServiceName().length() == 0 ? "Not available" : event.getServiceName();
        issueDescription.append("Service Name : " + serviceName + "\n");
        String transactionId = event.getTxId() == 0 ? "Not available" : event.getTxId() + "";
        issueDescription.append("Transaction Id: " + transactionId + "\n");
        String metricsName = event.getMetricsName().length() == 0 ? "Not available" : event.getMetricsName();
        issueDescription.append("Metrics Name : " + metricsName + "\n");
        Date d = new Date(event.getTime());
        issueDescription.append("Event Time [Raw:Human Readable]: " + event.getTime()  + ":" + SDF.format(d)+"\n");
        issueDescription.append("\nThis issue was created automatically by JENNIFER JIRA Adapter");

        return issueDescription.toString();
    }
}
