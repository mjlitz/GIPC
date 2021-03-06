package port.trace.nio;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import inputport.rpc.RemoteCall;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.TraceableInfo;

public class SocketChannelAccepted extends SocketChannelInfo {	
	public SocketChannelAccepted(String aMessage, Object aFinder,
			SocketChannel aSocketChannel, ServerSocketChannel aSocketServerChannel) {
		super(aMessage, aFinder, aSocketChannel);
	}
	public static SocketChannelAccepted newCase(Object aSource, 			
			SocketChannel aSocketChannel, ServerSocketChannel aServerSocketChannel) {    	
		String aMessage = 
				aSocketChannel.toString();
		SocketChannelAccepted retVal = new SocketChannelAccepted(aMessage, 
				aSource, 
				aSocketChannel,
				aServerSocketChannel);
    	retVal.announce();
    	return retVal;
	}
}
