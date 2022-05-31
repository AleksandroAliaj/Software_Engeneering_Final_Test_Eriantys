package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.stages.ActionStage;
import it.polimi.ingsw.view.stages.MageStageSocket;
import it.polimi.ingsw.view.stages.PlanningStage;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class MageController {
    private ArrayList<Mage> avaiableMages;
    private ClientMain client;
    private Stage stage;
    private Mage selectedmage;
    @FXML
    private ImageView mage1;
    @FXML
    private ImageView mage2;
    @FXML
    private ImageView mage3;
    @FXML
    private ImageView mage4;

    @FXML
    private Label result;

    public void setClient(ClientMain client) {
        this.client = client;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label textMage;
    @FXML
    public void clickMage1(){
        selectedmage = Mage.MAGE1;
        textMage.setText("You selected SUMMER");
    }
    @FXML
    public void clickMage2(){
        selectedmage = Mage.MAGE2;
        textMage.setText("You selected WINTER");
    }
    @FXML
    public void clickMage3(){
        selectedmage = Mage.MAGE3;
        textMage.setText("You selected SPRING");
    }
    @FXML
    public void clickMage4(){
        selectedmage = Mage.MAGE4;
        textMage.setText("You selected AUTUMN");
    }
    @FXML
    public void setDisabled1(){
        mage1.setDisable(true);
        mage1.setOpacity(0.5);
    }
    @FXML
    public void setDisabled2(){
        mage2.setDisable(true);
        mage2.setOpacity(0.5);
    }
    @FXML
    public void setDisabled3(){
        mage3.setDisable(true);
        mage3.setOpacity(0.5);
    }
    @FXML
    public void setDisabled4(){
        mage4.setDisable(true);
        mage4.setOpacity(0.5);
    }
    @FXML
    public void setEnabled1(){
        mage1.setDisable(false);
        mage1.setOpacity(1);
    }
    @FXML
    public void setEnabled2(){
        mage2.setDisable(false);
        mage2.setOpacity(1);
    }
    @FXML
    public void setEnabled3(){
        mage3.setDisable(false);
        mage3.setOpacity(1);
    }
    @FXML
    public void setEnabled4(){
        mage4.setDisable(false);
        mage4.setOpacity(1);
    }
    @FXML
    public void confirmMage() throws IOException {
        new PlanningStage();
    }

    @FXML
    public synchronized void confirmMageSocket() throws IOException {

        int mage = 0;

        switch (selectedmage) {
            case MAGE1:
                mage = 1;
                break;
            case MAGE2:
                mage = 2;
                break;
            case MAGE3:
                mage = 3;
                break;
            case MAGE4:
                mage = 4;
                break;
        }

        int finalMage = mage;
        Platform.runLater(() -> {


            client.getSendMessage().sendMageMessage(new MageMessage(finalMage));
            MessageInterface receivedMessage = client.getReceiveMessage().receiveMessage();

            System.out.println(receivedMessage.getCode());

            if (receivedMessage.getCode() == MessageType.MAGEERROR){
                result.setText("Error, try again");
                return;
            }

            try {
                new ActionStage(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.close();
        });
        /*
        client.getSendMessage().sendMageMessage(new MageMessage(mage));
        MessageInterface receivedMessage = client.getReceiveMessage().receiveMessage();


        if (receivedMessage.getCode() == MessageType.NOERROR){

            new ActionStage(client);
            stage.close();



            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {

                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{

                                    }finally{
                                        latch.countDown();
                                    }
                                }
                            });
                            latch.await();
                            //Keep with the background work
                            return null;
                        }
                    };
                }
            };
            //service.start();






        } else if (receivedMessage.getCode() == MessageType.MAGEERROR){
            result.setText("Error, try again");
        }

         */


    }
    public void setAvaiableMages(ArrayList<Mage> mages){
        this.avaiableMages = mages;
    }
}
