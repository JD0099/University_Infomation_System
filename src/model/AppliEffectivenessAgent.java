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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sungrae
 */
public class AppliEffectivenessAgent {

    private static final Logger logger = Logger.getLogger(ApplicationAgent.class.getName());
    DBConnectInfo dbConnectInfo;

    Connection con = null;
    String query = null;
    String addCreditQuery = null;
    Statement stmt = null;
    ResultSet rs = null;

    int checkCredit;
    String creditSumData;
    String checkEnroll;

    public AppliEffectivenessAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    /**
     * 최대로 신청할 수 있는 학점을 확인하는 함수.
     *
     * @param c_id (강좌번호)
     * @return true or false
     */
    public boolean checkMaxEnroll(String c_id) {
        try {
            query = "select count(*)<max_enroll from takes join course using(course_id) where course_id='" + c_id + "'";
            logger.log(Level.INFO, query);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                checkEnroll = rs.getString(1);
                logger.log(Level.INFO, checkEnroll);
            }
            if (checkEnroll.equals("0")) {
                return false;
            }else{
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }

    }

    /**
     * 신청한 학점을 확인하기 위해 사용하는 함수.
     *
     * @param credit 신청 학점
     * @param chkSum 신청 학점의 합을 계산하기 위한 배열
     * @return true or false
     */
    public boolean checkCreditSum(String credit, ArrayList<String> chkSum) {

        checkCredit = Integer.parseInt(credit);
        for (String i : chkSum) {
            checkCredit = checkCredit + Integer.parseInt(i);
        }
        logger.log(Level.INFO, Integer.toString(checkCredit));
        if (checkCredit > 18) {
            chkSum.clear();
            return false;
        } else {
            chkSum.clear();
            return true;
        }

    }

    /**
     * coursetable의 열을 선택하였는지를 확인하기 위한 함수.
     *
     * @param row
     * @return true or false
     */
    public boolean coursetableRowCheck(int row) {
        return row != -1;
    }

}
