package me.johnnywoof.udpstreams;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPInputStream extends InputStream {

	private final int buffer;
	private final DatagramSocket socket;
	private byte[] data = null;
	private int index;

	public UDPInputStream(DatagramSocket socket, int buffer) {
		this.socket = socket;
		this.buffer = buffer;
	}

	@Override
	public int read() throws IOException {
		if (this.data == null || this.index >= this.data.length) {
			this.index = 0;
			try {
				this.readSocket();
			} catch (IOException e) {
				return -1;
			}
		}

		return this.data[this.index];
	}

	//TODO Implement method
	/*
	@Override
	public int read(byte b[], int off, int len) throws IOException {

	}*/

	@Override
	public long skip(long n) throws IOException {
		long skipped = Math.min(n, this.available());
		this.index += skipped;
		return skipped;
	}

	@Override
	public int available() throws IOException {
		return (this.data.length - this.index);
	}

	@Override
	public void close() throws IOException {
		this.socket.close();
	}

	private void readSocket() throws IOException {
		this.data = new byte[this.buffer];
		this.socket.receive(new DatagramPacket(this.data, this.data.length));
	}

}
