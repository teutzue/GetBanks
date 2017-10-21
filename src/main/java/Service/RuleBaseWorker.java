package Service;

import org.json.JSONException;
import org.json.JSONObject;

import static Service.GetBanksReceiver.loan_request_complete;

public class RuleBaseWorker {
    RuleBaseClient client;
    //Object to call the rule base web service
    //Returns an JSON object with all banks
    public String[] processWithRuleBase() throws JSONException {
        client = new RuleBaseClient();

        String jsonStr = loan_request_complete;
        JSONObject loan_request_completed_json = new JSONObject(jsonStr);
        String credit_score = loan_request_completed_json.getString("creditScore");
        System.out.println("Credit score before Rule Base call: " + credit_score);

        return client.getBanks(Integer.parseInt(credit_score));
    }

}
