package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;

import java.util.ArrayList;
import java.util.Comparator;

public class SRTF implements AcessAlgorithm {
    ArrayList<Process> targetList;

    public SRTF(ArrayList<Process> targetList) {
        this.targetList = targetList;
    }

    @Override
    public double calculateAverageAccessTime() {
        ArrayList<Process> executionList=new ArrayList<>();
        int clock=0;
        int totalWaitingTime=0;
        /*Pętla wykona się do momentu, gdy na liście wykonywanych będą jakieś procesy,
        lub na liście procesów oczekujących znajdują się jakiekolwiek procesy, które nie nadeszły.*/
        targetList.sort(Comparator.comparing(Process::getInputTime).thenComparing(Process::getExecuteTime));
        for(int i=0;i< targetList.size() || !executionList.isEmpty();clock++) {
            /*Sprawdzam czy na liście procesów oczekujących znajduje się proces o odpowiednim czasie nadejścia,
            dodajemy go do listy i listę sortujemy(obsługa wywłaszczenia).*/
            while(i < targetList.size() && targetList.get(i).getInputTime() == clock) {
                executionList.add(targetList.get(i));
                executionList.sort(Comparator.comparing(Process::getRemainingTime));
                i++;
            }
            //Jeśli lista procesów do wykonania nie jest pusta.
            if (!executionList.isEmpty()) {
                Process actual = executionList.remove(0);
                actual.setRemainingTime(actual.getRemainingTime()-1);

                //Dla każdego z procesów z listy oczekujących dodaje czas oczekiwania 1.
                for(Process p :executionList) {
                    p.setWaitingTime(p.getWaitingTime()+1);
                }

                //Jeśli proces zakończył swoje wykonanie dodaje jego czas oczekiwania do całkowitego czasu oczekiwania.
                if (actual.getRemainingTime() <= 0) {
                    totalWaitingTime += actual.getWaitingTime();
                }
                else {
                    executionList.add(0, actual);
                }
            }
        }
        return (double)totalWaitingTime/targetList.size();
}
}
