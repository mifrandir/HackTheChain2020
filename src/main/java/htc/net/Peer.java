package htc.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.HashMap;

public class Peer {
  private static int nextID = 0;
  private static Map<Integer, Peer> instances = new HashMap<Integer, Peer>();
  private final int ID;
  private InetAddress addr;

  public Peer() {
    this.ID = nextID++;
    Peer.instances.put(this.ID, this);
  }

  public Peer(InetAddress addr) {
    this();
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

  public void write(DataOutputStream out) throws IOException {
    byte[] a = addr.getAddress();
    out.write(a.length);
    out.write(addr.getAddress());
  }

  public void read(DataInputStream in) throws IOException {
    int n = in.readInt();
    byte[] a = new byte[n];
    in.read(a);
    this.addr = InetAddress.getByAddress(a);
  }
}