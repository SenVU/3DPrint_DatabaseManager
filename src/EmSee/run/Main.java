package EmSee.run;

import java.util.Scanner;

public class Main {
    public static SaveDataHandler saveDataHandler = new SaveDataHandler();
    public static Questions questions = new Questions();
    public static DatabaseHandler databaseHandler = new DatabaseHandler();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        SaveData saveData = initialSaveDataLoading();
        databaseHandler.loadDatabase(saveData);
        CommandHandler commandHandler = new CommandHandler();
        System.out.println("What command do you want to EmSee.run? Type \"?\" for a list of all commands");
        while(true) {
            commandHandler.RunCommand(scanner.nextLine());
        }
    }

    static SaveData initialSaveDataLoading() {
        SaveData saveData;
        if(!saveDataHandler.doesSaveFileExist()) {
            System.out.println("Savefile does not exist, creating now...");
            saveData = new SaveData();
            saveData.setDataBaseLocation(questions.string("Where is the database Located?"));
            saveDataHandler.WriteToFile(saveData);
        }
        else {
            saveData = saveDataHandler.ReadFromSaveFile();
        }


        if(!questions.YesOrNo("The database directory is \"" + saveData.getDataBaseLocation() + "\" is that correct?")) {
            saveData.setDataBaseLocation(questions.string("What is the new location?"));
            saveDataHandler.WriteToFile(saveData);
        }
        return saveData;
    }
}