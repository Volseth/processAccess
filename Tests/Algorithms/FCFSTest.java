package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FCFSTest {

    @Test
    void calculateAverageAccessTime() {
        ArrayList<Process> processArrayList = new ArrayList<>();
        AcessAlgorithm accessAlgoritm=new FCFS(processArrayList);
        Process p1 = new Process(1,100,0);
        Process p2 = new Process(1,5,0);
        Process p3 = new Process(1,2,0);
        Process p4 = new Process(1,7,0);
        Process p5 = new Process(1,9,0);

        processArrayList.add(p1);
        processArrayList.add(p2);
        processArrayList.add(p3);
        processArrayList.add(p4);
        processArrayList.add(p5);
        assertEquals(85.2,accessAlgoritm.calculateAverageAccessTime(),0.1);
    }
}