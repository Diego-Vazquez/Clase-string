package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Locale;

public class Controller {
    int conta=0;

    @FXML protected void initialize(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(null);
        VBox box=new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPrefHeight(100);
        box.setPrefWidth(400);
        TextField txt=new TextField();
        txt.setPrefHeight(20);
        txt.setPrefWidth(100);
        txt.setPromptText("Escribe algo aqui");
        Button button=new Button("CharAt(2)");

        Label lb=new Label("...");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (txt.getText().isEmpty()){

                }else {
                    conta++;
                    accion(lb,txt.getText(),conta,button);
                }



            }
        });

        box.getChildren().add(0,txt);
        box.getChildren().add(1,button);
        box.getChildren().add(2,lb);
        alert.getDialogPane().setContent(box);
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                System.exit(0);
            }
        });
        alert.showAndWait();


    }



    public void accion(Label label,String palabra, int contador, Button boton){

        switch (contador){
            case 1:{ label.setText(String.valueOf(palabra.charAt(2)));break;}
            case 2:{ label.setText(palabra.replace("a","@"));boton.setText("replace(a,@)"); break;}
            case 3:{label.setText(palabra.substring(3));boton.setText("substring(3)");break;}
            case 4:{label.setText(palabra.concat(" palabra concatenada"));boton.setText("concat()");break;}
            case 5:{label.setText(palabra.toLowerCase());boton.setText("tolowercase()");break;}
            case 6:{label.setText(palabra.intern());boton.setText("intern()");break;}
            case 7:{label.setText(palabra.toUpperCase());boton.setText("touppercase()");break;}
            case 8:{label.setText(palabra.trim());boton.setText("Trim()");break;}
            case 9:{label.setText(String.valueOf(palabra.codePointAt(2)));boton.setText("codepointat(2)");break;}
            case 10:{label.setText(String.valueOf(palabra.codePointBefore(2)));boton.setText("codepointbefore(2)");break;}
            case 11:{label.setText(String.valueOf(palabra.codePointCount(0,palabra.length())));boton.setText("codepoincount(0,longitud de la palabra)");break;}
            case 12:{label.setText(String.valueOf(palabra.compareTo("casa")));boton.setText("compareTo(casa)");break;}
            case 13:{label.setText(String.valueOf(palabra.contains("a")));boton.setText("contains(a)");break;}
            case 14:{label.setText(String.valueOf(palabra.equalsIgnoreCase("casa")));boton.setText("equalsignorecase(casa)");break;}
            case 15:{label.setText(String.valueOf(palabra.subSequence(0,3)));boton.setText("subsequence(0,3)");break;}
            case 16:{label.setText(String.valueOf(palabra.startsWith("a")));boton.setText("startswith(a)");break;}
            case 17:{label.setText(String.valueOf(palabra.getBytes()));boton.setText("getbytes()");break;}
            case 18:{label.setText(String.valueOf(palabra.hashCode()));boton.setText("hashcode()");break;}
            case 19:{label.setText(String.valueOf(palabra.lastIndexOf(2)));boton.setText("lastindexof(2)");break;}
            case 20:{label.setText(String.valueOf(palabra.matches("casa")));boton.setText("matches(casa)"); break;}
            case 21:{ label.setText(String.valueOf(palabra.indexOf("a")));boton.setText("indexof(a)");break;}
            case 22:{ label.setText(String.valueOf(palabra.offsetByCodePoints(0,1)));boton.setText("offsetbycodepoints");break;}
            case 23:{ label.setText(String.valueOf(palabra.endsWith("a")));boton.setText("endswith(a)");break;}
            case 24:{ label.setText(String.valueOf(palabra.split("casa")));boton.setText("split(casa)");break;}
            case 25:{ label.setText(String.valueOf(palabra.compareToIgnoreCase("casa")));boton.setText("comparetoignorecase(casa)");break;}
            case 26:{ label.setText(String.valueOf(palabra.length()));boton.setText("length()");break;}
            case 27:{ label.setText(String.valueOf(palabra.equals("casa")));boton.setText("equals(casa)");break;}
            case 28:{ label.setText(palabra.substring(0,6));boton.setText("substring(0,6)");break;}
            case 29:{ label.setText(palabra.toUpperCase(Locale.ENGLISH));boton.setText("touppercase(Locale.ENGLISH)");break;}
            case 30:{ label.setText("ya no supe que otro usar XD");conta=0;break;}
        }
    }



}
