package htc.net.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import htc.net.Peer;
import htc.net.Packet;
import htc.net.PacketType;

public class PeerResp extends Packet {
  private Peer[] peers;

  public PeerResp() {
    this.peers = null;
  }

  public PeerResp(Peer[] peers) {
    this.peers = peers;
  }

  @Override
  public PacketType getType() {
    return PacketType.PEER_RESP;
  }

  @Override
  public boolean requiresResponse() {
    return true;
  }

  @Override
  public void write(DataOutputStream out) throws IOException {
    out.writeInt(this.peers.length);
    for (Peer p : this.peers) {
      p.write(out);
    }
  }

  @Override
  public void read(DataInputStream in) throws IOException {
    int n = in.readInt();
    this.peers = new Peer[n];
    for (int i = 0; i < n; i++) {
      this.peers[i] = new Peer();
      this.peers[i].read(in);
    }
  }
} 