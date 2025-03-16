package com.example.gateway.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;

/**
 * 自定义Predicate
 * xxxPredicateFactory xxx是predicate的名字
 */
@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {
    public static final String PARAM_KEY = "param";
    public static final String VALUE_KEY = "value";

    public VipRoutePredicateFactory() {
        super(Config.class);

    }

    @Override
    public Predicate apply(Config config) {
        return new GatewayPredicate() {
			@Override
			public boolean test(ServerWebExchange exchange) {
				ServerHttpRequest request = exchange.getRequest();
                String first = request.getQueryParams().getFirst(config.param);
                if (StringUtils.hasText(first) && first.equals(config.value)) {
                    return true;
                }
                return false;
			}

			@Override
			public Object getConfig() {
				return config;
			}

			@Override
			public String toString() {
				return String.format("Query: param=%s value=%s", config.getParam(), config.getValue());
			}
		};
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_KEY, VALUE_KEY);
    }

    @Validated
    public static class Config {

        @NotEmpty
        private String param;
        @NotEmpty
        private String value;

        public String getParam() {
            return param;
        }

        public Config setParam(String param) {
            this.param = param;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Config setValue(String regexp) {
            this.value = regexp;
            return this;
        }

    }

}
