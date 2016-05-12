package me.johnnywoof.udpstreams.test;

import me.johnnywoof.udpstreams.UDPInputStream;
import me.johnnywoof.udpstreams.UDPOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TestCode {

	public static void main(String[] args) throws IOException {

		DatagramSocket socket = new DatagramSocket();

		DataInputStream in = new DataInputStream(new UDPInputStream(socket, 1024));
		DataOutputStream out = new DataOutputStream(new UDPOutputStream(socket, 1024, new InetSocketAddress("localhost", 1023)));

		out.writeUTF("Hi there!");
		System.out.println("From peer: " + in.readUTF());

		in.close();
		out.close();

	}

}
