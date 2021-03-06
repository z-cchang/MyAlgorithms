package Algorithms.Graph.Utils;

import Algorithms.Graph.Network.Node;
import Algorithms.Graph.Network.NodeList;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is aimed to save nodes with a signName as
 * a edgeList defined in Network.NodeList
 */
public class HNodeList extends NodeList {
    // target protein
    //----------keys-----------
    public String signName;
    protected int ID;
    //---------
    protected int score;

    public HNodeList(String signName){
        this.signName = signName;
        this.ID = -1;
        this.score = -1;
    }
    public HNodeList(){
        this.ID = -1;
        this.score = -1;
    }

    public int getNonZeroNumb(){
        int sum = 0;
        for (Node node : this) {
            if(node.getValue() != 0){
                sum ++;
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HNodeList nodes = (HNodeList) o;
        return signName.equals(nodes.signName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), signName, ID);
    }

   //---------------PUBLIC------------------

    public String getSignName() {
        return signName;
    }

    public int getID() {
        return ID;
    }

    public int getScore() {
        return score;
    }
    //-----------------SET-----------------

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
