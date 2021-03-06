package inputport.datacomm.simplex.buffer.nio;

public class ASelectionManagerManager  {
	public static String SELECTING_THREAD_NAME = "Selecting Thread";
	protected static SelectionManager selectionManager;
	public static SelectionManager getSelectionManager() {
		if (selectionManager == null) {
			selectionManager = new AScatterGatherSelectionManager();
			Thread selectingThread = new Thread (selectionManager);
			selectingThread.setName(SELECTING_THREAD_NAME);
			selectingThread.start();
		}
		return selectionManager;
	}	
}
