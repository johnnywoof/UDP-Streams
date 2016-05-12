package me.johnnywoof.udpstreams;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class UDPOutputStream extends OutputStream {

	private final int buffer;
	private final DatagramSocket socket;
	private final InetSocketAddress address;
	private ByteBuffer data;
	private int index;

	public UDPOutputStream(DatagramSocket socket, int buffer, InetSocketAddress address) {
		this.socket = socket;
		this.buffer = buffer;
		this.address = address;
		this.data = ByteBuffer.allocate(this.buffer);
	}

	@Override
	public void write(int b) throws IOException {
		this.data.putInt(b);
	}

	@Override
	public void write(byte b[], int off, int len) throws IOException {
		this.data.put(b, off, len);
	}

	@Override
	public void flush() throws IOException {
		this.socket.send(new DatagramPacket(this.data.array(), this.buffer, this.address));
		this.index = 0;
		this.data = ByteBuffer.allocate(this.buffer);
	}

	@Override
	public void close() throws IOException {
		this.socket.close();
		this.data.clear();
	}

}
