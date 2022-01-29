import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Cient implements Initializable {
public ListView<String> listView;
public TextField textField;
private DataInputStream is;
private DataOutputStream os;

public void sendMessage(ActionEvent actionEvent) throws IOException {
    String text = textField.getText();
    textField.clear();
    os.writeUTF(text);
    os.flush();
}
private void readLoop(){
    try{
        while(true){
            String msg = is.readUTF();
            Platform.runLater(()->{
                listView.getItems().add(msg);
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { Socket socket = new Socket("localhost",8189);
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
            Thread readThread = new Thread(this::readLoop);
            readThread.setDaemon(true);
            readThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}