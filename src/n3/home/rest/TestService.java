package n3.home.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Path("/test")
@Component
public class TestService {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "success";
    }
}
