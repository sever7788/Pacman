package pacman;

import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;

public class Replay {
    public int count = 0;
    public ArrayList<ObjectData> screens = new ArrayList<>();
    Replay(){}
    public void addReplayData(double x, double y){
        screens.add(new ObjectData(x, y));
    }
    public void addReplayData(double x, double y, int direction){
        screens.add(new ObjectData(x, y, direction));
    }

    public void outputFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("rep_data.bin",false))) {
            ObjectData objectData = null;
            oos.writeObject(screens);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public void inputFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("rep_data.bin"))) {
            screens=((ArrayList<ObjectData>)ois.readObject());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
