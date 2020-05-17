package htc.net;

import java.util.*;
import java.util.concurrent.locks.*;

import htc.chain.Block;
import htc.net.packets.PeerReq;
import htc.net.packets.PeerResp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class PeerManager {

  private static int nextID = 0;
  private static int MAX_PEER_COUNT = 10;

  private Map<Integer, Peer> peers = new HashMap<Integer, Peer>();

  /**
   * Given an address this methods finds a corresponding peer by ID or if
   * none-existant creates a new one.
   * 
   * @param addr the addr of the desired peer.
   * @return the ID of the peer that matches this address
   */
  public int getID(InetAddress addr) {
    for (Peer p : this.peers.values()) {
      if (p.getAddr().equals(addr)) {
        return p.getID();
      }
    }
    return this.insert(addr).getID();
  }

  private Peer insert(InetAddress addr) {
    Peer p = new Peer(nextID++, addr);
    this.peers.put(p.getID(), p);
    if (this.peers.size() >= PeerManager.MAX_PEER_COUNT) {
      this.requestPeers(p.getID());
    }
    return p;
  }

  private void requestPeers(int id) {
    try {
      Socket conn = new Socket(this.peers.get(id).getAddr(), Server.DEFAULT_PORT);
      var req = new PeerReq();
      var out = new ObjectOutputStream(conn.getOutputStream());
      out.writeObject(req.getType());
      req.write(out);
      var in = new ObjectInputStream(conn.getInputStream());
      if ((PacketType) in.readObject() == PacketType.PEER_RESP) {
        var resp = new PeerResp();
        resp.read(in);
        var peers = resp.getPeers();
        for (InetAddress addr : peers) {
          this.getID(addr);
        }
      }
      conn.close();
      out.close();
      in.close();
      return;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.println("Got invalid response.");
      e.printStackTrace();
    }
  }

  public void broadcastNewBlock(Block b) {
    // TODO: Create new block packet and await response
  }

  public List<Peer> getPeers(int sender) {
    List<Peer> peers = new ArrayList<Peer>();
    for (Peer p : this.peers.values()) {
      if (p.getID() == sender) {
        continue;
      }
      peers.add(p);
      if (peers.size() >= PeerManager.MAX_PEER_COUNT) {
        break;
      }
    }
    return peers;
  }
}