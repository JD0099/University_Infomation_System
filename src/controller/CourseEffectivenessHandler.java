/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CourseEffectivenessAgent;
import java.util.ArrayList;

/**
 * 유효성 검사를 위한 핸들러
 *
 * @author Sungrae
 */
public class CourseEffectivenessHandler {

    CourseEffectivenessAgent cEAgent = new CourseEffectivenessAgent();

    public CourseEffectivenessHandler() {
    }

    public boolean infoTableRowCheck(int row) {
        return cEAgent.infoTableRowCheck(row);
    }

    public boolean checkOpenCourse(String cId) {
        return cEAgent.openCourseCheck(cId);
    }

    public boolean checkOpenCourseNum(String min_enroll, String max_enroll) {
        return cEAgent.checkOpenCourseNum(min_enroll, max_enroll);
    }

    public boolean checkDeleteCourse(ArrayList<String> dChkArray) {
        return cEAgent.checkDeleteCourse(dChkArray);
    }

    public boolean checkCourseId(String cId) {
        return cEAgent.checkCourseId(cId);
    }

    public boolean checkModifyCourse(String cId) {
        return cEAgent.checkModifyCourse(cId);
    }

    public boolean checkCredit(String credit) {
        return cEAgent.checkCreditInfo(credit);
    }

    public boolean checkCourseName(String cname) {
        return cEAgent.checkCourseName(cname);
    }

    public boolean checkCourseDesc(String desc) {
        return cEAgent.checkCourseDesc(desc);
    }
}
