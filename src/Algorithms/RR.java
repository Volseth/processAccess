package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;


import java.util.ArrayList;
import java.util.Comparator;


public class RR implements AcessAlgorithm {
    private int quantum;
    private ArrayList<Process> targetList;

    public RR(ArrayList<Process> targetList,int quantum){
        this.quantum=quantum;
        this.targetList=targetList;
    }

    @Override
    public double calculateAverageAccessTime() {
        ArrayList<Process> executionList=new ArrayList<>();
        int clock=0;
        int totalWaitingTime=0;
        int remainingQuantumTime=this.quantum;
        targetList.sort(Comparator.comparing(Process::getInputTime));
        /*Pętla wykona się do momentu, gdy na liście wykonywanych będą jakieś procesy,
        lub na liście procesów oczekujących znajdują się jakiekolwiek procesy, które nie nadeszły.*/
        for(int i=0;i< targetList.size() || !executionList.isEmpty();clock++) {

            //Sprawdzam czy na liście procesów oczekujących znajduje się proces o odpowiednim czasie nadejścia.
            while(i < targetList.size() && targetList.get(i).getInputTime() == clock) {
                executionList.add(targetList.get(i));
                i++;
            }

            //Jeśli lista procesów do wykonania nie jest pusta.
            if (!executionList.isEmpty()) {
                Process actual = executionList.remove(0);
                actual.setRemainingTime(actual.getRemainingTime()-1);
                remainingQuantumTime--;
                /*Dla każdego z procesów z listy oczekujących dodaje czas oczekiwania 1,
                oprócz pierwszego który się w danym momencie wykonuje(metoda remove to gwarantuje)*/
                for(Process p :executionList) {
                    p.setWaitingTime(p.getWaitingTime()+1);
                }
                /*Jeśli proces zakończył swoje wykonanie przed upływem kwantu czasu dodaje jego czas oczekiwania
                do całkowitego czasu oczekiwania i usuwam z kolejki do wykonania.*/
                if (actual.getRemainingTime()<=0) {
                    totalWaitingTime += actual.getWaitingTime();
                    remainingQuantumTime=this.quantum;
                }
                //Jeśli kwant czasu nie upłynął, proces się nie zakończył to przywracam proces na początek kolejki.
                else if(remainingQuantumTime>0) {
                    executionList.add(0,actual);
                }
                //Jeśli upłynął kwant czasu dodaje proces na koniec kolejki.
                else {
                    //Sprawdzam czy przed dodaniem na koniec nie pojawił nowy proces.
                    while(i < targetList.size() && targetList.get(i).getInputTime() == clock+1) {
                        executionList.add(targetList.get(i));
                        i++;
                    }
                    executionList.add(actual);
                    remainingQuantumTime=this.quantum;
                }

            }
        }

        return (double)(totalWaitingTime)/targetList.size();
    }
}
