package htc.net;

public enum PacketType {
  PEER_REQ, PEER_RESP, // Request to get a set of peers to communicate with
  NEW_BLOCK, NEW_BLOCK_ACK, // Broadcast information about a new block
  NEW_TRANS, NEW_TRANS_ACK, // Broadcast information about a new transaction
  STATUS_REQ, STATUS_RESP, // Request to get the current state of the chain
}