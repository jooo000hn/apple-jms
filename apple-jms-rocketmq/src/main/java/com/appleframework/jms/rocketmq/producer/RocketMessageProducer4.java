package com.appleframework.jms.rocketmq.producer;

import java.io.Serializable;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appleframework.jms.core.exception.MQException;
import com.appleframework.jms.core.producer.MessageProducer4;
import com.appleframework.jms.core.utils.ByteUtils;
import com.appleframework.jms.rocketmq.RocketMQProducer;

/**
 * @author Cruise.Xu
 * 
 */
public class RocketMessageProducer4 implements MessageProducer4 {
	
	private final static Logger logger = LoggerFactory.getLogger(RocketMessageProducer4.class);

	private RocketMQProducer producer;

	public void setProducer(RocketMQProducer producer) {
		this.producer = producer;
	}

	public void sendByte(String topic, String tags, String keys, byte[] message) throws MQException {
        Message msg = new Message(topic, tags, keys, message);
        try {
        	SendResult result = producer.send(msg);
        	logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}

	public void sendObject(String topic, String tags, String keys, Serializable message) throws MQException {		
		Message msg = new Message(topic, tags, keys, ByteUtils.toBytes(message));
		try {
			SendResult result = producer.send(msg);
			logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}

	public void sendText(String topic, String tags, String keys, String message) throws MQException {		
		Message msg = new Message(topic, tags, keys, ByteUtils.toBytes(message));
		try {
			SendResult result = producer.send(msg);
			logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}
	
	public void destory() {
		try {
			producer.shutdown();
		} catch (Exception e) {
		}
	}

}
