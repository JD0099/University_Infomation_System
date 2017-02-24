/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 강의 정보에 대한 유효성 검사를 하기 위한 Entity 클래스
 *
 * @author Sungrae
 */
public class CourseEffectivenessAgent {

    DBConnectInfo dbConnectInfo;
    Connection con = null;
    String query = null;
    Statement stmt = null;

    ResultSet rs = null;
    int min = 0;
    int max = 0;
    int chkCredit = 0;
    boolean cIdMatch;

    Pattern cPattern = Pattern.compile("C[0-9]{3}$");
    ArrayList<String> checkOpenCourse = new ArrayList<>();
    ArrayList<String> deleteCourseCheck = new ArrayList<>();
    ArrayList<String> modifyCourseCheck = new ArrayList<>();

    public CourseEffectivenessAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    public boolean openCourseCheck(String cId) {
        checkOpenCourse.clear();
        try {
            query = "select prof_id from course where course_id = '" + cId + "' and prof_id is not null";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                checkOpenCourse.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            return false;
        }
        return checkOpenCourse.isEmpty();

    }

    public boolean infoTableRowCheck(int row) {
        return row != -1;
    }

    public boolean checkOpenCourseNum(String min_enroll, String max_enroll) {
        try {
            min = Integer.parseInt(min_enroll);
            max = Integer.parseInt(max_enroll);
            if (min > max) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException ex) {
            return false;
        }

    }

    public boolean checkDeleteCourse(ArrayList<String> dChkArray) {

        try {
            for (String i : dChkArray) {
                query = "select prof_id from univ.course where course_id = '" + i + "' and prof_id is not null";
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    deleteCourseCheck.add(rs.getString(1));
                }
            }
        } catch (SQLException ex) {
            return false;
        }
        if (deleteCourseCheck.isEmpty()) {
            deleteCourseCheck.clear();
            return true;
        } else {
            deleteCourseCheck.clear();
            return false;
        }
    }

    public boolean checkCreditInfo(String credit) {
        try {
            chkCredit = Integer.parseInt(credit);
            if (chkCredit == 0) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public boolean checkCourseId(String cId) {
        Matcher match = cPattern.matcher(cId.trim());
        return cIdMatch = match.matches();
    }

    public boolean checkModifyCourse(String cId) {
        try {
            query = "select prof_id from course where course_id = '" + cId + "' and prof_id is not null";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                modifyCourseCheck.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            return false;
        }
        if (modifyCourseCheck.isEmpty()) {
            modifyCourseCheck.clear();
            return true;
        } else {
            modifyCourseCheck.clear();
            return false;
        }
    }

    public boolean checkCourseName(String cname) {
        if (cname.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkCourseDesc(String desc) {
        if (desc.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
