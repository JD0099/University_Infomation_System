/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.AppliEffectivenessAgent;

/**
 * 학생 수강신청 부분 유효성을 확인하기 위한 Control 클래스
 * @author Sungrae
 */
public class AppliEffectivenessHandler {

    AppliEffectivenessAgent aEAgent = new AppliEffectivenessAgent();

    public AppliEffectivenessHandler() {
    }

    public boolean CourseTableRowCheck(int row) {
        return aEAgent.coursetableRowCheck(row);
    }

    public boolean checkCreditSum(String credit,ArrayList<String> chkSum) {
        return aEAgent.checkCreditSum(credit,chkSum);
    }

    public boolean checkMaxEnroll(String c_id) {
        return aEAgent.checkMaxEnroll(c_id);
    }
    
}
