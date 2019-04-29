package cn.shuaijunlan.java.basic.learning.io.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:51 AM 11/26/18.
 */
public class IOServer {
    public static void main(String[] args) {
        try {
            final ServerSocket serverSocket = new ServerSocket(8008);
            ExecutorService executor = Executors.newCachedThreadPool();
            executor.execute(() -> {
                while (true){
                    try {
                        Socket socket = serverSocket.accept();

                        new Thread(() -> {
                            int len;
                            byte[] data = new byte[1024];
                            try {
                                InputStream inputStream =socket.getInputStream();

                                while ((len = inputStream.read(data)) != -1){

                                    System.out.println(new String(data, 0, len));
                                }
                                System.out.println(len);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                // socket
                            }

                        }).start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
