package port.sessionserver.asymmetricexample;

import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfAsymmetricP2PWithSessionServer {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {

		
		Class[] classes = {
				port.sessionserver.ASessionServerLauncher.class,
				AliceMemberSessionClient.class,
				BobServerSessionClient.class,
				CathyClientSessionClient.class

				
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}
	
	

}