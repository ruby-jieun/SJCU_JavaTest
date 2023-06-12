package ex02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MultiChatServer {

	HashMap clients;

	public MultiChatServer() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}

	public void start() {
		ServerSocket ss = null;
		Socket s = null;

		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 시작 되었습니다. 쳇 시작 합시다!!");
			while (true) {
				s = ss.accept();
				System.out.println("[" + s.getInetAddress() + ":" + s.getPort() + "] 에서 접속하셨습니다.");
				ServerReceiver thread = new ServerReceiver(s);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ServerReceiver extends Thread {
		Socket s;
		DataInputStream dis;
		DataOutputStream dos;

		public ServerReceiver(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void sendToAll(String msg) {
			Iterator it = clients.keySet().iterator();

			while (it.hasNext()) {
				try {
					DataOutputStream dos = (DataOutputStream) clients.get(it.next());
					dos.writeUTF(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}// end sendToAll()

		@Override
		public void run() {
			String name = "";
			try {
				name = dis.readUTF();
				sendToAll("#" + name + " 님이 입장하셨습니다.");

				clients.put(name, dos);
				System.out.println("현재 서버 접속자 수는 : " + clients.size() + " 입니다.");

				while (dis != null) {
					sendToAll(dis.readUTF());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				sendToAll("#" + name + " 님이 나가셨습니다.");
				clients.remove(name);
				System.out.println("[" + s.getInetAddress() + ":" + s.getPort() + "] 에서 접속 종료 하셨습니다.");
				System.out.println("현재 서버 접속자 수는 : " + clients.size() + " 입니다.");
			}
		}
	}

	public static void main(String[] args) {
		new MultiChatServer().start();

	}
}
