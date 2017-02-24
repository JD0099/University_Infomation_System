/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CourseInfoAgent;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *강좌 정보에 관련된 Control 클래스
 * @author Sungrae
 */
public class CourseInfoHandler {

    Logger logger = Logger.getLogger(CourseInfoHandler.class.getName());

    CourseInfoAgent cAgent = new CourseInfoAgent();

    public CourseInfoHandler() {
    }

    public boolean getCreateCourseInfo(String cId, String cname, String deptId, String credit, String desc) {
        return cAgent.createCourseInfoAgent(cId, cname, deptId, credit, desc);
    }

    public boolean getOpenCourseHandler(String cId, String pId, String min_enroll, String max_enroll) {
        return cAgent.openCourseAgent(cId, pId, min_enroll, max_enroll);
    }

    public boolean getModifyHandler(String cId, String cname, String deptId, String credit, String desc) {
        return cAgent.setModifyInfo(cId, cname, deptId, credit, desc);
    }
/**
 * 교수 정보를 찾았는지 확인하는 함수.
 * @param dName
 * @return 
 */
    public boolean getProfInfoState(String dName) {
        return cAgent.profInfoAgent(dName);
    }

    public boolean setBillDataInfo() {
        return cAgent.setBillInfoData();
    }

    public boolean getRequestBillInfo(ArrayList<String> requestArray) {
        if (cAgent.beforeRequestBill() == true && cAgent.requestBillSubSystem() == true) {
            return cAgent.BillRequestOk(requestArray);
        } else {
            return false;
        }
    }

    public ArrayList<String> getStuBillArray() {
        return cAgent.getStuBillInfoData();
    }

    public ArrayList<String> getProfNameInfoData() {
        return cAgent.getpNameInfoData();
    }

    public ArrayList<String> getProfIdInfoData() {
        return cAgent.getpIdInfoData();

    }

    public ArrayList<String> getShowInfoData() {
        return cAgent.getSetInfoData();
    }

    public boolean showState() {
        return cAgent.showCourseInfoAgent();
    }

    public boolean getDeleteCourseInfo(ArrayList<String> dArray) {
        return cAgent.deleteCourseInfo(dArray);
    }

    public boolean getRowValue(String cId) {
        return cAgent.showCourseDesc(cId);
    }

    public String getDesc() {
        return cAgent.getDescInfo();
    }

}
