package htc.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.HashMap;

public class Peer {
  private static Map<Integer, Peer> instances = new HashMap<Integer, Peer>();
  private final int ID;
  private InetAddress addr;

  public Peer(int id) {
    this.ID = id;
    Peer.instances.put(this.ID, this);
  }

  public Peer(int id, InetAddress addr) {
    this(id);
    this.addr = addr;
  }

  public int getID() {
    return this.ID;
  }

  public static Peer getByID(int id) {
    return instances.get(id);
  }

  public void forget() {
    instances.remove(this.ID);
  }

  public InetAddress getAddr() {
    return addr;
  }

  public void write(ObjectOutputStream out) throws IOException {
    byte[] a = addr.getAddress();
    out.write(a.length);
    out.write(a);
  }

  public void read(ObjectInputStream in) throws IOException {
    int n = in.readInt();
    byte[] a = new byte[n];
    in.read(a);
    this.addr = InetAddress.getByAddress(a);
  }
}