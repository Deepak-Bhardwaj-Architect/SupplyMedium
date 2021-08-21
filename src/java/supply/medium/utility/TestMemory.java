/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.utility;

/**
 *
 * @author LenovoB560
 */
public class TestMemory {

    public static void test(String location) {
//        showReport(location);
    }
    
//    public static void main(String args[]){
//        showReport("inside");
//        try {
//            Process proc;
//            proc = Runtime.getRuntime().exec("cmd.exe /c java -Xms256m -Xmx1024m TestMemory /n");
//        } catch (Exception e) {
//            System.out.println("something went wrong");
//        }
//        showReport("inside");
//    }

    public static void showReport(String location) {

        int mb = 1024 * 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] at :: " + location + " #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }
}
