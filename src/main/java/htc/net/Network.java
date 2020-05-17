package htc.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import htc.net.packets.PeerReq;
import htc.net.packets.PeerResp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A class to manage all the connections to peers in the network and unify
 * network traffic into a single interface.
 */
public class Network implements ConnectionHandler {
  private final PeerManager manager = new PeerManager();

  public Network() {
  }

  public void handle(Socket connection) {
    var addr = (InetAddress) connection.getInetAddress();
    int id = this.manager.getID(addr);
    try {
      var in = new ObjectInputStream(connection.getInputStream());
      var packets = Packet.getAll(in);
      if (packets.isEmpty()) {
        return;
      }
      in.close();
      for (Packet p : packets) {
        switch (p.getType()) {
          case PEER_REQ:
            this.handlePeerReq((PeerReq) p, id, connection);
            break;
          default:
            throw new UnsupportedOperationException();
        }
      }
      connection.close();

    } catch (IOException e) {
      System.err.println("Could not get InputStream from socket.");
      e.printStackTrace();
    }
  }

  private void handlePeerReq(PeerReq packet, int sender, Socket conn) throws IOException {
    var peers = this.manager.getPeers(sender);
    var arr = new InetAddress[peers.size()];
    for (int i = 0; i < peers.size(); i++) {
      arr[i] = peers.get(i).getAddr();
    }
    var resp = new PeerResp(arr);
    var out = new ObjectOutputStream(conn.getOutputStream());
    out.writeObject(PacketType.PEER_RESP);
    resp.write(out);
  }
}