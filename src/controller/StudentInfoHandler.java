/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.ApplicationAgent;

/**
 * 수강신청과 관련된 Control 클래스
 *
 * @author Sungrae
 */
public class StudentInfoHandler {

    ApplicationAgent aAgent = new ApplicationAgent();

    public StudentInfoHandler() {
    }

    public boolean setClassApplication(ArrayList<String> appliData) {
        return aAgent.classApplication(appliData);
    }

    public boolean setStuHackjum(String s_id, String credit) {
        return aAgent.applyHackjum(s_id, credit);
    }

    public boolean getScoreInfo(String s_id) {
        return aAgent.showScoreData(s_id);
    }

    public ArrayList<String> getScoreInfoData() {
        return aAgent.getScoreInfoData();
    }

    public boolean getShowInfo(String s_id) {
        return aAgent.showInfoData(s_id);
    }

    public ArrayList<String> getShowInfoData() {
        return aAgent.getInfoData();
    }

    public boolean getCreditSum(String s_id) {
        return aAgent.applyCourseCreditSum(s_id);
    }

    public String getCreditSumData() {
        return aAgent.getCreditSumData();
    }

}
