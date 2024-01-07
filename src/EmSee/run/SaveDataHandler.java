package EmSee.run;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveDataHandler {
    String saveFileLocation = System.getProperty("user.dir")+"\\EmSee.Documents\\3DDatabaseManagerSaveFiles.txt";
    public static final Gson gson = new Gson();

    public SaveDataHandler() {
    }

    public boolean doesSaveFileExist() {
        File saveFile= new File(saveFileLocation);
        return saveFile.exists();
    }
    public void WriteToFile(SaveData saveData){
        String myData = gson.toJson(saveData);
        File saveFile= new File(saveFileLocation);
        if(!saveFile.exists()) {
            try {
                File directory = new File(saveFile.getParent());
                if(!directory.exists()) {
                    directory.mkdirs();
                }
                saveFile.createNewFile();
                System.out.println("EmSee.run.SaveData created: " + saveFileLocation);
            } catch (IOException e) {
                System.out.println("Exception Occurred: " + e);
            }
        } /*else {
            try {
                saveFile.delete();
            } catch (IOException e) {
                System.out.println("Exception Occurred: " + e);
            }
        }*/
        try {
            FileWriter saveWriter;
            saveWriter = new FileWriter(saveFile.getAbsoluteFile(), false);

            BufferedWriter bufferWriter = new BufferedWriter(saveWriter);
            bufferWriter.write(myData);
            bufferWriter.close();

            System.out.println("EmSee.run.SaveData saved\n");
        } catch (IOException e) {

            System.out.println("Got an error while saving data to file " + e);
        }
    }

    public SaveData ReadFromSaveFile() {
        SaveData toReturn = null;
        File saveFile = new File(saveFileLocation);
        if (!saveFile.exists())
            System.out.println("Tried to load " + saveFileLocation + " but it doesn't exist");
        else {
            InputStreamReader isReader;
            try {
                System.out.println("Reading EmSee.run.SaveData");
                isReader = new InputStreamReader(new FileInputStream(saveFile), StandardCharsets.UTF_8);

                JsonReader myReader = new JsonReader(isReader);
                SaveData saveData = gson.fromJson(myReader, SaveData.class);
                toReturn = saveData;
                System.out.println("DataBase Location: " + saveData.getDataBaseLocation());

            } catch (Exception e) {
                System.out.println("error load cache from file " + e);
            }
            System.out.println("\nEmSee.run.SaveData loaded successfully from file");
        }
        return toReturn;
    }
}