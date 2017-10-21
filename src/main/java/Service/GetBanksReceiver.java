package Service;

import com.rabbitmq.client.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class GetBanksReceiver {

        private final static String QUEUE_NAME = "credit_score_queue";
        public static String loan_request_complete;
        public static String[] banks;

        public static void main(String[] argv) throws Exception {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for complete loan requests form Get Credit Score. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    loan_request_complete = new String(body, "UTF-8");
                    System.out.println(" [x] Received loan request complete from Get Credit Score: '" + loan_request_complete + "'");

                    RuleBaseWorker rl = new RuleBaseWorker();
                    GetBanksService serv = new GetBanksService();
                    try {
                        banks = rl.processWithRuleBase();
                        // once you get the banks, process and send the message further
                        serv.processMessageAndSendToRecipList(banks, loan_request_complete);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume(QUEUE_NAME, true, consumer);
        }
}
