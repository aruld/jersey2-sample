import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.spi.TestContainer;
import org.junit.Test;
import core.Cluster;
import resources.ClusterResource;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static junit.framework.Assert.assertEquals;

public class ClusterTest extends JerseyTest {

  @Override
  protected Application configure() {
    enable(TestProperties.LOG_TRAFFIC);
    enable(TestProperties.DUMP_ENTITY);
    return new ResourceConfig().registerClasses(ClusterResource.class, JacksonFeature.class);
  }

  @Override
  protected javax.ws.rs.client.Client getClient(TestContainer tc, ApplicationHandler application) {
    javax.ws.rs.client.Client origClient = super.getClient(tc, application);
    origClient.register(JacksonFeature.class);
    return origClient;
  }

  @Test
  public void simpleTest() {
    Cluster cXml = target().path("admin").path("35").request(MediaType.APPLICATION_XML).get(Cluster.class);
    Cluster cJson = target().path("admin").path("35").request(MediaType.APPLICATION_JSON).get(Cluster.class);
    assertEquals(cXml, cJson);
  }
}