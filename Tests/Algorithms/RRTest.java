package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RRTest {

    @Test
    void calculateAverageAccessTime() {
        ArrayList<Process> processArrayList = new ArrayList<>();
        AcessAlgorithm accessAlgoritm=new RR(processArrayList,6);
        Process p1 = new Process(0,10,0);
        Process p2 = new Process(4,4,0);
        Process p3 = new Process(16,20,0);
        Process p4 = new Process(20,2,0);
        Process p5 = new Process(50,8,0);
        Process p6 = new Process(78,35,0);

        processArrayList.add(p1);
        processArrayList.add(p2);
        processArrayList.add(p3);
        processArrayList.add(p4);
        processArrayList.add(p5);
        processArrayList.add(p6);
        assertEquals(1.66,accessAlgoritm.calculateAverageAccessTime(),0.02);
    }
}