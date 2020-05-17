package htc.net.packets;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
  public void write(ObjectOutputStream out) throws IOException {
    // We don't need to write any content.
  }

  @Override
  public void read(ObjectInputStream in) throws IOException {
    // We don't need to read any content.
  }
}