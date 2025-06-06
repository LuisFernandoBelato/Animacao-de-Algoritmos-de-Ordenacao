package com.example.merge1taskthreadanimacao;

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

    // RECURSOS PRINCIPAIS

    AnchorPane pane;
    Button botao_inicio;
    private int Espacamento = 70;
    private Button[] vetor;
    private Button[] Particao1;
    private Button[] Particao2;
    //private Text textoauxiliar;

    // RECURSOS AUXILIARES

    private Font negrito = Font.font("Arial", FontWeight.BOLD,18);
    private Font normal = new Font(14);
    private Text texto1 = new Text();
    private Text texto2 = new Text();

    // VARIÁVEIS QUE PRECISAM SER GLOBAIS

    private int seq,i,j,k,aux,seqaux,meio;
    private int Tam = 16; // MERGE 1


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Merge Sort 1ª Implementação - Animação");
        pane = new AnchorPane();

        botao_inicio = new Button();
        botao_inicio.setLayoutX(650); botao_inicio.setLayoutY(570);
        botao_inicio.setText("Ordenar");
        botao_inicio.setOnAction(e -> moveBotoes(pane));
        pane.getChildren().add(botao_inicio);

        vetor = new Button[Tam];
        for (int i = 0, ramdom; i < Tam; i++)
        {
            ramdom = (int) (Math.random() * 70) + 1;
            vetor[i] = new Button(String.valueOf(ramdom));
            vetor[i].setLayoutX(140+Espacamento*i); vetor[i].setLayoutY(200);
            vetor[i].setMinHeight(40); vetor[i].setMinWidth(40);
            vetor[i].setFont(new Font(14));
            pane.getChildren().add(vetor[i]);
        }

        Particao1 = new Button[Tam/2];
        Particao2 = new Button[Tam/2];
        for (int i = 0; i < Tam/2; i++)
        {
            Particao1[i] = new Button("#");
            Particao1[i].setLayoutX(415+Espacamento*i); Particao1[i].setLayoutY(350);
            Particao1[i].setMinHeight(40); Particao1[i].setMinWidth(40);
            Particao1[i].setFont(new Font(14));
            pane.getChildren().add(Particao1[i]);

            Particao2[i] = new Button("#");
            Particao2[i].setLayoutX(415+Espacamento*i); Particao2[i].setLayoutY(400);
            Particao2[i].setMinHeight(40); Particao2[i].setMinWidth(40);
            Particao2[i].setFont(new Font(14));
            pane.getChildren().add(Particao2[i]);
        }

        InsereTextoApresentacao();
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
                    RemoveTextoApresentacao();
                    seq = 1;
                    while (seq < Tam)
                    {
                        // PARTIÇÃO

                        meio = Tam / 2;

                        InsereTextoNaParticao();
                        for (i=0; i < meio; i++)
                        {

                            TextoBotao("Daqui...",normal,new Text(),vetor[i],"cima",3);
                            TextoBotao("Pra Cá!",normal,new Text(),Particao1[i],"cima",3);

                            Destaca(Particao1[i],vetor[i]);
                            Platform.runLater(() -> {Particao1[i].setText(vetor[i].getText());});
                            try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                            Normaliza(Particao1[i],vetor[i]);
                            try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

                            //----------------------------------------------------------------------------

                            TextoBotao("Daqui...",normal,new Text(),vetor[i + meio],"cima",3);
                            TextoBotao("Pra Cá!",normal,new Text(),Particao2[i],"baixo",3);

                            Destaca(Particao2[i],vetor[i + meio]);
                            Platform.runLater(() -> {Particao2[i].setText(vetor[i + meio].getText());});
                            try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
                            Normaliza(Particao2[i],vetor[i + meio]);
                            try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}

                        }
                        RemoveTextoNaParticao();

                        // FUSÃO

                        seqaux = seq;
                        aux = seq;
                        i=0;j=0;k=0;
                        InsereTextoNaFusao();
                        while (k < Tam)
                        {
                            while (i < seqaux && j < seqaux)
                            {
                                Destaca(Particao1[i],Particao2[j]);
                                TextoBotao("Compara",normal,new Text(),Particao1[i],"cima",7);
                                TextoBotao("Compara",normal,new Text(),Particao2[j],"baixo",7);
                                Normaliza(Particao1[i],Particao2[j]);
                                if (Integer.parseInt(Particao1[i].getText()) < Integer.parseInt(Particao2[j].getText()))
                                {
                                    Platform.runLater(() -> vetor[k].setText(Particao1[i].getText()));
                                    TextoBotao("Escreve o Menor Deles",negrito,new Text(),vetor[k],"cima",70);
                                    Destaca(Particao1[i],vetor[k]);
                                    try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                    Normaliza(Particao1[i],vetor[k]);
                                    try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                    k++; i++;
                                }
                                else
                                {
                                    Platform.runLater(() -> vetor[k].setText(Particao2[j].getText()));
                                    TextoBotao("Escreve o Menor Deles",negrito,new Text(),vetor[k],"cima",70);
                                    Destaca(Particao2[j],vetor[k]);
                                    try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                    Normaliza(Particao2[j],vetor[k]);
                                    try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                    k++; j++;
                                }
                            }
                            while (i < seqaux)
                            {
                                Platform.runLater(() -> vetor[k].setText(Particao1[i].getText()));
                                TextoBotao("Copia o Resto",negrito,new Text(),vetor[k],"cima",40);
                                Destaca(Particao1[i],vetor[k]);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                Normaliza(Particao1[i],vetor[k]);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                k++; i++;
                            }
                            while (j < seqaux)
                            {
                                Platform.runLater(() -> vetor[k].setText(Particao2[j].getText()));
                                TextoBotao("Copia o Resto",negrito,new Text(),vetor[k],"cima",40);
                                Destaca(Particao2[j],vetor[k]);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                Normaliza(Particao2[j],vetor[k]);
                                try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
                                k++; j++;
                            }
                            seqaux = seqaux + aux;
                        }
                        RemoveTextoNaFusao();
                        seq = seq * 2;

                    }
                    InsereTextoFinal();
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

    public void InsereTextoNaParticao ()
    {
        Platform.runLater(() -> {
            texto1.setText("ESTRUTURA PRINCIPAL   -  PARA  -   PARTIÇÕES AUXILIARES");
            texto1.setFont(negrito);
            texto1.setLayoutX(420);
            texto1.setLayoutY(100);

            texto2.setText("COPIANDO DADOS ...");
            texto2.setFont(negrito);
            texto2.setLayoutX(600);
            texto2.setLayoutY(70);

            pane.getChildren().add(texto1);
            pane.getChildren().add(texto2);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public void RemoveTextoNaParticao ()
    {
        Platform.runLater(() -> {
            pane.getChildren().remove(texto1);
            pane.getChildren().remove(texto2);});
    }


    public void InsereTextoNaFusao ()
    {
        Platform.runLater(() -> {
            texto1.setText("PARTIÇÕES AUXILARES   -  PARA  -   ESTRUTURA PRINCIPAL");
            texto1.setFont(negrito);
            texto1.setLayoutX(420);
            texto1.setLayoutY(100);

            texto2.setText("FUSAO DOS DADOS ...");
            texto2.setFont(negrito);
            texto2.setLayoutX(608);
            texto2.setLayoutY(70);

            pane.getChildren().add(texto1);
            pane.getChildren().add(texto2);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public void RemoveTextoNaFusao ()
    {
        Platform.runLater(() -> {
            pane.getChildren().remove(texto1);
            pane.getChildren().remove(texto2);});
    }



    public void TextoBotao (String string, Font tipo,Text texto, Button botao, String orientacao, int ajuste)
    {
        if (orientacao.equals("cima"))
        {
            Platform.runLater(() -> {
                texto.setText(string);
                texto.setFont(tipo);
                texto.setLayoutX(botao.getLayoutX()-ajuste);
                texto.setLayoutY(botao.getLayoutY()-20);
                pane.getChildren().add(texto);
            });
            try {Thread.sleep(1200);} catch (InterruptedException e) {e.printStackTrace();}
        }
        else
        {
            Platform.runLater(() -> {
                texto.setText(string);
                texto.setFont(tipo);
                texto.setLayoutX(botao.getLayoutX()-ajuste);
                texto.setLayoutY(botao.getLayoutY()+65);
                pane.getChildren().add(texto);
            });
            try {Thread.sleep(1200);} catch (InterruptedException e) {e.printStackTrace();}
        }
        Platform.runLater(() -> pane.getChildren().remove(texto));
        try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}
    }


    public void InsereTextoApresentacao ()
    {
        Platform.runLater(() -> {
            texto1.setText("MERGE 1ª IMPLEMENTAÇÃO (Animação) - PESQUISA E ORDENAÇÃO");
            texto1.setFont(negrito);
            texto1.setLayoutX(400);
            texto1.setLayoutY(100);

            texto2.setText("BEM VINDO !");
            texto2.setFont(negrito);
            texto2.setLayoutX(635);
            texto2.setLayoutY(70);

            pane.getChildren().add(texto1);
            pane.getChildren().add(texto2);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }

    public void RemoveTextoApresentacao ()
    {
        Platform.runLater(() -> {
            pane.getChildren().remove(texto1);
            pane.getChildren().remove(texto2);});
    }


    public void InsereTextoFinal ()
    {
        Platform.runLater(() -> {
            texto1.setText("MERGE 1ª IMPLEMENTAÇÃO (Animação) - PESQUISA E ORDENAÇÃO");
            texto1.setFont(negrito);
            texto1.setLayoutX(400);
            texto1.setLayoutY(100);

            texto2.setText("ORDENAÇÃO TERMINADA !");
            texto2.setFont(negrito);
            texto2.setLayoutX(575);
            texto2.setLayoutY(70);

            pane.getChildren().add(texto1);
            pane.getChildren().add(texto2);
        });
        try {Thread.sleep(800);} catch (InterruptedException e) {e.printStackTrace();}
    }


    // MAIN



    public static void main(String[] args) {
        launch();
    }
}