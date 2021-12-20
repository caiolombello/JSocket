import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Conectando cliente ao servidor...");

        // Definindo IP
        System.out.print("IP: ");
        String ip = input.nextLine();

        // Definindo Porta
        System.out.print("Porta: ");
        int port = input.nextInt();

        // Abrindo conexão
        Socket socket = new Socket(ip, port);

        // Stream
        String message;
        DataInputStream streamInput;
        DataOutputStream streamOutput;
        do {
            // Definindo saída
            streamOutput = new DataOutputStream(socket.getOutputStream());
            System.out.println("Mensagem: ");
            message = input.nextLine();
            streamOutput.writeUTF(message);

            // Definindo entrada
            streamInput = new DataInputStream(socket.getInputStream());
            String newMessage = streamInput.readUTF();
            System.out.println(newMessage);

        } while (!message.equals("disconnect"));

        // Fechando entrada e saída
        streamInput.close();
        streamOutput.close();

        // Fechando conexão
        System.out.println("Desconectado do servidor!");
        input.close();
        socket.close();
    }
}
