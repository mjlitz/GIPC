package sessionport.datacomm.duplex.object.example;

import port.sessionserver.relay.RelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfDuplexObjectRelayingSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				RelayerSupportingSessionServerLauncher.class,
				SessionServerRelayerLauncher.class, // seems that a relayer is not automatically registered with the session server
				AliceObjectDuplexSessionPort.class,
				BobObjectDuplexSessionPort.class,
				CathyObjectDuplexSessionPort.class
		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}