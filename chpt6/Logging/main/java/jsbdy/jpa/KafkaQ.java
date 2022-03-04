package jsbdy.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!local")
public class KafkaQ implements MessageQueueInterface {

    private static final Logger logger= LoggerFactory.getLogger(KafkaQ.class);

    public KafkaQ(){
        logger.info("kafmq component");
    }

    @Override
    public String readMessage(){

        return "message from kafkamq";
    }
}