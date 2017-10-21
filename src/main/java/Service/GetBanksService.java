package Service;

import com.rabbitmq.client.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.json.Json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import static Service.GetBanksReceiver.loan_request_complete;

public class GetBanksService {
    private final static String QUEUE_NAME = "banks_queue";

    //1. Receive the message (JSON - loan request) from Get Credit Score
    //2. Consult the rule base and get the appropriate banks
    //3. Send the message to the recipient list
    public void processMessageAndSendToRecipList(String [] banks, String loan) throws IOException, TimeoutException, JSONException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //code to construct the JSON message

        System.out.println("Banks received in the GetBanks Service: " + banks.length);

        JSONObject loan_json = new JSONObject(loan);
        String ssn = loan_json.getString("ssn");
        Double loanAmount = loan_json.getDouble("loanAmount");
        String loanDuration = loan_json.getString("loanDuration");
        int creditScore = loan_json.getInt("creditScore");
        System.out.println("SSN extracted: " + ssn + "; Loan amount: " + loanAmount+ "; Loan duration "
                + loanDuration + "; Credit Score: " + creditScore);


        JSONObject loanRequest = new JSONObject();

        loanRequest.put("ssn",ssn);
        loanRequest.put("loanAmount",loanAmount);
        loanRequest.put("loanDuration",loanDuration);
        loanRequest.put("creditScore",creditScore);


        loanRequest.put("banks", new JSONArray(banks));


        System.out.println("Loan Request in JSON format after enrichment with the banks array: " + loanRequest.toString());

        channel.basicPublish("", QUEUE_NAME, null, loanRequest.toString().getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + loanRequest.toString() + "' from Get Banks(GetBanksService) to RecipientList");


        channel.close();
        connection.close();

    }

}
