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
import java.util.regex.Pattern;
import provideTuitionBillsSystem.ProvideTuitionBillsSystem;

/**
 * 강의 정보 관리에 대한 Entity 클래스.
 * @author Sungrae
 */
public class CourseInfoAgent {

    DBConnectInfo dbConnectInfo;

    Connection con = null;
    String query = null;
    Statement stmt = null;

    ProvideTuitionBillsSystem system;
    ArrayList<String> setInfoData = new ArrayList<>();
    ArrayList<String> pNameInfoData = new ArrayList<>();
    ArrayList<String> pIdInfoData = new ArrayList<>();
    ArrayList<String> billInfoData = new ArrayList<>();
    ArrayList<String> needSysInfoData = new ArrayList<>();
    ArrayList<String> checkOpenCourse = new ArrayList<>();
    ArrayList<String> deleteCourseCheck = new ArrayList<>();
    ArrayList<String> modifyCourseCheck = new ArrayList<>();

    Pattern cPattern = Pattern.compile("C[0-9]{3}$");

    ResultSet rs = null;
    String desc;
    int min = 0;
    int max = 0;
    int chkCredit = 0;
    boolean cIdMatch;

    public CourseInfoAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    public boolean createCourseInfoAgent(String cId, String cname, String deptId, String credit, String desc) {
        try {
            query = "insert into course(course_id,title,credit,des,dept_id) "
                    + "values('" + cId + "','" + cname + "'," + credit + ",'" + desc + "'," + deptId + ")";
            stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
            System.out.println(query);

        } catch (SQLException ex) {
            return false;
        }

        return true;

    }

    public boolean showCourseInfoAgent() {
        setInfoData.clear();
        try {
            query = "select c.course_id,c.title,d.dept_name,c.credit,p.name,c.min_enroll,c.max_enroll "
                    + "from course c left outer join professor p on(c.prof_id=p.prof_id) "
                    + "left outer join department d on(c.dept_id=d.dept_id)";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                setInfoData.add(rs.getString(1));
                setInfoData.add(rs.getString(2));
                setInfoData.add(rs.getString(3));
                setInfoData.add(rs.getString(4));
                setInfoData.add(rs.getString(5));
                setInfoData.add(rs.getString(6));
                setInfoData.add(rs.getString(7));
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean showCourseDesc(String cId) {
        try {
            query = "SELECT des FROM course where course_id = '" + cId + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                desc = rs.getString(1);
            }

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean setModifyInfo(String cId, String title, String deptId, String credit, String desc) {
        try {
            query = "update course set title ='" + title + "',dept_id='" + deptId
                    + "',credit=" + credit + ",des='" + desc + "'" + " where course_id = '" + cId + "'";
            System.out.println(query);
            stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean profInfoAgent(String dName) {
        pIdInfoData.clear();
        pNameInfoData.clear();
        try {
            query = "select prof_id, name from professor join department using(dept_id) where dept_name='" + dName + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                pIdInfoData.add(rs.getString(1));
                pNameInfoData.add(rs.getString(2));
            }
            System.out.println(pNameInfoData);
            System.out.println(pIdInfoData);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean openCourseAgent(String cId, String pId, String min_enroll, String max_enroll) {
        try {
            query = "Update course set prof_id = '" + pId + "', min_enroll = " + min_enroll + ", max_enroll = " + max_enroll + " where course_id = '" + cId + "'";
            stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean deleteCourseInfo(ArrayList<String> dArray) {
        try {
            for (String i : dArray) {
                query = "DELETE FROM course WHERE course_id = '" + i + "'";
                System.out.println(query);
                stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean setBillInfoData() {
        billInfoData.clear();
        try {
            query = "select stu_id,name from student where bill = 0";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                billInfoData.add(rs.getString(1));
                billInfoData.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean beforeRequestBill() {
        needSysInfoData.clear();
        try {
            query = "select s.stu_id, s.name, s.bill,c.title from student s join takes t on(s.stu_id=t.stu_id) join course c on(t.course_id=c.course_id)";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                needSysInfoData.add(rs.getString(1));
                needSysInfoData.add(rs.getString(2));
                needSysInfoData.add(rs.getString(3));
                needSysInfoData.add(rs.getString(4));
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean requestBillSubSystem() {
        system = new ProvideTuitionBillsSystem(needSysInfoData);
        return system.performInfo() == true;
    }

    public boolean BillRequestOk(ArrayList<String> bArray) {
        try {
            for (String i : bArray) {
                query = "Update student set bill = 1 where stu_id = '" + i + "'";
                System.out.println(query);
                stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getStuBillInfoData() {
        return billInfoData;
    }

    public ArrayList<String> getpNameInfoData() {
        return pNameInfoData;
    }

    public ArrayList<String> getpIdInfoData() {
        return pIdInfoData;
    }

    public ArrayList<String> getSetInfoData() {
        return setInfoData;
    }

    public String getDescInfo() {
        return desc;
    }

}
