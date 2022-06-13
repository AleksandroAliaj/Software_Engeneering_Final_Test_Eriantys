package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.communication.common.messages.UseCharacterMessage;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.view.stages.CharacterStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class CharacterInfoController {

    private final Image redStudent = new Image("Assets/Students/student_red.png");
    private final Image greenStudent = new Image("Assets/Students/student_green.png");
    private final Image blueStudent = new Image("Assets/Students/student_blue.png");
    private final Image yellowStudent = new Image("Assets/Students/student_yellow.png");
    private final Image pinkStudent = new Image("Assets/Students/student_pink.png");

    private Stage stage;

    private ClientMain clientMain;

    private ActionController actionController;

    private ToggleGroup toggleGroup;

    private ModelMessage model;
    private ArrayList<Node> itemsOnCard;
    private String message;
    private MessageType code;
    private int id;
    private int island;
    private int student;
    private int[] studentsFromEntrance;
    private int[] studentsOnCard;
    private ColorStudent colorStudent;
    private int[] studentsFromDinignRoom;
    private int choice;


    private final UseCharacterMessage characterMessage = new UseCharacterMessage();

    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane3;
    @FXML
    private AnchorPane pane5;
    @FXML
    private AnchorPane pane7;
    @FXML
    private AnchorPane pane9;
    @FXML
    private AnchorPane pane10;
    @FXML
    private AnchorPane pane11;
    @FXML
    private AnchorPane pane12;


    public void setActionController(ActionController actionController) {
        this.actionController = actionController;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public CharacterCard getCharacterByNum(int num) {
        int i = 0;
        for (CharacterCard card : model.getCharacterCards()) {
            if (card.getNum() == num) {
                choice = i;
                return card;
            }
            i++;
        }
        return null;

    }

    public void setClientMain(ClientMain clientMain) {
        this.clientMain = clientMain;
    }

    public void character1() {
        Platform.runLater(() -> {
            TextField islandText = (TextField) itemsOnCard.get(4);
            island = Integer.parseInt(islandText.getText());
            if (island > model.getArchipelago().size() || student==-1) {
                Label label = (Label) itemsOnCard.get(7);
                label.setOpacity(1);
            } else {
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter1Message(island, student, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }
        });

    }

    public void character3() {
        Platform.runLater(() -> {
            TextField islandText = (TextField) itemsOnCard.get(0);
            island = Integer.parseInt(islandText.getText());
            if (island > model.getArchipelago().size() || islandText.getText().equals("")) {
                Label label = (Label) itemsOnCard.get(2);
                label.setOpacity(1);
            } else {
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter3Message(island, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }
        });
    }

    public void clickOnStudent1() {
        student = 0;
        Label label = (Label) itemsOnCard.get(5);
        label.setText("Selected Student: 1");
    }

    public void clickOnStudent2() {
        student = 1;
        Label label = (Label) itemsOnCard.get(5);
        label.setText("Selected Student: 2");
    }

    public void clickOnStudent3() {
        student = 2;
        Label label = (Label) itemsOnCard.get(5);
        label.setText("Selected Student: 3");
    }

    public void clickOnStudent4() {
        student = 3;
        Label label = (Label) itemsOnCard.get(5);
        label.setText("Selected Student: 4");
    }


    public void character5() {
        Platform.runLater(() -> {
            TextField islandText = (TextField) itemsOnCard.get(0);
            island = Integer.parseInt(islandText.getText());
            if (island > model.getArchipelago().size() || islandText.getText().equals("")) {
                Label label = (Label) itemsOnCard.get(2);
                label.setOpacity(1);
            } else {
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter5Message(island, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }
        });
    }

    public void clickOn7Student1() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 0;
                itemsOnCard.get(0).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student2() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {

            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 1;
                itemsOnCard.get(1).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student3() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 2;
                itemsOnCard.get(2).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student4() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 3;
                itemsOnCard.get(3).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student5() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 4;
                itemsOnCard.get(4).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student6() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 5;
                itemsOnCard.get(5).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student7() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 6;
                itemsOnCard.get(6).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student8() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 7;
                itemsOnCard.get(7).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student9() {
        for (int i = 0; i < studentsFromEntrance.length; i++) {
            if (studentsFromEntrance[i] == -1) {
                studentsFromEntrance[i] = 8;
                itemsOnCard.get(8).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student10() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 0;
                itemsOnCard.get(9).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student11() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 1;
                itemsOnCard.get(10).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student12() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 2;
                itemsOnCard.get(11).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student13() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 3;
                itemsOnCard.get(12).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student14() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 4;
                itemsOnCard.get(13).setEffect(new Bloom());
                return;
            }
        }
    }

    public void clickOn7Student15() {
        for (int i = 0; i < studentsOnCard.length; i++) {
            if (studentsOnCard[i] == -1) {
                studentsOnCard[i] = 5;
                itemsOnCard.get(14).setEffect(new Bloom());
                return;
            }
        }
    }


    public void character7() {


        Platform.runLater(() -> {
            if (IntStream.of(studentsFromEntrance).anyMatch(x -> x == -1) || IntStream.of(studentsOnCard).anyMatch(x -> x == -1)) {

            } else {
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter7Message(studentsFromEntrance, studentsOnCard, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                stage.close();
            }


        });


    }

    public void character9() {

        Platform.runLater(() -> {
            boolean ok = false;

            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(0))) {
                colorStudent = ColorStudent.BLUE;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(1))) {
                colorStudent = ColorStudent.RED;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(2))) {
                colorStudent = ColorStudent.GREEN;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(3))) {
                colorStudent = ColorStudent.YELLOW;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(4))) {
                colorStudent = ColorStudent.PINK;
                ok = true;
            }

            if (ok){
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter9Message(colorStudent, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }

        });

    }

    public void character10() {
        clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter10Message(studentsFromEntrance, studentsFromDinignRoom, choice));
    }

    public void character11() {
        Platform.runLater(() -> {
            if (student != -1){
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter11Message(student, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }
        });
    }

    public void character12() {
        Platform.runLater(() -> {
            boolean ok = false;

            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(0))) {
                colorStudent = ColorStudent.BLUE;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(1))) {
                colorStudent = ColorStudent.RED;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(2))) {
                colorStudent = ColorStudent.GREEN;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(3))) {
                colorStudent = ColorStudent.YELLOW;
                ok = true;
            }
            if (toggleGroup.getSelectedToggle().equals(itemsOnCard.get(4))) {
                colorStudent = ColorStudent.PINK;
                ok = true;
            }

            if (ok){
                clientMain.getSendMessage().sendCharacterMessage(characterMessage.useCharacter12Message(colorStudent, choice));
                clientMain.receiveMessage();
                clientMain.getSendMessage().sendModelMessage(new ModelMessage());
                model = (ModelMessage) clientMain.receiveMessage();
                actionController.setModel(model);
                actionController.showmodel(clientMain);
                MessageInterface receivedMessage1 = clientMain.receiveMessage();
                stage.close();
            }

        });


    }

    public void setToggleGroup() {
        this.toggleGroup = new ToggleGroup();
        for (int i = 0; i < 5; i++) {
            RadioButton button = (RadioButton) itemsOnCard.get(i);
            button.setToggleGroup(toggleGroup);
        }

    }


    public void init(int num, int choice, ModelMessage model) {
        this.choice = choice;
        this.model = model;
        this.student = -1;
        this.island = -1;

        switch (num) {
            case 1:
                pane1.setOpacity(1);
                pane1.setDisable(false);
                itemsOnCard = new ArrayList<>(pane1.getChildren());
                CharacterCard card = getCharacterByNum(1);
                int i = 0;
                for (Student student : card.getStudents()) {
                    ImageView imageView = (ImageView) itemsOnCard.get(i);
                    switch (student.getColor()) {
                        case YELLOW -> imageView.setImage(yellowStudent);
                        case PINK -> imageView.setImage(pinkStudent);
                        case BLUE -> imageView.setImage(blueStudent);
                        case GREEN -> imageView.setImage(greenStudent);
                        case RED -> imageView.setImage(redStudent);
                    }
                    i++;
                }
                break;
            case 3:
                pane3.setOpacity(1);
                pane3.setDisable(false);
                itemsOnCard = new ArrayList<>(pane3.getChildren());
                break;
            case 5:
                pane5.setOpacity(1);
                pane5.setDisable(false);
                itemsOnCard = new ArrayList<>(pane5.getChildren());
                break;
            case 7:
                pane7.setOpacity(1);
                pane7.setDisable(false);


                studentsFromEntrance = new int[3];
                studentsOnCard = new int[3];
                Arrays.fill(studentsFromEntrance, -1);
                Arrays.stream(studentsFromEntrance).forEach(System.out::println);
                Arrays.fill(studentsOnCard, -1);

                itemsOnCard = new ArrayList<>(pane7.getChildren());
                CharacterCard card7 = getCharacterByNum(7);
                int j = 8;
                for (Student student : card7.getStudents()) {
                    ImageView imageView = (ImageView) itemsOnCard.get(j);
                    switch (student.getColor()) {
                        case YELLOW -> imageView.setImage(yellowStudent);
                        case PINK -> imageView.setImage(pinkStudent);
                        case BLUE -> imageView.setImage(blueStudent);
                        case GREEN -> imageView.setImage(greenStudent);
                        case RED -> imageView.setImage(redStudent);
                    }
                    j++;
                }
                j = 0;
                for (j = 0; j < 9; j++) {
                    ImageView imageView = (ImageView) itemsOnCard.get(j);
                    imageView.setOpacity(0);
                    imageView.setDisable(true);
                }
                j = 0;
                for (Student student : model.getSchoolBoard().getEntrance().getStudents()) {
                    ImageView imageView = (ImageView) itemsOnCard.get(j);
                    imageView.setDisable(false);
                    imageView.setOpacity(1);
                    switch (student.getColor()) {
                        case YELLOW -> imageView.setImage(yellowStudent);
                        case PINK -> imageView.setImage(pinkStudent);
                        case BLUE -> imageView.setImage(blueStudent);
                        case GREEN -> imageView.setImage(greenStudent);
                        case RED -> imageView.setImage(redStudent);
                    }
                    j++;
                }


                break;
            case 9:
                pane9.setOpacity(1);
                pane9.setDisable(false);
                itemsOnCard = new ArrayList<>(pane9.getChildren());
                setToggleGroup();
                break;
            case 10:
                pane10.setOpacity(1);
                pane10.setDisable(false);
                itemsOnCard = new ArrayList<>(pane10.getChildren());
                break;
            case 11:
                pane11.setOpacity(1);
                pane11.setDisable(false);
                itemsOnCard = new ArrayList<>(pane11.getChildren());
                break;
            case 12:
                pane12.setOpacity(1);
                pane12.setDisable(false);
                itemsOnCard = new ArrayList<>(pane12.getChildren());
                setToggleGroup();
                break;


        }
    }

}
