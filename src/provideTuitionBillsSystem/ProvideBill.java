/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provideTuitionBillsSystem;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 청구서 발급이라는 행위를 표현하는 부분.
 * 
 * 랜덤 함수를 이용하여 청구서를 랜덤하게 보내준다.
 * @since  2016.12.07
 * @author Sungrae
 * @version 3
 */
public class ProvideBill implements ProvideBehavior {

    private static Logger logger = Logger.getLogger(ProvideBill.class.getName());
    Random random = new Random();

    @Override
    public boolean provideBill() {
        logger.log(Level.INFO, "청구서 발급 담당");
        return random.nextBoolean();
    }

}
