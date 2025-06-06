package com.example.combsortanimacaopo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    AnchorPane pane;
    Button botao_inicio;
    private int espaco = 70;
    private Button[] vet;
    private int TL = 8;

    private Font negrito = Font.font("Arial", FontWeight.BOLD,18);
    private Font normal = new Font(14);
    private Text tx1 = new Text();

    // VARIÁVEIS QUE PRECISAM SER GLOBAIS

    private int i,dist;
    private String troca;
    private Button aux;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Ordenação - Comb Sort (Animação)");
        pane = new AnchorPane();

        botao_inicio = new Button();
        botao_inicio.setLayoutX(650); botao_inicio.setLayoutY(570);
        botao_inicio.setText("Começar");
        botao_inicio.setOnAction(e -> moveBotoes(pane));
        pane.getChildren().add(botao_inicio);

        vet = new Button[TL];
        int random;
        for (int i = 0; i < TL; i++)
        {
            random = (int) (Math.random() * 50);
            vet[i] = new Button(String.valueOf(random));
            vet[i].setLayoutX(espaco*i+415); vet[i].setLayoutY(350);
            vet[i].setMinHeight(40); vet[i].setMinWidth(40);
            vet[i].setFont(new Font(14));
            pane.getChildren().add(vet[i]);
        }

        comeco();
        Scene scene = new Scene(pane, 1000, 600);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public void moveBotoes(AnchorPane pane)
    {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call () throws InterruptedException {
                try
                {
                    remove();
                    dist = TL;
                    durante();
                    while (dist > 0)
                    {
                        dist = (int) (dist/1.3);
                        for (i=0; i < (TL - dist); i++)
                        {
                            Destaca(vet[i],vet[i+dist]);
                            textodobotao("COMPARA",normal,new Text(),vet[i],13);
                            try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                            textodobotao("COMPARA",normal,new Text(),vet[i+dist],13);
                            Normaliza(vet[i],vet[i+dist]);
                            try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                            if (Integer.parseInt(vet[i].getText()) > Integer.parseInt(vet[i+dist].getText()))
                            {
                                Platform.runLater(() -> {
                                    troca = vet[i].getText();
                                    vet[i].setText(vet[i+dist].getText());
                                    vet[i+dist].setText(troca);
                                });
                                Destaca(vet[i],vet[i+dist]);
                                textodobotao("TROCA !",negrito,new Text(),vet[i],15);
                                textodobotao("TROCA !",negrito,new Text(),vet[i+dist],15);
                                Thread.sleep(1400);
                                Normaliza(vet[i],vet[i+dist]);

                            }
                            else
                            {
                                textodobotao("MANTÊM",normal,new Text(),vet[i],11);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                textodobotao("MANTÊM",normal,new Text(),vet[i+dist],11);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                            }
                        }
                    }
                    remove();
                    fim();
                } catch (Exception e) {
                    System.out.println("Deu Errado");
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    public void Destaca (Button Destaca1, Button Destaca2)
    {
        Platform.runLater(() -> {
            Destaca1.setFont(negrito);
            Destaca2.setFont(negrito);
        });
    }

    public void Normaliza (Button Normaliza1, Button Normaliza2)
    {
        Platform.runLater(() -> {
            Normaliza1.setFont(normal);
            Normaliza2.setFont(normal);
        });
    }

    public void durante ()
    {
        Platform.runLater(() -> {
            tx1.setText("ORDENANDO...");
            tx1.setFont(negrito);
            tx1.setLayoutX(615);
            tx1.setLayoutY(150);
            pane.getChildren().add(tx1);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public void remove ()
    {
        Platform.runLater(() -> pane.getChildren().remove(tx1));
    }


    public void textodobotao (String string, Font tipo,Text texto, Button botao, int ajuste)
    {
        Platform.runLater(() -> {
            texto.setText(string);
            texto.setFont(tipo);
            texto.setLayoutX(botao.getLayoutX()-ajuste);
            texto.setLayoutY(botao.getLayoutY()-20);
            pane.getChildren().add(texto);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
        Platform.runLater(() -> pane.getChildren().remove(texto));
    }

    public void textodobotaosematraso (String string, Font tipo,Text texto, Button botao, int ajuste)
    {
        Platform.runLater(() -> {
            texto.setText(string);
            texto.setFont(tipo);
            texto.setLayoutX(botao.getLayoutX()-ajuste);
            texto.setLayoutY(botao.getLayoutY()-20);
            pane.getChildren().add(texto);
        });
        Platform.runLater(() -> pane.getChildren().remove(texto));
    }


    public void comeco ()
    {
        Platform.runLater(() -> {
            tx1.setText("PESQUISA E ORDENAÇÃO - COMB SORT");
            tx1.setFont(negrito);
            tx1.setLayoutX(500);
            tx1.setLayoutY(130);
            pane.getChildren().add(tx1);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }


    public void fim ()
    {
        Platform.runLater(() -> {
            tx1.setText("TODOS FORAM NÚMEROS ORDENADOS !");
            tx1.setFont(negrito);
            tx1.setLayoutX(500);
            tx1.setLayoutY(150);
            pane.getChildren().add(tx1);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public static void main(String[] args) {
        launch();
    }
}