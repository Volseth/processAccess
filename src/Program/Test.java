package Program;

import Algorithms.FCFS;
import Algorithms.RR;
import Algorithms.SJF;
import Algorithms.SRTF;
import Interfaces.AcessAlgorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    private static void fillCollectionWithExampleData(ArrayList<Process> processesQueue, int numberOfProcesses, int avgInputTime,  int avgExecuteTime){
        for(int i=0;i<numberOfProcesses;i++){
            processesQueue.add(new Process(avgExecuteTime, avgInputTime));
        }
    }
    private void start(){
        double accessTimeFCFS=0;
        double accessTimeSJF=0;
        double accessTimeSRTF=0;
        double accessTimeRR=0;
        double accessTimeRR2=0;

        Scanner scanner= new Scanner(System.in);
        System.out.println("Podaj ilość kolejek testowych:");
        int n= scanner.nextInt();
        System.out.println("Podaj ilość procesów w każdej kolejce testowej");
        int p= scanner.nextInt();
        System.out.println("Podaj maksymalny czas nadejścia procesu");
        int inputTime= scanner.nextInt();
        System.out.println("Podaj średni czas potrzebny na wykonanie procesu");
        int executeTime= scanner.nextInt();
        System.out.println("Podaj kwant czasu dla algorytmu rotacyjnego.");
        int quantum= scanner.nextInt();
        for(int i=0; i<n;i++){
            ArrayList<Process> processesQueueFCFS= new ArrayList<>();
            fillCollectionWithExampleData(processesQueueFCFS,p,executeTime,inputTime);
            ArrayList<Process> processesQueueSJF= new ArrayList<>(processesQueueFCFS);
            ArrayList<Process> processesQueueSRTF= new ArrayList<>(processesQueueFCFS);
            ArrayList<Process> processesQueueRR= new ArrayList<>(processesQueueFCFS);
            AcessAlgorithm accessAlgorithmFCFS= new FCFS(processesQueueFCFS);
            AcessAlgorithm accessAlgorithmSJF= new SJF(processesQueueSJF);
            AcessAlgorithm accessAlgorithmSRTF= new SRTF(processesQueueSRTF);
            AcessAlgorithm accessAlgorithmRR= new RR(processesQueueRR,quantum);
            accessTimeFCFS+=accessAlgorithmFCFS.calculateAverageAccessTime();
            accessTimeSJF+=accessAlgorithmSJF.calculateAverageAccessTime();
            for (Process process: processesQueueRR) {
                process.setWaitingTime(0);
                process.setRemainingTime(process.getExecuteTime());
            }
            accessTimeSRTF+=accessAlgorithmSRTF.calculateAverageAccessTime();
            for (Process process: processesQueueRR) {
                process.setWaitingTime(0);
                process.setRemainingTime(process.getExecuteTime());
            }
            accessTimeRR+=accessAlgorithmRR.calculateAverageAccessTime();

            Process.saveToFile(processesQueueFCFS,i+"plik.csv");

        }
        System.out.println("Średni czas oczekiwania dla algorytmu FCFS:"+ accessTimeFCFS/n);
        System.out.println("Średni czas oczekiwania dla algorytmu SJF:"+ accessTimeSJF/n);
        System.out.println("Średni czas oczekiwania dla algorytmu SRTF:"+ accessTimeSRTF/n);
        System.out.println("Średni czas oczekiwania dla algorytmu RR:"+ accessTimeRR/n);
    }


    public static void main(String[] args) {
        Test test= new Test();
        test.start();
    }
}
