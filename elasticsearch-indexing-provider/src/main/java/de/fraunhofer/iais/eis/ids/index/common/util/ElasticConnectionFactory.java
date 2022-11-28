package de.fraunhofer.iais.eis.ids.index.common.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ElasticConnectionFactory extends ConnectionFactory {
    final static private Logger logger = LoggerFactory.getLogger(ElasticConnectionFactory.class);
    // carying the connection object
    private RestHighLevelClient client;

    ElasticConnectionFactory(String hostname, String port, Integer retries) {
        super(hostname, port, retries);
    }

    ElasticConnectionFactory(String hostname, String port) {
        super(hostname, port);
    }

    @Override
    public RestHighLevelClient createConnection() throws IOException {
        // create a new client in case that the client wasn't initiated yet.
        // This replaces the creation process, that happened originally in the constructor
        if (this.client == null) {
            logger.info("instantiating connection to the elastic search server ");
            this.client = this.getFreshElasticsearchClient();
        }

        // in case that Elastic dosen't answer properly we refresh the connection
        if (!pingElasticServer()) {
            logger.info("re-instantiating connection to the elastic search server, after it interrupted");
            this.client = this.getFreshElasticsearchClient();
        }

        return this.client;
    }

    private RestHighLevelClient getFreshElasticsearchClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(getHostname(), Integer.parseInt(getPort()), "http")));

    }

    private boolean pingElasticServer() throws IOException {
        //@Todo: ping throughs ElasticsearchException, in case that elastic is not properly handling the connection. We should clarify how this relates to the return of the ping.
        return this.client.ping(RequestOptions.DEFAULT);
    }


}
