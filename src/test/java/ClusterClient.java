import core.Cluster;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientFactory;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ClusterClient {
    public static void main(String[] args) {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonFeature.class);
        Client client = ClientFactory.newClient(clientConfig);
        WebTarget target = client.target("http://localhost:8080/cluster");
        target.register(LoggingFilter.class);
        Cluster cJson = target.path("admin").path("35").request(MediaType.APPLICATION_JSON).get(Cluster.class);
        System.out.println(cJson.getName());
        Cluster cXml = target.path("admin").path("35").request(MediaType.APPLICATION_XML).get(Cluster.class);
        System.out.println(cXml.getName());
    }
}
