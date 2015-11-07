package n3.home.circuitbreaker;

public abstract class AbstractCircuitBreakerState implements
		CircuitBreakerState {

	protected CircuitBreaker circuitBreaker;

	protected AbstractCircuitBreakerState(CircuitBreaker circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
	}
	
	protected long hotspotCount = 0;
	protected long totalCount = 0;
	
	abstract protected boolean isThresholdReached();
}
