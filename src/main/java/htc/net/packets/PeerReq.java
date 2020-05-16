package htc.net.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import htc.net.Packet;
import htc.net.PacketType;

public class PeerReq extends Packet {
  public PeerReq() {
  }

  @Override
  public PacketType getType() {
    return PacketType.PEER_REQ;
  }

  @Override
  public boolean requiresResponse() {
    return true;
  }

  @Override
  public void write(DataOutputStream out) throws IOException {
    // We don't need to write any content.
  }

  @Override
  public void read(DataInputStream in) throws IOException {
    // We don't need to read any content.
  }
}