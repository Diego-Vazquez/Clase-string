package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

import javax.swing.text.DefaultCaret;

public class Cerebro_bot extends Thread{
    private TextArea salida;
    private Bot bot;
    private int contador=1;
    private String aux="";



    public Cerebro_bot(TextArea salida, Bot bot) {
        this.salida = salida;
        this.bot = bot;
    }

    public TextArea getSalida() {
        return salida;
    }

    public void setSalida(TextArea salida) {
        this.salida = salida;
    }

    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }


    @Override
    public void run() {
      while (true){
          try {
              Platform.runLater(new Runnable() {
                  @Override
                  public void run() {

                      if (bot.isPensando()){
                          if (contador==4){
                              aux=salida.getText().replace(bot.getNombre_del_bot()+": "+"...",bot.getNombre_del_bot()+": "+bot.pensar()+"\n\n");
                              salida.setText(aux);
                              contador=1;
                              bot.setPensando(false);
                              salida.selectEnd();
                              salida.deselect();


                          }else if (contador==3){
                              aux=salida.getText().replace(bot.getNombre_del_bot()+": "+"..",bot.getNombre_del_bot()+": "+"...");
                              salida.setText(aux);
                              contador++;
                          }else if(contador==2){
                              aux=salida.getText().replace(bot.getNombre_del_bot()+": "+".",bot.getNombre_del_bot()+": "+"..");
                              salida.setText(aux);
                              contador++;
                          }else if (contador==1){
                              salida.appendText(bot.getNombre_del_bot()+": "+".");
                              contador++;

                          }

                      }else {
                          if (bot.isTerminar()){
                              contador++;
                              if (contador==4){
                                  System.exit(0);
                              }
                          }
                      }





                  }
              });

              Thread.sleep(600);
          }catch (Exception e){

          }
      }
    }
}
