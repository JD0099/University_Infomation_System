/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.ProfInfoAgent;

/**
 *필요한 값들을 주고 받는 핸들러
 * @author 박지동
 */
public class ProfInfoHandler {

    ProfInfoAgent pAgent = new ProfInfoAgent();

    public ProfInfoHandler() {
    }

    public boolean setGradeGrant(ArrayList<String> sArray, String c_id, String grade, String grade_num) {
        System.out.println(sArray + c_id + grade + grade_num);

        return pAgent.gradeGrantAgent(sArray, c_id, grade, grade_num);
    }

    public boolean showAttendStuState(String c_id) {
        return pAgent.showAttendStuInfoAgent(c_id);
    }

    public boolean showStuState(String c_id) {
        return pAgent.showStuInfoAgent(c_id);
    }

    public boolean showCourseState(String p_id) {
        return pAgent.showCourseInfoAgent(p_id);
    }

    public ArrayList<String> getShowAttendStuInfoData() {
        return pAgent.getSetAttendStuInfoData();
    }

    public ArrayList<String> getShowStuInfoData() {
        return pAgent.getSetStuInfoData();
    }

    public ArrayList<String> getCourseInfoData() {
        return pAgent.getSetInfoData();
    }

}
