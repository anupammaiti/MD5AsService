package com.siebel.md5;
public class MainService {

	/**
	 * Flag to know if this service
	 * instance has been stopped.
	 */
	private boolean stopped = false;
	private boolean alreadyStarted=false;

	/**
	 * Single static instance of the service class
	 */
	private static MainService serviceInstance = new MainService();

	/**
	 * by default main method is called by prunsrv to start/stop
	 * the service. Pass the argument "start" to start the service,
	 * and pass "stop" to stop the service.
	 */
	public static void main(String args[]) {
		String cmd = "start";
		if(args.length > 0) {
			cmd = args[0];
		}

		if("start".equals(cmd)) {
			serviceInstance.start();
		}
		else {
			serviceInstance.stop();
		}
	}


	/**
	 * Start this service instance
	 */
	public void start() {
		stopped = false;
		System.out.println("MainService Started " + new java.util.Date());
		while(!stopped) {
			synchronized(this) {
				if(alreadyStarted==false){
					SoapService soapService=new SoapService();
					soapService.exposeSoap();
					alreadyStarted=true;
				}
			}
		}
		System.out.println("MainService Stopped " + new java.util.Date());
	}

	/**
	 * Stop this service instance
	 */
	public void stop() {
		stopped = true;
		synchronized(this) {
			this.notify();
		}
	}

}