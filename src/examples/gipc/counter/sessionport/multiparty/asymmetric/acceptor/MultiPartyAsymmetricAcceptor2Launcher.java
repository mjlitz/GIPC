package examples.gipc.counter.sessionport.multiparty.asymmetric.acceptor;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import consensus.Accepted;
import consensus.Learned;
import consensus.Learner;
import consensus.multiparty.asymmetric.listener.AnAsymmetricMultiPartyLearnerConsensusMechanism;
import consensus.multiparty.asymmetric.listener.MultiPartyAsymmetricListenerConsensusMechanism;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyAcceptor;
import consensus.twoparty.asymmetric.TwoPartyAsymmetricAcceptor;
import consensus.twoparty.symmetric.ASymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.SymmetricTwoPartyPeer;
import consensus.twoparty.symmetric.TwoPartySymmetricConsensusMechanism;
import bus.uigen.visitors.AddListenersAdapterVisitor;
import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;
import port.SessionChoice;
import port.trace.consensus.ConsensusTraceUtility;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.gipc.counter.layers.AMultiLayerCounterServer;
import examples.gipc.counter.sessionport.CounterSessionMember;
import examples.gipc.counter.sessionport.twoparty.asymmetric.AConsensusVetoer;
import examples.gipc.counter.sessionport.twoparty.asymmetric.ATwoPartyAsymmetricAcceptorLauncher;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class MultiPartyAsymmetricAcceptor2Launcher extends AMultiPartyAsymmetricAcceptorLauncher {
	
	
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";
	
	
	
	public static void beListener() {
		ConsensusTraceUtility.setTracing();
		init(MY_NAME, MY_PORT_NUMBER);
		greetingMechanism.addConsensusVetoer(new AConsensusVetoer<String>());
	}

	public static void main (String[] args) {
		
		beListener();
	}

}