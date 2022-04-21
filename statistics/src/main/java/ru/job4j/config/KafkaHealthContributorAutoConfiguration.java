package ru.job4j.config;

import org.springframework.boot.actuate.autoconfigure.health.CompositeHealthContributorConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnBean(KafkaTemplate.class)
@ConditionalOnEnabledHealthIndicator("kafka")
@AutoConfigureAfter(KafkaAutoConfiguration.class)
public class KafkaHealthContributorAutoConfiguration extends CompositeHealthContributorConfiguration<KafkaHealthIndicator, KafkaTemplate> {

    @Bean
    @ConditionalOnMissingBean(name = { "kafkaHealthIndicator", "kafkaHealthContributor" })
    public HealthContributor rabbitHealthContributor(Map<String, KafkaTemplate> kafkaTemplates) {
        return createContributor(kafkaTemplates);
    }
}