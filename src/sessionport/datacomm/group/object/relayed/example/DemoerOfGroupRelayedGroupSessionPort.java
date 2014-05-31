package sessionport.datacomm.group.object.relayed.example;

import port.sessionserver.ASessionServerLauncher;
import port.sessionserver.relay.ASessionServerRelayerLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServerLauncher;
import port.sessionserver.relay.SessionServerRelayerLauncher;
import sessionport.rpc.duplex.example.AnAliceDuplexRPCSessionPort;
import bus.uigen.models.MainClassLaunchingUtility;

public class DemoerOfGroupRelayedGroupSessionPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
//				SessionPortSessionServerLauncher.class,
				ARelayerSupportingSessionServerLauncher.class,
				ASessionServerRelayerLauncher.class,
				AliceMemberRelayedGroupSessionPort.class,
				BobServerRelayedGroupSessionPort.class,
				CathyClientRelayedGroupSessionPort.class, // cathy can receive changes even though she is a client only, with session ports the behcior is different from session server

		};
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
