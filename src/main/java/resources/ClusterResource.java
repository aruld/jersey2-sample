package resources;

import core.Cluster;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("cluster")
public class ClusterResource {

    @GET
    @Path("{id}")
    public Cluster getCluster(@PathParam("id") String id) {
        Cluster c = new Cluster();
        c.setId(Integer.valueOf(id));
        c.setName("GF Cluster");
        return c;
    }
}