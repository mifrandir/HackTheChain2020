package htc.net.packets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.io.IOException;
import htc.net.Packet;
import htc.net.PacketType;

public class PeerResp extends Packet {
  private InetAddress[] peers;

  public PeerResp() {
    this.peers = null;
  }

  public PeerResp(InetAddress[] peers) {
    this.peers = peers;
  }

  public InetAddress[] getPeers() {
    return this.peers; // Unsafe opeartion, but fine for now.
  }

  @Override
  public PacketType getType() {
    return PacketType.PEER_RESP;
  }

  @Override
  public boolean requiresResponse() {
    return false;
  }

  @Override
  public void write(ObjectOutputStream out) throws IOException {
    out.writeInt(this.peers.length);
    for (InetAddress addr : this.peers) {
      out.writeObject(addr);
    }
  }

  @Override
  public void read(ObjectInputStream in) throws IOException {
    int n = in.readInt();
    this.peers = new InetAddress[n];
    for (int i = 0; i < n; i++) {
      try {
        this.peers[i] = (InetAddress) in.readObject();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return;
      }
    }
  }
}