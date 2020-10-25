package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;

import javax.swing.text.DefaultCaret;

public class Controller {
    @FXML
    TextArea salida;
    @FXML
    TextField entrada;
    @FXML
    Button boton;
    Bot bot=new Bot("Pato");
   static Cerebro_bot hilo=null;
    @FXML protected void initialize(){

        salida.setText("");
        entrada.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    if (bot.isPensando()){

                    }else {
                        salida.appendText(System.getProperty("user.name")+": "+entrada.getText().trim()+"\n\n");
                        bot.setInstruccion(entrada.getText().trim().toLowerCase());
                        bot.setPensando(true);
                        entrada.setText("");
                    }





                }

            }
        });
        hilo=new Cerebro_bot(salida,bot);
        hilo.start();
    }









}
