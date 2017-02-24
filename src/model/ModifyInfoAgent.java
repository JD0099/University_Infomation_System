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
 * 비밀번호 수정에 관련된 Entity 클래스.
 * @author Sungrae
 */
public class ModifyInfoAgent {

    DBConnectInfo dbConnectInfo;

    Connection con = null;
    String query = null;
    String pwdChangeQuery = null;
    Statement stmt = null;
    ResultSet rs;

    public ModifyInfoAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    public boolean stuModifyInfo(String id, String curPwd, String newPwd, String newPwd1) {
        try {
            query = "SELECT password FROM student where stu_id = '" + id + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                //rs.getString(1) 한 열 읽기
                if (rs.getString(1).equals(curPwd)) {
                    if (newPwd.equals(newPwd1)) {
                        System.out.println(id);
                        System.out.println(newPwd);
                        System.out.println(curPwd);

                        pwdChangeQuery = "UPDATE student SET password='" + newPwd + "'WHERE stu_id='" + id + "'";
                        stmt = con.prepareStatement(pwdChangeQuery);
                        int cnt = stmt.executeUpdate(pwdChangeQuery);
                        stmt.executeUpdate("commit");

                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean profModifyInfo(String id, String curPwd, String newPwd, String newPwd1) {
        try {
            query = "SELECT password FROM professor where prof_id = '" + id + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (rs.getString(1).equals(curPwd)) {
                    if (newPwd.equals(newPwd1)) {
                        pwdChangeQuery = "UPDATE professor SET password='" + newPwd + "'WHERE prof_id='" + id + "'";
                        stmt = con.prepareStatement(pwdChangeQuery);
                        int cnt = stmt.executeUpdate(pwdChangeQuery);
                        stmt.executeUpdate("commit");
                        System.out.println(cnt);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean hakModifyInfo(String id, String curPwd, String newPwd, String newPwd1) {
        try {
            query = "SELECT password FROM haksa_admin where hak_id = '" + id + "'";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                if (rs.getString(1).equals(curPwd)) {
                    if (newPwd.equals(newPwd1)) {
                        pwdChangeQuery = "UPDATE haksa_admin SET password='" + newPwd + "'WHERE hak_id='" + id + "'";
                        stmt = con.prepareStatement(pwdChangeQuery);
                        int cnt = stmt.executeUpdate(pwdChangeQuery);
                        stmt.executeUpdate("commit");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    public boolean subModifyInfo(String id, String curPwd, String newPwd, String newPwd1) {
        try {
            query = "SELECT password FROM subject_admin where sub_id = '" + id + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (rs.getString(1).equals(curPwd)) {
                    if (newPwd.equals(newPwd1)) {
                        pwdChangeQuery = "UPDATE subject_admin SET password='" + newPwd + "'WHERE sub_id='" + id + "'";
                        stmt = con.prepareStatement(pwdChangeQuery);
                        int cnt = stmt.executeUpdate(pwdChangeQuery);
                        stmt.executeUpdate("commit");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

}
