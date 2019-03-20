package Algorithms;

import Interfaces.AcessAlgorithm;
import Program.Process;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SRTFTest {

    @Test
    void calculateAverageAccessTime() {
        ArrayList<Process> processArrayList = new ArrayList<>();
        AcessAlgorithm accessAlgoritm=new SRTF(processArrayList);
        Process p1 = new Process(0,6,0);
        Process p2 = new Process(2,2,0);
        Process p3 = new Process(4,3,0);
        Process p4 = new Process(10,15,0);
        Process p5 = new Process(15,8,0);
        processArrayList.add(p1);
        processArrayList.add(p2);
        processArrayList.add(p3);
        processArrayList.add(p4);
        processArrayList.add(p5);
        assertEquals(2.8,accessAlgoritm.calculateAverageAccessTime(),0);
    }
}