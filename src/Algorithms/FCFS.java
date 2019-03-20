package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class FCFS implements AcessAlgorithm {
    ArrayList<Process> targetList;

    public FCFS(ArrayList<Process> targetList) {
        this.targetList = targetList;
    }

    @Override
    public double calculateAverageAccessTime() {
        double time=0;
        double totalWaitingTime=0;
        double waitingTime=0;
        targetList.sort(Comparator.comparingDouble(Process::getInputTime));
        for (Process p:targetList) {
            //Ustawienie zegara gdy pierwszy proces wchodzi w czasie innym niż 0.
            if(time<p.getInputTime()){ time=p.getInputTime(); }
            time+=p.getExecuteTime();
            waitingTime=time-(p.getInputTime()+p.getExecuteTime());
            totalWaitingTime+=waitingTime;
        }
        return totalWaitingTime/(double)targetList.size();
    }
}
