package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.pieces.MotherNature;
import it.polimi.ingsw.model.pieces.NoEntryTile;
import it.polimi.ingsw.model.pieces.Professor;

import java.util.ArrayList;
import java.util.Collection;

public class GameComponents {
    private ArrayList<IslandCard> archipelago;
    private MotherNature motherNature;
    private ArrayList<SchoolBoard> SchoolBoards;
    private Bag bag;
    private Collection<CloudCard> cloudCards;
    private ArrayList<Professor> professorCollection;
    private CoinReserve coins;
    private Collection<NoEntryTile> prohibitionCards;
    private CharacterDeck specialDeck;

    // Start of Getters, Setters, Constructor

    //constructor pro
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature motherNature, ArrayList<SchoolBoard> schoolBoards, Bag bag, Collection<CloudCard> cloudCards, ArrayList<Professor> professorCollection, CoinReserve coins, Collection<NoEntryTile> prohibitionCards, CharacterDeck specialDeck) {
        this.archipelago = archipelago;
        this.motherNature = motherNature;
        SchoolBoards = schoolBoards;
        this.bag = bag;
        this.cloudCards = cloudCards;
        this.professorCollection = professorCollection;
        this.coins = coins;
        this.prohibitionCards = prohibitionCards;
        this.specialDeck = specialDeck;
    }

    //normal constructor
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature motherNature, ArrayList<SchoolBoard> schoolBoards, Bag bag, Collection<CloudCard> cloudCards, ArrayList<Professor> professorCollection) {
        this.archipelago = archipelago;
        this.motherNature = motherNature;
        SchoolBoards = schoolBoards;
        this.bag = bag;
        this.cloudCards = cloudCards;
        this.professorCollection = professorCollection;
    }

    public CoinReserve getCoins() {
        return coins;
    }

    public Collection<NoEntryTile> getProhibitionCards() {
        return prohibitionCards;
    }

    public void setProhibitionCards(Collection<NoEntryTile> prohibitionCards) {
        this.prohibitionCards = prohibitionCards;
    }

    public CharacterDeck getSpecialDeck() {
        return specialDeck;
    }

    public void setSpecialDeck(CharacterDeck specialDeck) {
        this.specialDeck = specialDeck;
    }

    public ArrayList<IslandCard> getArchipelago() {

        return archipelago;
    }

    public void setArchipelago(ArrayList<IslandCard> archipelago) {

        this.archipelago = archipelago;
    }

    public MotherNature getMotherNature() {
        return motherNature;
    }

    public void setMotherNature(MotherNature motherNature) {

        this.motherNature = motherNature;
    }

    public ArrayList<SchoolBoard> getSchoolBoards() {

        return SchoolBoards;
    }

    public void setSchoolBoards(ArrayList<SchoolBoard> schoolBoards) {

        SchoolBoards = schoolBoards;
    }

    public it.polimi.ingsw.model.board.Bag getBag() {

        return bag;
    }

    public void setBag(it.polimi.ingsw.model.board.Bag bag) {

        this.bag = bag;
    }

    public Collection<CloudCard> getCloudCards() {

        return cloudCards;
    }

    public void setCloudCards(Collection<CloudCard> cloudCards) {

        this.cloudCards = cloudCards;
    }

    public ArrayList<Professor> getProfessorCollection() {
        return professorCollection;
    }

    public void setProfessorCollection(ArrayList<Professor> professorCollection) {
        this.professorCollection = professorCollection;
    }

    // End of Getters, Setters, Constructor

    public void printClouds(){
        int j = 1;
        System.out.println("Cloud cards: ");
        for(CloudCard cloudCard : this.cloudCards){
            System.out.println("Cloud card " + j + " students: ");
            for(int i = 0; i < cloudCard.getStudents().size(); i++){
                System.out.println("Student " + i + " color: " + cloudCard.getStudents().get(i));
            }
        }
    }

    public void printArchipelago(){
        int i = 0;
        System.out.println("Archipelago: ");
        for(IslandCard islandCard : this.archipelago){
            System.out.println("Island: " + i + "\tTower : " + this.getArchipelago().get(i).getTower().getColor());
            System.out.println("Students:");
            for(int j = 0; j < islandCard.getStudents().size(); j++){
                System.out.println("Student " + j + "color : " + islandCard.getStudents().get(j));
            }
            System.out.println("Merged with: ");
            for(int j = 0; j < islandCard.getMergedWith().size(); i++){
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for(int k = 0; k < tempIslandCard.getStudents().size(); k++){
                    System.out.println("Student " + k + "color : " + islandCard.getStudents().get(k));
                }
            }
            i++;
        }
    }
}
