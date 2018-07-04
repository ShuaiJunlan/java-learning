package cn.shuaijunlan.netty.tansfer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 14:10 2018/4/15.
 * 基于Java API实现Oio Server
 */
public class PlainOioServer {
    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        for (;;){
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            new Thread(() -> {
                OutputStream outputStream;
                try {
                    outputStream = clientSocket.getOutputStream();
                    outputStream.write("Hi! \r\n".getBytes(Charset.forName("UTF-8")));
                    outputStream.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        }
    }
}
