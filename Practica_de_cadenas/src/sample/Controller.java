package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {

    @FXML
    TextArea receta,ingredientes,preparacion,preguntas;
    @FXML
    Button boton;
    private File archivo=null;
    private String recetaa;
    private double[]obtener=null;

    @FXML protected void initialize() throws Exception {
        ingredientes.setEditable(false);
        preguntas.setEditable(false);
        preparacion.setEditable(false);
        receta.setEditable(true);
        archivo=new File("Receta.txt");
        recetaa=leer(archivo);
        receta.setText(recetaa);
        Separar();
        obtener = obtener_cantidades();


        receta.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().isLetterKey()||event.getCode().isDigitKey()||event.getCode().equals(KeyCode.BACK_SPACE)||event.getCode().equals(KeyCode.SPACE)){
                    actualizar();
                }
            }
        });




        boton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               calcular(obtener_numero_de_personas());
                preguntas.setText("¿Cuántos gramos de chiles en total son utilizados?\n" +contar_gramos()+" Gramos\n"+
                        "¿Cuál es el cuarto paso del proceso de preparación?\n" +cuarto_paso()+"\n\n"+
                        "Mostrar todos los ingredientes que sean asados (mostrar toda la linea)\n" +mostrar_asados()+"\n"+
                        "Mostrar cuántos ingredientes de cada cosa se necesita para n personas (la receta es para 60 personas)");

            }
        });

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


    public void Separar(){
        ingredientes.clear();
        preguntas.clear();
        preparacion.clear();
        String[]aux=recetaa.split("\n");
        boolean encontro=false;
        int lineas=0;
        for (String a: aux){
            if (a.contains("_")){
             lineas++;
             continue;
            }
            switch (lineas){
                case 1:{ ingredientes.setText(ingredientes.getText()+a+"\n"); break;}
                case 2:{ if (a.contains("Preparación"))continue;preparacion.setText(preparacion.getText()+a+"\n"); break;}
                case 3:{ preguntas.setText(preguntas.getText()+a+"\n");break;}

            }

        }
    }


    public void actualizar(){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(archivo));
            writer.write("");
            writer.write(receta.getText());
            writer.close();
            recetaa=leer(archivo);
            Separar();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String contar_gramos(){
        double contador=0;
        for (String a: ingredientes.getText().split("\n")){
            if (a.contains("Gramos")&&a.contains("chile")){
                contador= contador+Double.parseDouble(a.split(" ")[0]);
            }
        }
        return String.valueOf(contador);
    }


    public String cuarto_paso(){
        return preparacion.getText().split("\n")[7];
    }

    public String mostrar_asados(){
        String[] a=ingredientes.getText().split("\n");
        String c="";
        for (String b: a){
            b.toLowerCase();
            if (b.contains("asad")){
                c=c+b+"\n";
            }
        }
        return c;

    }


    public void calcular(int personas){
        String[] a=ingredientes.getText().split("\n");
        String cadena="";

        double[] cantidades =new double[obtener.length];
        System.out.println("long "+cantidades.length);
        for (int i=0;i<obtener.length;i++){
            cantidades[i]=(obtener[i]/60);
        }
        for (int i=0;i<cantidades.length;i++){
            cantidades[i]=(cantidades[i]*personas);
        }

        for (int i=0;i<a.length;i++){
            try{
                String[] s=a[i].split(" ");
                String aux= String.valueOf(redondear(cantidades[i],1));
                for (int x=1;x<s.length;x++){
                    aux=aux+" "+s[x].trim();

                }

                System.out.println(aux+"\n");
                cadena=cadena+aux+"\n";
            }catch (Exception e){

            }


        }
        ingredientes.setText(cadena);

    }




    public boolean isnumero(String num){
        try {
            double a= Double.parseDouble(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public double[] obtener_cantidades(){
        String[] a=ingredientes.getText().split("\n");

        int conta=0;
        for (String b: a){
            if (isnumero(b.split(" ")[0]))conta++;
        }
        double[] cantidades=new double[conta];
        for (int i=0;i<cantidades.length;i++){
            if (isnumero(a[i].split(" ")[0])){
                cantidades[i]= Double.parseDouble(a[i].split(" ")[0]);
            }

        }
        return cantidades;
    }


    public BigDecimal redondear(double numero, int sifras_significativas){
        BigDecimal decimal=new BigDecimal(numero);
        decimal=decimal.setScale(sifras_significativas, RoundingMode.HALF_UP);
        return decimal;
    }


    public int obtener_numero_de_personas(){
        String[] a=preparacion.getText().split("\n");
        String aux="";
        for (String b: a){
            if (b.contains("PERSONAS")){
                aux=b.split(" ")[0];
                break;
            }
        }
        return Integer.parseInt(aux);
    }
}

