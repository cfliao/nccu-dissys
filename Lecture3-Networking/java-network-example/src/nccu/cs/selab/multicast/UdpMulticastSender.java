package nccu.cs.selab.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class UdpMulticastSender {

	final static String INET_ADDR = "239.255.255.250";
	final static int PORT = 1901;

	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		// Get the address that we are going to connect to.
		InetAddress address = InetAddress.getByName(INET_ADDR);

		// Create a buffer of bytes, which will be used to store
		// the incoming bytes containing the information from the server.
		// Since the message is small here, 256 bytes should be enough.
		//byte[] buf = new byte[256];

		try (MulticastSocket clientSocket = new MulticastSocket(1901)) {

			//InetSocketAddress group = new InetSocketAddress(InetAddress.getByName("239.255.255.250"), 1901);
			//clientSocket.joinGroup(group, NetworkInterface.getByInetAddress(InetAddress.getByName("127.0.0.1")));

			clientSocket.joinGroup(address);
			String msg = "GET /discover \n";

			DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, address, PORT);
			clientSocket.send(msgPacket);

			System.out.println("Sent packet with msg: " + msg);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

//		try (DatagramSocket receiver = new DatagramSocket()) {
//			byte[] receiveData = new byte[256];
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			receiver.receive(receivePacket);
//			System.out.println("From server at: " + receivePacket.getAddress() + ":" + receivePacket.getPort());
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
	}
}
