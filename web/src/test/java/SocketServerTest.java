import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/29.
 */
public class SocketServerTest {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            Socket s = ss.accept();
            InputStream inp = s.getInputStream();
            byte[] b = new byte[1024];
            int length = inp.read(b);

            if (length>0){
                System.out.println(new String(b));
            }

            OutputStream out = s.getOutputStream();
            out.write("<html><body>111</body></html>".getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
