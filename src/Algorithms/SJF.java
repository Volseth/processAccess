package Algorithms;

import Interfaces.AcessAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;

import Program.Process;


public class SJF  implements AcessAlgorithm {
    private ArrayList<Process> targetList;
    public SJF(ArrayList<Process> targetList){
        this.targetList=targetList;
    }
    @Override
    public double calculateAverageAccessTime() {
        ArrayList<Process> executionList=new ArrayList<>();
        double time=0;
        double totalWaitingTime=0;
        double size=targetList.size();
        targetList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getExecuteTime));
        time+=targetList.get(0).getInputTime();

        while(!targetList.isEmpty() || !executionList.isEmpty()) {
            //Dodajemy procesy które nadchodzą do listy, pomijamy skoki o 1, tylko skaczemy o cały proces procesora
            for (int i = 0; i < targetList.size(); i++) {
                if (targetList.get(i).getInputTime() <= time) {
                    executionList.add(targetList.remove(i));
                    i--;
                }
                //Sortujemy listę w przypadku, gdy zdarzy się sytuacja, że podczas wykonania długiego procesy w tym czasie dojdzie kilka krótkich o różnym czasie
                targetList.sort(Comparator.comparing(Process::getExecuteTime));
                //Jeśli lista nie jest pusta
                if (!executionList.isEmpty())
                {
                    //Wyliczamy czas procesora dla zakończenia procesu
                    if (time <= executionList.get(0).getInputTime()) {
                        time = (executionList.get(0).getInputTime() + executionList.get(0).getExecuteTime());
                    }
                    //Doliczamy czas oczekiwania do całkowitego czasu i usuwamy proces z listy.
                    else {
                        totalWaitingTime += time - executionList.get(0).getInputTime();
                        time+=executionList.get(0).getExecuteTime();
                    }
                    executionList.remove(0);

                }
                else {
                    time=targetList.get(0).getInputTime();
                }
            }
        }

        return totalWaitingTime/size;
    }
}
