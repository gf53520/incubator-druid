package io.druid.cube.http;

import io.druid.metadata.MetadataSegmentManager;
import com.google.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by tuo on 2018/7/26.
 */
@Path("/insight/v1")
public class CubeResource {

  private final MetadataSegmentManager metadataSegmentManager;

  @Inject
  public CubeResource(MetadataSegmentManager metadataSegmentManager) {
    this.metadataSegmentManager = metadataSegmentManager;
  }

  @GET
  @Path("/cubes")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCubes(
      @QueryParam("full") final String full,
      @QueryParam("includeDisabled") final String includeDisabled,
      @Context final HttpServletRequest req
  ) {
    return Response.ok().build();
  }
}
