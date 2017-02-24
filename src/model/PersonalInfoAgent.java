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

/**
 * 로그인을 하기 위한 Entity 클래스.
 * @author Sungrae
 */
public class PersonalInfoAgent {

    DBConnectInfo dbConnectInfo;
    Connection con = null;
    String query = null;
    Statement stmt = null;
    ResultSet rs = null;
    String name;

    public PersonalInfoAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    public boolean searchStudentInfo(String stu, String pwd) {
        try {
            query = "SELECT stu_id,password,name FROM student where stu_id = '" + stu + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString(3);
                if (rs.getString(1).equals(stu)
                        && rs.getString(2).equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean searchProfInfo(String prof, String pwd) {
        try {
            query = "SELECT prof_id,password,name FROM professor where prof_id = '" + prof + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString(3);
                if (rs.getString(1).equals(prof)
                        && rs.getString(2).equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean searchSubInfo(String sub, String pwd) {
        try {
            query = "SELECT sub_id,password,name FROM subject_admin where sub_id = '" + sub + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString(3);
                if (rs.getString(1).equals(sub)
                        && rs.getString(2).equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean searchHakInfo(String hak, String pwd) {
        try {
            query = "SELECT hak_id,password,name FROM haksa_admin where hak_id = '" + hak + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                name = rs.getString(3);
                if (rs.getString(1).equals(hak)
                        && rs.getString(2).equals(pwd)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getNameInfo() {
        return name;
    }
}
