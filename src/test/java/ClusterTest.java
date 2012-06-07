import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.spi.TestContainer;
import org.junit.Test;
import core.Cluster;
import resources.ClusterResource;
import modules.JsonJackson2Module;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static junit.framework.Assert.assertEquals;

public class ClusterTest extends JerseyTest {
    protected static final String JSON_MIME_TYPE = MediaType.APPLICATION_JSON;
    protected static final String XML_MIME_TYPE = MediaType.APPLICATION_XML;

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig().addModules(new JsonJackson2Module()).addClasses(ClusterResource.class);
    }

    @Override
    protected javax.ws.rs.client.Client getClient(TestContainer tc, ApplicationHandler application) {
        javax.ws.rs.client.Client origClient = super.getClient(tc, application);
        for (Class<?> provider : JsonJackson2Module.getProviders()) {
            origClient.configuration().register(provider);
        }
        return origClient;
    }

    @Test
    public void simpleTest() {
        Cluster cXml = target().path("cluster").path("35").request(XML_MIME_TYPE).get(Cluster.class);
        Cluster cJson = target().path("cluster").path("35").request(JSON_MIME_TYPE).get(Cluster.class);
        assertEquals(cXml, cJson);
    }
}