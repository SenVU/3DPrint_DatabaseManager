package EmSee.run;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHandler {
    public DatabaseHandler(){}

    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public void addSale(){
        SaveData saveData=Main.saveDataHandler.SilentReadData();
        if (workbook==null) loadDatabase();
        if (openSheet("sales")) {
            Cell nextEmptyID = null;
            int i = 0;
            nextEmptyID = sheet.getRow(0).getCell(0);
            while (!(nextEmptyID==null)) {
                i++;
                nextEmptyID = sheet.getRow(i).getCell(0);
            }
            nextEmptyID= sheet.getRow(i).createCell(0);
            nextEmptyID.setCellValue(i);
            Cell dateCell = sheet.getRow(i).createCell(1);
            dateCell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            updateFormulas();

            openSheet("sales");
            while (true) {
                Cell materialCodeCell = sheet.getRow(i).createCell(2);
                materialCodeCell.setCellValue(Main.questions.integer("What is the material code?"));
                updateFormulas();
                openSheet("sales");

                String materialName = sheet.getRow(i).getCell(3).getStringCellValue()+"-"+sheet.getRow(i).getCell(4).getStringCellValue()+"-"+sheet.getRow(i).getCell(5).getStringCellValue()+"-"+sheet.getRow(i).getCell(6).getStringCellValue();
                if (Main.questions.YesOrNo("Is this material correct? "+materialName)) break;
            }

            Cell nameCell = sheet.getRow(i).createCell(7);
            nameCell.setCellValue(Main.questions.string("What is the client name?"));
            saveDatabase(saveData);
        }
    }

    boolean openSheet(String name) {
        sheet = workbook.getSheet(name);
        if(sheet==null) System.out.println("The Database sheet \""+name+"\" does not exist");
        return !(sheet==null);
    }

    public void loadDatabase() {
        try {
            FileInputStream file = new FileInputStream(Main.saveDataHandler.SilentReadData().getDataBaseLocation());
            workbook = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            System.out.println("Database does not exist " + e);
        } catch (IOException e) {
            System.out.println("error on database loading " + e);
        }
    }

    public void saveDatabase(SaveData saveData) {
        updateFormulas();
        try
        {
            FileOutputStream out = new FileOutputStream(saveData.getDataBaseLocation());
            workbook.write(out);
            out.close();
            loadDatabase();
        }
        catch (Exception e) {
            System.out.println("error on database saving " + e);
        }
    }

    public void updateFormulas() {
        try {
            XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
        } catch (Exception e) {
            System.out.println("error on database formula updating " + e);
        }

    }
}
