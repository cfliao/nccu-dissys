package nccu.cs.selab.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class UdpMulticastReceiver {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		MulticastSocket s = new MulticastSocket(1901);
		//InetSocketAddress group = new InetSocketAddress(InetAddress.getByName("239.255.255.250"), 1901);
		//s.joinGroup(group, NetworkInterface.getByName("lo"));
		s.joinGroup(InetAddress.getByName("239.255.255.250"));
//		Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
//		while (e.hasMoreElements()) {
//			System.out.println(e.nextElement());
//		}

		byte buf[] = new byte[1024];
		DatagramPacket pack = new DatagramPacket(buf, buf.length);
		s.receive(pack);
		System.out.println("Received data from: " + pack.getAddress().toString() + ":" + pack.getPort()
				+ " with length: " + pack.getLength());
		System.out.println(new Date().toString() + " " + new String(pack.getData()));
		System.out.println();
		s.close();
	}
}
