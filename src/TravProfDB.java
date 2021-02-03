import java.io.*;
import java.util.*;

public class TravProfDB {
    String fileName;
    Hashtable<String, TravProf> travelers = new Hashtable<>();
    String[] keys;
    int index;


    public TravProfDB(String fileName){
        this.fileName = fileName;
    }

    public void insertNewProfile(TravProf profile){
        travelers.put(profile.getLastName(),profile);
    }

    public boolean deleteProfile(String ID, String lName){
        //This method first checks if the profile exists, then checks if the travel agent has access.
        //If the travel agent is not the one listed in the profile, they cannot access it.
        if (travelers.get(lName) != null) {
            if(travelers.get(lName).getTravAgentID().equals(ID)){
                travelers.remove(lName);
                return true;
            } else{
                System.out.println("Insufficient Access.");
                return false;
            }
        } else{
            return false;
        }
    }

    public TravProf findProfile(String ID, String lName){
        //This method first checks if the profile exists, then checks if the travel agent has access.
        //If the travel agent is not the one listed in the profile, they cannot access it.
        if(travelers.get(lName) != null){
            if(travelers.get(lName).getTravAgentID().equals(ID)){
                return travelers.get(lName);
            } else{
                System.out.println("Insufficient Access.");
                return null;
            }
        } else{
            return null;
        }
    }

    public TravProf findFirstProfile(){
        //This method first checks if travelers is empty or not. If not, it returns the first profile.
        if (travelers.size() == 0)
        {
            return null;
        }
        else{
            keys = travelers.keySet().toArray(new String[0]);
            index = 0;
            return travelers.get(keys[index]);
        }
    }

    public TravProf findNextProfile(){
        index += 1;
        if(index >= keys.length){
            return null;
        } else {
            return travelers.get(keys[index]);
        }
    }

    public void initializeDatabase(String fName) throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream(fName);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        this.travelers = (Hashtable<String, TravProf>) objectInputStream.readObject();

    }

    public void writeAllTravProf(String fName) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(travelers);
        outputStream.close();

    }
}
