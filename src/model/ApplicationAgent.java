package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 학생 정보에 대한 모든 정보들을 관리하는 Entity Class.
 *
 * @author user
 */
public class ApplicationAgent {

    private static final Logger logger = Logger.getLogger(ApplicationAgent.class.getName());
    DBConnectInfo dbConnectInfo;

    Connection con = null;
    String query = null;
    String addCreditQuery = null;
    Statement stmt = null;
    ResultSet rs = null;

    String creditSumData;
    ArrayList<String> infoData = new ArrayList<>();
    ArrayList<String> scoreInfoData = new ArrayList<>();

    public ApplicationAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.con = dbConnectInfo.getConnection();
    }

    /**
     * 테이블에 보여줄 정보를 찾아주는 함수.
     *
     * @param s_id 학생 아이디
     * @return true,false
     */
    public boolean showInfoData(String s_id) {
        try {
            query = "select course_id,title,dept_name,credit,name,min_enroll,max_enroll from (professor join department using(dept_id)) "
                    + "right outer join course using(prof_id) where name is not null and course_id not in (select course_id from takes where stu_id ='" + s_id + "')";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);
            logger.log(Level.INFO, query);
            while (rs.next()) {
                infoData.add(rs.getString(1));
                infoData.add(rs.getString(2));
                infoData.add(rs.getString(3));
                infoData.add(rs.getString(4));
                infoData.add(rs.getString(5));
                infoData.add(rs.getString(6));
                infoData.add(rs.getString(7));
            }

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * 수강 신청 정보를 Insert 해주는 함수.
     *
     * @param appliData
     * @return true,false
     */
    public boolean classApplication(ArrayList<String> appliData) {
        try {
            for (int i = 0; i < appliData.size(); i = i + 2) {

                query = "insert into takes(stu_id,course_id) "
                        + " values('" + appliData.get(i) + "','" + appliData.get(i + 1) + "'" + ")";
                logger.log(Level.INFO, query);
                stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean applyCourseCreditSum(String s_id) {
        try {
            query = "select sum(credit) from univ.course join univ.takes using(course_id) where stu_id = '" + s_id + "' having sum(credit)<=18";
            logger.log(Level.INFO, query);
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                creditSumData = rs.getString(1);
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean applyHackjum(String s_id, String hackjum) {
        try {
            query = "update student set hackjum = " + hackjum + " where stu_id = '" + s_id + "'";
            logger.log(Level.INFO, query);
            stmt = con.prepareStatement(query);
            stmt.executeUpdate(query);
            stmt.executeUpdate("commit");
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    /**
     * 자신의 성적을 보여주는 함수.
     *
     * @param s_id
     * @return true,false
     */
    public boolean showScoreData(String s_id) {
        try {
            logger.log(Level.INFO, s_id);
            query = "select course_id,title,dept_name,credit,name,grade,grade_num"
                    + " from ((course join department using(dept_id)) join professor using (prof_id)) join takes using(course_id) where stu_id ='" + s_id + "'";
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                scoreInfoData.add(rs.getString(1));
                scoreInfoData.add(rs.getString(2));
                scoreInfoData.add(rs.getString(3));
                scoreInfoData.add(rs.getString(4));
                scoreInfoData.add(rs.getString(5));
                scoreInfoData.add(rs.getString(6));
                scoreInfoData.add(rs.getString(7));
            }
            logger.log(Level.INFO, scoreInfoData.get(0));
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getInfoData() {
        return infoData;
    }

    public String getCreditSumData() {
        return creditSumData;
    }

    public ArrayList<String> getScoreInfoData() {
        return scoreInfoData;
    }

}
