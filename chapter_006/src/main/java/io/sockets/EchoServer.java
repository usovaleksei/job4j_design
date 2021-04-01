package io.sockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class emulated server work
 *
 * @author Aleksei Usov
 * @since 25/03/2021
 */

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    /** @noinspection checkstyle:InnerAssignment*/
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String msg = "";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=")) {
                            msg = str;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (msg.contains("?msg=Exit")) {
                        out.write("Bye".getBytes());
                        server.close();
                    } else if (msg.contains("?msg=Hello")) {
                        out.write("Hello, dear friend".getBytes());
                    } else {
                        out.write("What".getBytes());
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in server work", e);
        }
    }
}
