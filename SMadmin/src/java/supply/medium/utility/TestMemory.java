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
//            ErrorMaster.insert("something went wrong");
//        }
//        showReport("inside");
//    }

    public static void showReport(String location) {

        int mb = 1024 * 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        ErrorMaster.insert("##### Heap utilization statistics [MB] at :: " + location + " #####");

        //Print used memory
        ErrorMaster.insert("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        ErrorMaster.insert("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        ErrorMaster.insert("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        ErrorMaster.insert("Max Memory:" + runtime.maxMemory() / mb);
    }
}
