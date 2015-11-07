package n3.home.circuitbreaker;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OpenState extends AbstractCircuitBreakerState {
	
	
	private final ScheduledThreadPoolExecutor executor;

	protected OpenState(CircuitBreaker circuitBreaker) {
		super(circuitBreaker);
		executor = null;
	}
	
	public OpenState(CircuitBreaker circuitBreaker, Long delayMinite) {
		super(circuitBreaker);
		executor = new ScheduledThreadPoolExecutor(1);
		executor.schedule(new OpenStateSchedule(), delayMinite, TimeUnit.MINUTES);
	}
	
	@Override
	public void handle(Object obj) {
		
	}

	@Override
	protected boolean isThresholdReached() {
		return false;
	}

	@Override
	public void destroy() {
		if(!executor.isTerminated()) {
			executor.shutdownNow();
		}
	}

	class OpenStateSchedule implements Runnable {
		@Override
		public void run() {
			try {
				new HalfOpenStateTransfer(circuitBreaker).transfer();
			} catch (Exception e) {
				e.printStackTrace();
				Thread.interrupted();
			}
		}
	}
	
}
