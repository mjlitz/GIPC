package consensus.asynchronous;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptResponseReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import port.trace.consensus.ProposalMade;
import port.trace.consensus.ProposalQuorumAchieved;
import port.trace.consensus.ProposalQuorumNotAchieved;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.AnAbstractConsensusMechanism;
import consensus.ReplicationSynchrony;
import consensus.ProposalState;
import consensus.ProposalFeedbackKind;

public class AnAsynchronousConsensusMechanism<StateType> extends
		ALearnerConsensusMechanism<StateType> {
	protected Learner<StateType> learners;
	protected float maxProposalNumberSentInLearnNotification = -1;
	
//	protected short numLearners;

	public AnAsynchronousConsensusMechanism(
			GIPCSessionRegistry aRegistry , String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
//		learners = aPeerProxy;
//		numLearners = aNumLearners;
		// eventualConsistency = anEventualConsistency;
	}

	protected Learner<StateType> learners() {
		return (Learner<StateType>) all();
	}
	
	protected short maxLearners() {
		return numMaximumMembers();
	}
	
	protected short numCurrentLearners() {
		return numCurrentMembers();
	}
	protected void recordAndSendLearnNotification(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordSentLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
		sendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
	}
	protected void recordSentLearnNotification(float aProposalNumber, StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberSentInLearnNotification = Math.max(maxProposalNumberSentInLearnNotification, aProposalNumber);
	}
	protected void sendLearnNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind anAgreement) {
		ProposalLearnNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal, anAgreement);
		learners().learn(aProposalNumber, aProposal, anAgreement);

	}
	
	@Override
	public float propose(StateType aProposal) {
//		if (lastProposalisPending() && !getAllowConcurrentProposals())
//			return -1;
		float aProposalNumber = getAndSetNextProposalNumber(aProposal);
		ProposalMade.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordProposalState(aProposalNumber, aProposal);
		dispatchPropose(aProposalNumber, aProposal);	
		return aProposalNumber;
	}
	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
		localPropose(aProposalNumber, aProposal);
	}
	

//	protected boolean sufficientLearners(short aMaxLearners, short aCurrentLearners) {
//		return aMaxLearners == aCurrentLearners;
//	}
//
//	protected Boolean sufficientLearners(short aMaxLearners, short aCurrentLearners, short aLearnedNotifications) {
//		if (aLearnedNotifications != aCurrentLearners)
//			return null;
//		if (aCurrentLearners >= aMaxLearners/2) {
//			return true;	
//		}
//		return false;
//	}
	@Override
	protected void localPropose(float aProposalNumber, StateType aProposal) {
		recordAndSendLearnNotification(aProposalNumber, aProposal, ProposalFeedbackKind.SUCCESS);
//		ProposalFeedbackKind aFeedbackKind = ProposalFeedbackKind.SUCCESS;
//		recordSentLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
//		sendLearnNotification(aProposalNumber, aProposal, aFeedbackKind);
	}
	

//	@Override
//	public void learned(float aProposalNumber, StateType aProposal) {
//		ProposalLearnedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
//		if (!isPending(aProposalNumber))
//			return;
//		short aTotalLearners = maxLearners();
//		short aLearnedNotifications = incrementCount(aProposalNumber, LEARNED_NOTIFICATION, 1);
//		Boolean aSufficientLearners = sufficientLearners(aTotalLearners, numCurrentLearners(), aLearnedNotifications);
//		if (aSufficientLearners == null) {
//			return;
//		}
//		if (aSufficientLearners) {
//			ProposalQuorumAchieved.newCase(this, getObjectName(), aProposalNumber, aProposal, aTotalLearners, aLearnedNotifications);
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
//		} else {
//			ProposalQuorumNotAchieved.newCase(this, getObjectName(), aProposalNumber, aProposal, aTotalLearners, aLearnedNotifications);
//
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_NOT_COMMUNICATED);
//
//		}
//		
//		
//	}

}
