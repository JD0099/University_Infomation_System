/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provideTuitionBillsSystem;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stratergy 패턴으로 구현된 수강료 청구서 발급 시스템.
 * 
 * @since  2016.12.07
 * @author Sungrae
 * @version 3
 */
public class ProvideTuitionBillsSystem {

    private static Logger logger = Logger.getLogger(ProvideTuitionBillsSystem.class.getName());

    protected ProvideBehavior pBehavior;
    ArrayList<String> infoArray = new ArrayList<>();

    public ProvideTuitionBillsSystem() {

    }

    public ProvideTuitionBillsSystem(ArrayList<String> infoArray) {
        pBehavior = new ProvideBill();
        this.infoArray = infoArray;
    }

    public boolean performInfo() {
        for (String i : infoArray) {
            logger.log(Level.INFO, i);
        }
        return pBehavior.provideBill();

    }

}
