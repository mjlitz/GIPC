package inputport.datacomm.simplex.buffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;

import java.nio.ByteBuffer;

import port.trace.buffer.BufferSendInitiated;
import util.trace.Tracer;


public class AClientChannelSendBufferForwarder<ChannelType>  implements NamingSender<ByteBuffer>{
	SimplexBufferClientInputPortDriver<ChannelType> clientInputDriver;
	public AClientChannelSendBufferForwarder(InputPort anInputPort, SimplexBufferClientInputPortDriver<ChannelType> aClientInputDriver) {
		clientInputDriver = aClientInputDriver;
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
//		BufferChannelSendInitiated.newCase(this, clientInputDriver, message);
		Tracer.info(this, "Forwarding client channel message: " + message + " to:" + remoteName );
		clientInputDriver.send(remoteName, message);
	}
}
