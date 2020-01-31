import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class CreateTopic {
    private static final Logger logger = Logger.getLogger(CreateTopic.class);

    public static void main(String[] args) throws IOException {
//        BasicConfigurator.configure();
        DOMConfigurator.configure("D:\\Source\\ChangeInfo\\kafkapoc\\src\\main\\resources\\log4j2.xml");
        Properties properties = KafkaProperty.cerateProperty();
        String topicName = "test1";

//        AdminClient adminClient = AdminClient.create(properties);
//
//        createTopic(adminClient,topicName);
//        listAllTopics(adminClient);

        producer(properties, topicName);

//        consumer(properties, topicName);

    }

    private static void consumer(Properties properties, String topicName) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer
                <String, String>(properties);

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        //print the topic name
        System.out.println("Subscribed to topic " + topicName);
        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)

                // print the offset,key and value for the consumer records.
                System.out.printf("offset = %d, key = %s, value = %s\n",
                        record.offset(), record.key(), record.value());
        }
    }

    private static void producer(Properties properties, String topicName) {
        Producer<String, String> producer = new KafkaProducer
                <String, String>(properties);
        for(int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>(topicName,
                    Integer.toString(i), Integer.toString(i)));
        }
        System.out.println("Message sent successfully");
        producer.close();
    }

    private static void createTopic(AdminClient adminClient,String topicName) {

        CreateTopicsResult result = adminClient.createTopics(Arrays.asList(new NewTopic(topicName, 1, (short) 1)));
        System.out.println("Topic Created: ");
    }

    private static void listAllTopics(AdminClient adminClient) {
        ListTopicsResult topics = adminClient.listTopics();
        Set<String> topicNames = null;
        try {
            logger.info("Starting listing names");
            topicNames = topics.names().get();
            logger.info("Listed names");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for(String topic: topicNames) {
            System.out.println(topic);
        }
    }
}
