package EmSee.run;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class DatabaseHandler {
    public DatabaseHandler(){}

    XSSFWorkbook workbook;

    public void openSheet(String name) {
        XSSFSheet sheet = workbook.getSheet(name);
        if(sheet==null) System.out.println("The Database sheet \""+name+"\" does not exist");
    }

    public void loadDatabase(SaveData saveData) {
        try {
            FileInputStream file = new FileInputStream(new File(saveData.getDataBaseLocation()));
            workbook = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            System.out.println("Database does not exist " + e);
        } catch (IOException e) {
            System.out.println("error on database loading " + e);
        }
    }

    public void saveDatabase(SaveData saveData) {
        try
        {
            FileOutputStream out = new FileOutputStream(saveData.getDataBaseLocation());
            workbook.write(out);
            out.close();
            System.out.println("Database successfully saved");
        }
        catch (Exception e)
        {
            System.out.println("error on database saving " + e);
        }
    }
}
