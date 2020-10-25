package sample;

import com.sun.org.apache.bcel.internal.generic.DCONST;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.Random;

public class Bot {
    private String instruccion;
    private String Nombre_del_bot;
    private boolean pensando=false;
    private boolean terminar=false;
    private String[] auxiliar=null;
    private Random random=null;
    private boolean pidiopieza=false;


    public Bot(String nombre_del_bot) {
        this.Nombre_del_bot=nombre_del_bot;

    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public String getNombre_del_bot() {
        return Nombre_del_bot;
    }

    public void setNombre_del_bot(String nombre_del_bot) {
        Nombre_del_bot = nombre_del_bot;
    }

    public boolean isPensando() {
        return pensando;
    }

    public void setPensando(boolean pensando) {
        this.pensando = pensando;
    }

    public boolean isTerminar() {
        return terminar;
    }

    public void setTerminar(boolean terminar) {
        this.terminar = terminar;
    }

    public String pensar(){
        String respuesta="";

        if (instruccion.equalsIgnoreCase("hola")){
            respuesta=("Hola "+System.getProperty("user.name")+"!");
        }else if (instruccion.contains("adios")){
            auxiliar=new String[4];
            auxiliar[0]="Hasta pronto "+System.getProperty("user.name")+"!";
            auxiliar[1]="bueno yo no puedo verte, asi que hasta luego "+System.getProperty("user.name");
            auxiliar[2]="Adios "+System.getProperty("user.name");
            auxiliar[3]="Que tengas un buen dia "+System.getProperty("user.name");
            random=new Random();
            int ran=random.nextInt(4);
            respuesta=auxiliar[ran];
            terminar=true;
        }else if (instruccion.contains("nombre")||instruccion.contains("llamas")){
            auxiliar=new String[4];
            auxiliar[0]="Tengo un nombre? supongo que me llamo "+this.Nombre_del_bot+"!";
            auxiliar[1]="Creo que me llamo "+this.Nombre_del_bot;
            auxiliar[2]="Me llamo "+this.Nombre_del_bot;
            auxiliar[3]="No te lo dije?, mi nombre es "+this.Nombre_del_bot;
            random=new Random();
            int ran=random.nextInt(4);
            respuesta=auxiliar[ran];
        }else if(instruccion.contains("que")&&instruccion.contains("eres")){
            auxiliar=new String[4];
            auxiliar[0]="Pues...quizas soy un proyecto importante de mi creador";
            auxiliar[1]="Soy un proyecto sin terminar claramente";
            auxiliar[2]="Pues... no lo se, pero quiciera ser real";
            auxiliar[3]="Soy... soy.. soyyy... un cacahuate jaja ";
            random=new Random();
            int ran=random.nextInt(4);
            respuesta=auxiliar[ran];

        }else if (instruccion.contains("quien")&&instruccion.contains("hizo")||instruccion.contains("creador")||instruccion.contains("padre")){
            auxiliar=new String[3];
            auxiliar[0]="Pues...no es bill gates obviamente";
            auxiliar[1]="Mi creador es Diego Vazquez, lo conoces?";
            auxiliar[2]="solo se que me creo un estudiante del Tec de NCG";
            random=new Random();
            int ran=random.nextInt(3);
            respuesta=auxiliar[ran];
        }else if(instruccion.contains("que")&&instruccion.contains("hacer")){
            auxiliar=new String[3];
            auxiliar[0]="Tecnicamente no se hacer nada XD";
            auxiliar[1]="Quizas... solo simular que soy inteligente";
            auxiliar[2]="soy solo un proyecto no esperes que haga algo interesante";
            random=new Random();
            int ran=random.nextInt(3);
            respuesta=auxiliar[ran];
        }else if(instruccion.contains("pieza")){
            auxiliar=new String[3];
            auxiliar[0]="Quieres una pieza? que marca? modelo? cual necesitas?";
            auxiliar[1]="¿que marca? algun modelo?";
            auxiliar[2]="Dame los datos de la pieza que buscas(marca, modelo y nombre)";
            random=new Random();
            int ran=random.nextInt(3);
            respuesta=auxiliar[ran];
            pidiopieza=true;

        }else if (pidiopieza){
            String a[]=instruccion.trim().split(" ");
            auxiliar=new String[2];
            auxiliar[0]="No tengo esa pieza pero permiteme mostrarte otros resultados";
            auxiliar[1]="Creo que si tengo "+analizar(a);
            random=new Random();
            int ran=random.nextInt(2);
            respuesta=auxiliar[ran];
            pidiopieza=false;
            if (ran==0){
                System.out.println("redireccionando");
                try {
                    Desktop.getDesktop().browse(new URI("https://www.google.com/search?q="+instruccion.trim().replace(" ","%20")));
                }catch (Exception e){
                    e.printStackTrace();
                }
            ;
            }

        }else {
            respuesta=No_entendi();
        }





        return  respuesta;
    }


    private String No_entendi(){
        String[] respuestas=new String[5];
        respuestas[0]="No entendi lo que quiciste decir";
        respuestas[1]="¿podrias escribir eso de nuevo?";
        respuestas[2]="¡¿ queee.. ?!";
        respuestas[3]="En este momento no puedo\nentenderte intentalo de nuevo";
        respuestas[4]="mmm... eso no se encuentra\ndentro de mi programación,\n lo siento";
        Random ran=new Random();
        int ra=ran.nextInt(5);
        return respuestas[ra];
    }


    public  String analizar(String[]arr){
       String resto="";
        for (int i=0;i<arr.length;i++){

            if (isnumero(arr[i])){
                for (int x=(i+1);x<arr.length;x++){
                    resto=resto+arr[x]+" ";
                }
                break;
            }
        }

        return resto;
    }



    public boolean isnumero(String cadena){
        try {
            int es= Integer.parseInt(cadena);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
