package n3.home.circuitbreaker;

public class CircuitBreaker {

	private final String NAME;
	private volatile CircuitBreakerState state;
	
	public CircuitBreaker(String name) {
		NAME = name;
	}
	
	public void handleInCurrentState(Object obj) {
		try {
			state.handle(obj);
		} catch (Exception e) {
			
		}
	}

	public CircuitBreakerState getState() {
		return state;
	}

	public void setState(CircuitBreakerState state) {
		this.state = state;
	}

	public String getNAME() {
		return NAME;
	}
	
}
