package htc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import htc.net.packets.*;

public abstract class Packet {
  public abstract PacketType getType();

  public abstract boolean requiresResponse();

  /**
   * Writes this package into the given stream.
   * 
   * @param out the DataOutputStream to write to.
   * @throws IOException thrown by the DataOutputStream.
   */
  public abstract void write(DataOutputStream out) throws IOException;

  /**
   * Reads this package from the given stream.
   * 
   * @param in The DataInputStream to read from.
   * @throws IOException thrown by DataInputStream.
   */
  public abstract void read(DataInputStream in) throws IOException;

  public static Packet construct(PacketType type) {
    switch (type) {
      case PEER_REQ:
        return new PeerReq();
      case PEER_RESP:
        return new PeerResp();
      default:
        throw new UnsupportedOperationException();
    }
  }

}