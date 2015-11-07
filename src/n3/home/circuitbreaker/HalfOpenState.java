package n3.home.circuitbreaker;

public class HalfOpenState extends AbstractCircuitBreakerState {

	public HalfOpenState(CircuitBreaker circuitBreaker) {
		super(circuitBreaker);
	}

	@Override
	public void handle(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isThresholdReached() {
		// TODO Auto-generated method stub
		return false;
	}

}
