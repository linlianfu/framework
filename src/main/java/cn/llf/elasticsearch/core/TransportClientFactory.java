package cn.llf.elasticsearch.core;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.util.Properties;

import static org.apache.commons.lang.StringUtils.split;
import static org.apache.commons.lang.StringUtils.substringAfterLast;
import static org.apache.commons.lang.StringUtils.substringBeforeLast;

/**
 * 创建者：   linlf
 * 创建时间： 2017/8/25
 * 描述：
 */
@Log4j
public class TransportClientFactory implements FactoryBean<TransportClient>,InitializingBean, DisposableBean {
    static final String COLON = ":";
    static final String COMMA = ",";
    private TransportClient client;
    private Properties properties;

    @Setter
    private String clusterNodes = "127.0.0.1:9300";
    @Setter
    private String clusterName = "elasticsearch";
    @Setter
    private Boolean clientTransportSniff = true;
    @Setter
    private Boolean clientIgnoreClusterName = Boolean.FALSE;
    @Setter
    private String clientPingTimeout = "5s";
    @Setter
    private String clientNodesSamplerInterval = "5s";


    @Override
    public void destroy() throws Exception {
        try {
            log.info("Closing elasticSearch  client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            log.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    protected void buildClient() throws Exception {
        client = new PreBuiltTransportClient(settings());
        Assert.hasText(clusterNodes, "[Assertion failed] clusterNodes settings missing.");
        for (String clusterNode : split(clusterNodes, COMMA)) {
            String hostName = substringBeforeLast(clusterNode, COLON);
            String port = substringAfterLast(clusterNode, COLON);
            Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
            Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
            log.info("adding transport node : " + clusterNode);
            client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port)));
        }
        client.connectedNodes();
    }

    private Settings settings() {
        if (properties != null) {
            return Settings.builder().put(properties).build();
        }
        return Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", clientTransportSniff)
                .put("client.transport.ignore_cluster_name", clientIgnoreClusterName)
                .put("client.transport.ping_timeout", clientPingTimeout)
                .put("client.transport.nodes_sampler_interval", clientNodesSamplerInterval)
                .build();
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
