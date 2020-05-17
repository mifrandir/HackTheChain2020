package htc.net;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Closeable {
  public static final int DEFAULT_PORT = 8000;
  private final int port;
  private final ConnectionHandler handler;
  private ServerSocket sock;

  public Server(int port, ConnectionHandler handler) {
    this.port = port;
    this.handler = handler;
  }

  public void start() {
    System.err.println("Started server");

    new Thread(() -> {
      try {
        this.sock = new ServerSocket(port);
        while (!sock.isClosed()) {
          Socket conn = sock.accept();
          System.out.println("Incoming connection: " + conn.getInetAddress() + " " + conn.getPort());
          handler.handle(conn);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();
  }

  @Override
  public void close() throws IOException {
    sock.close();
  }
}