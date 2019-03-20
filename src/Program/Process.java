package Program;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Process{
    private int inputTime;
    private int executeTime;
    private int waitingTime;
    private int remainingTime;

    public Process(int avgInputTime, int avgExecuteTime){
        Random r= new Random();
        this.inputTime=(int)(r.nextDouble()*avgInputTime)+1;
        this.executeTime=(int)(r.nextGaussian()*50+avgExecuteTime)+1;
        while(this.executeTime<=0){
          this.executeTime=(int)(r.nextGaussian()*50+avgExecuteTime)+1;
        }
        this.remainingTime=executeTime;
        this.waitingTime=0;
    }
    public Process(){
        this.inputTime=0;
        this.executeTime=5;
    }
    public Process(int inputTime, int executeTime,int remainingTime){
        this.inputTime=inputTime;
        this.executeTime=executeTime;
        this.remainingTime=executeTime;
    }


    public int getInputTime() {
        return inputTime;
    }

    public int getExecuteTime() {
        return executeTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setInputTime(int inputTime) {
        this.inputTime = inputTime;
    }

    public void setExecuteTime(int executeTime) {
        this.executeTime = executeTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        return inputTime+";"+executeTime;
    }
    public static void saveToFile(ArrayList<Process> processesList, String filepath){
        try(BufferedWriter bufferedWriter= new BufferedWriter(new FileWriter(new File(filepath)))){
            Iterator it=processesList.iterator();
            bufferedWriter.write("Czas wejścia;Czas wykonania");
            bufferedWriter.newLine();
            while(it.hasNext()){
                bufferedWriter.write(it.next().toString());
                bufferedWriter.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}

