package minsang.chat.config;

import minsang.chat.domain.ChatMessageDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ChatMessageDTO> messageListner() {
		ConcurrentKafkaListenerContainerFactory<String, ChatMessageDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, ChatMessageDTO> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaConsumerConfigurations(), new StringDeserializer(), new JsonDeserializer<>(ChatMessageDTO.class));
	}

	@Bean
	public Map<String, Object> kafkaConsumerConfigurations() {

		Map<String, Object> configurations = new HashMap<>();
		configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configurations.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return configurations;
	}
}
