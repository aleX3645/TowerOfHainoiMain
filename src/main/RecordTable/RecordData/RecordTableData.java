package main.RecordTable.RecordData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.MainGame.Time.Time;
import java.io.*;

/**
 * Класс таблицы рекордов
 * */
public class RecordTableData {

    public RecordTableData(int difficulty){
        this.difficulty = difficulty;
        try(BufferedReader reader = new BufferedReader(new FileReader("recordTable" + this.difficulty + ".txt"))){

            String temp;
            while((temp = reader.readLine())!=null){
                String[] stringPlayer = temp.split("-");

                Data cell = new Data(stringPlayer[0],Integer.parseInt(stringPlayer[1]),new Time(stringPlayer[2]));
                table.add(cell);
            }
        }catch (FileNotFoundException ignored){ }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**Сложность*/
    private int difficulty;
    /**Лист для таблицы рекордов*/
    private ObservableList<Data> table = FXCollections.observableArrayList();

    /**
     * Добавляет новую ячейку
     * */
    public void addNewRecord(Data newCell){
        if(table.size() == 0){
            table.add(newCell);
            Save();

            return;
        }
        for(int i = 0; i < table.size(); i++){
            if(newCell.getMoves() < table.get(i).getMoves()){
                table.add(i,newCell);
                Save();

                return;

            }
            if(newCell.getMoves() == table.get(i).getMoves() &&
                    compare(newCell.getTime(),table.get(i).getTime())){
                table.add(i, newCell);
                Save();

                return;
            }
        }
        table.add(newCell);
        Save();
    }

    public int getPlace(Data data){
        if(table.size() == 0){
            return 1;
        }
        for(int i = 0; i < table.size(); i++){
            if(data.getMoves() < table.get(i).getMoves()){
                return i+1;
            }
            if(data.getMoves() == table.get(i).getMoves() &&
                    compare(data.getTime(),table.get(i).getTime())){
                return i+1;
            }
        }
        return table.size();
    }


    /**
     * Сохраняет таблицу рекордов
     * */
    private void Save(){
        try(FileWriter writer = new FileWriter("recordTable" + this.difficulty + ".txt",false)){
            writer.write(toString());
        }
        catch (IOException ioException){
            System.out.println("hehere");
            ioException.printStackTrace();
        }
    }

    /**
     * Сбрасывает таблицу рекордов
     * */
    public void Reset(){
        table = FXCollections.observableArrayList();
        Save();
    }

    /**
     * Сравнивает 2 времени, возвращает true, если первое больше второго
     * */
    private boolean compare(Time time1, Time time2){
        final boolean b = time1.getMinutes() < time2.getMinutes() || time1.getMinutes() == time2.getMinutes() && time1.getSeconds() < time2.getSeconds()
                || time1.getMinutes() == time2.getMinutes() && time1.getSeconds() == time2.getSeconds() && time1.getMseconds() < time2.getMseconds();
        return b;
    }

    /**
     * Возвращает лист для таблицы
     * */
    public ObservableList<Data> getList(){
        return table;
    }

    /**
     * Приводит таблицу к строке
     * */
    @Override
    public String toString(){
        StringBuilder stringTable = new StringBuilder();

        if(table.size() == 0){
            return stringTable.toString();
        }

        stringTable.append(table.get(0).getName()).append("-");
        stringTable.append(table.get(0).getMoves()).append("-");
        stringTable.append(table.get(0).getTime().toString());
        for(int i = 1; i < table.size(); i++){
            stringTable.append("\n");
            stringTable.append(table.get(i).getName()).append("-");
            stringTable.append(table.get(i).getMoves()).append("-");
            stringTable.append(table.get(i).getTime().toString());
        }

        return stringTable.toString();
    }


}



