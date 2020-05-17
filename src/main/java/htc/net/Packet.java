package htc.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import htc.net.packets.*;

public abstract class Packet {
  public abstract PacketType getType();

  public abstract boolean requiresResponse();

  /**
   * Writes this package into the given stream.
   * 
   * @param out the ObjectOutputStream to write to.
   * @throws IOException thrown by the ObjectOutputStream.
   */
  public abstract void write(ObjectOutputStream out) throws IOException;

  /**
   * Reads this package from the given stream.
   * 
   * @param in The ObjectInputStream to read from.
   * @throws IOException thrown by ObjectInputStream.
   */
  public abstract void read(ObjectInputStream in) throws IOException;

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

  /**
   * Reads all the packets from the stream and returns them in the same order
   * until an error is encountered. This closes the Stream.
   * 
   * @param in the stream
   * @return the packet
   */
  public static List<Packet> getAll(ObjectInputStream in) {
    List<Packet> packets = new ArrayList<>();
    Packet next;
    while ((next = Packet.getNext(in)) != null) {
      packets.add(next);
    }
    return packets;
  }

  /**
   * Retreives the very next package from the given input stream.
   * 
   * If the operation fails, null is returned. This permanently destroys the
   * stream.
   * 
   * @param in the stream
   * @return the packet
   */
  public static Packet getNext(ObjectInputStream in) {
    try {
      var type = (PacketType) in.readObject();
      var pack = Packet.construct(type);
      pack.read(in);
      return pack;
    } catch (IOException e) {
      System.err.println("Encountered IOException:");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.println("Could not cast to PacketType:");
      e.printStackTrace();
    }
    return null;
  }
}