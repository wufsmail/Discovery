package com.nepxion.discovery.console.rest;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.nepxion.discovery.common.entity.SentinelRuleType;
import com.nepxion.discovery.console.resource.ServiceResource;

public class SentinelClearRestInvoker extends AbstractRestInvoker {
    private SentinelRuleType ruleType;

    public SentinelClearRestInvoker(ServiceResource serviceResource, String serviceId, RestTemplate restTemplate, SentinelRuleType ruleType) {
        super(serviceResource, serviceId, restTemplate);

        this.ruleType = ruleType;
    }

    @Override
    protected String getDescription() {
        return "Sentinel rules cleared";
    }

    @Override
    protected String getSuffixPath() {
        String path = StringUtils.equals(ruleType.toString(), "param-flow") ? "sentinel-param" : "sentinel-core";

        return path + "/clear-" + ruleType + "-rules";
    }

    @Override
    protected String doRest(String url) {
        return restTemplate.postForEntity(url, null, String.class).getBody();
    }
}