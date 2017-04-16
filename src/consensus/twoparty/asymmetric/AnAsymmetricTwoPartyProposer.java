package consensus.twoparty.asymmetric;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.AnAbstractConsensusMechanism;
import consensus.ProposalState;
import consensus.ProposalRejectionKind;
import consensus.asynchronous.ALearnerConsensusMechanism;
import consensus.synchronous.Acceptor;

public class AnAsymmetricTwoPartyProposer<StateType> extends
		ALearnerConsensusMechanism<StateType> implements TwoPartyAssymetricProposer<StateType> {
	protected Acceptor<StateType> acceptor;

	public AnAsymmetricTwoPartyProposer(GIPCSessionRegistry anInputPort, String aName, short aMyId,
			Acceptor<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		acceptor = aPeerProxy;
	}
	protected Acceptor<StateType> acceptor() {
		return acceptor;
	}
//	@Override
//	protected boolean allowConcurrentProposals() {
//		return false;
//	}
	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptor.accept(aProposalNumber, aProposal);		
	}
	@Override
	protected void localPropose(float aProposalNumber, StateType aProposal) {
		sendAcceptRequest(aProposalNumber, aProposal);		
	}
	@Override
	public void accepted(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		ProposalAcceptedNotificationReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal, aRejectionKind);
		learn(aProposalNumber, aProposal, aRejectionKind);
//		if (anAgreement)
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);	
//		else
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_REJECTED);	

	}
	@Override
	public void accept(float aProposalNumber, StateType aProposal) {
		// TODO Auto-generated method stub
		
	}

}
