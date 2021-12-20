import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Abrindo servidor...");

        // Mostrando IP
        InetAddress inetaddress = InetAddress.getLocalHost();
        System.out.println("Seu IP é: " + inetaddress);

        // Definindo porta
        System.out.println("Defina a porta do servidor: ");
        int port = input.nextInt();

        // Abrindo conexão
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("A porta " + port + " foi aberta!");
        System.out.println("Servidor esperando receber mensagem de cliente...");

        // Aguardando solicitação de conexão do cliente
        Socket socket = serverSocket.accept();

        // Mostrando IP do cliente
        System.out.println("Cliente: " + socket.getInetAddress().getHostAddress() + " conectado.");

        // Stream
        boolean i = true;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;
        while (i) {
            // Definindo entrada
            inputStream = new DataInputStream(socket.getInputStream());
            String message = inputStream.readUTF();
            System.out.println("Cliente: " + message);

            // Definindo saída
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF("Mensagem recebida!");
        }

        // Fechando entrada e saída
        inputStream.close();
        outputStream.close();

        // Fechando servidor
        System.out.println("Fechando servidor!");
        socket.close();
        serverSocket.close();
    }
}
