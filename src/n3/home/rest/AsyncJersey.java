package n3.home.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/asyncJersey")
@Component
//@Scope("prototype")
public class AsyncJersey {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String asyncGet(@Suspended final AsyncResponse asyncResponse) {

		System.out.println(this);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				String result = veryExpensiveOperation();
				asyncResponse.resume(result);
			}

			private String veryExpensiveOperation() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "Got it!";
			}
		}).start();

		return "SUCCESS";
	}

}
