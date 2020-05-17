package htc.net;

import java.net.Socket;

public interface ConnectionHandler {
  public void handle(Socket connection);
}