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

/**
 *교수가 하는 일들을 모두 관리하는 Entity Class
 * @author 박지동
 */
public class ProfInfoAgent {

    DBConnectInfo dbConnectInfo;
    Connection con = null;
    String query = null;
    Statement stmt = null;
    ResultSet rs = null;

    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> setInfoData = new ArrayList<>();
    ArrayList<String> setStuInfoData = new ArrayList<>();
    ArrayList<String> setAttendStuInfoData = new ArrayList<>();

    public ProfInfoAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    public boolean showCourseInfoAgent(String p_id) {
        try {
            query = "select course_id, title, credit, min_enroll, max_enroll from course where prof_id = '" + p_id + "' and min_enroll > 0 and max_enroll > 0";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                setInfoData.add(rs.getString(1));
                setInfoData.add(rs.getString(2));
                setInfoData.add(rs.getString(3));
                setInfoData.add(rs.getString(4));
                setInfoData.add(rs.getString(5));
            }

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public ArrayList<String> getSetInfoData() {
        return setInfoData;
    }

    public boolean showStuInfoAgent(String c_id) {
        try {
            query = "select stu_id, name, grade, grade_num from student join takes using(stu_id) where course_id = '" + c_id + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                setStuInfoData.add(rs.getString(1));
                setStuInfoData.add(rs.getString(2));
                setStuInfoData.add(rs.getString(3));
                setStuInfoData.add(rs.getString(4));
            }

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public ArrayList<String> getSetStuInfoData() {
        return setStuInfoData;
    }

    public boolean showAttendStuInfoAgent(String c_id) {
        try {
            query = "select stu_id, name from student join takes using(stu_id) where course_id = '" + c_id + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                setAttendStuInfoData.add(rs.getString(1));
                setAttendStuInfoData.add(rs.getString(2));
            }

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public ArrayList<String> getSetAttendStuInfoData() {
        return setAttendStuInfoData;
    }

    public boolean gradeGrantAgent(ArrayList<String> array, String c_id, String grade, String grade_num) {
        try {
            for (String i : array) {
                query = "update takes set grade = '" + grade + "', grade_num = '" + grade_num + "' where stu_id = '" + i + "' and course_id = '" + c_id + "'";
                System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
}
