package Service;

import net.RuleBase;
import net.RuleBaseService;
import net.RuleBaseServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public class RuleBaseClient {
    public String[] getBanks(int score) {
        String[] banks = null;
        RuleBaseService rbsl = new RuleBaseServiceLocator();
        try {

            RuleBase port = rbsl.getRuleBasePort();
            banks = port.getBanks(score);

        } catch (ServiceException | RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return banks;
    }

}
