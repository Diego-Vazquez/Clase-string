package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Controller {
    @FXML
    JFXTextArea consola;
    @FXML
    TextArea txt1;
    @FXML
    Button boton1;
    private String text;


    @FXML protected void initialize(){
        try {
            text=leer(crear_fichero("Receta.txt"));
            txt1.setText(text);
            boton1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        consola.setText("Numero de lineas: "+contar_lineas(text));
                        consola.setText(consola.getText().concat("\n"+buscar_cantidad("queso")));
                    } catch (Exception e) { e.printStackTrace(); }
                }
            });
        } catch (Exception e) { e.printStackTrace(); }


    }



    public File crear_fichero(String nombre)throws Exception{
        File archivo=new File(nombre);
        if (archivo.exists()){
            System.out.println("el archivo ya existe");
        }else {
            archivo.createNewFile();
            System.out.print("Archivo creado");

        }
        return archivo;
    }


    public String leer(File archivo)throws Exception{
        BufferedReader reader=new BufferedReader(new FileReader(archivo));
        String texto="";
        String aux="";
        while ((texto=reader.readLine())!=null){
            aux=aux.concat(texto+"\n");
        }
        return aux;


    }

    public int contar_lineas(String cadena)throws Exception{
        return cadena.split("\n").length;
    }

    public String buscar_cantidad(String ingrediente)throws Exception{
        String[]aux = text.split("\n");
        String retur="";
        for (String a: aux){
            if (a.contains(ingrediente)){
                retur=a.split(" ")[0];
                if (a.split(" ")[1].equalsIgnoreCase("kg")||a.split(" ")[1].equalsIgnoreCase("pz")){
                    retur=retur.concat(" "+a.split(" ")[1]);
                }
                break;
            }
        }
        return retur;

    }


}
