/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.utility;

/**
 *
 * @author Intel8GB
 */
public class ErrorMaster {

    public static void insert(String error) {
//        clearConsole();
        System.out.println(error);
    }

//    public static void clearConsole() {
//        try {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                Runtime.getRuntime().exec("clear");
//            }
//        } catch (Exception ex) {
//            insert("Exception at clearConsole in ErrorMaster : "+ex.getMessage());
//        }
//    }

}