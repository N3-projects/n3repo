package n3.home.circuitbreaker;

public interface CircuitBreakerState {

	public void handle(Object obj);
	
	public void destroy();
}
