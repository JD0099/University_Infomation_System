/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yun
 */
public class UserInfoAgent implements ErrorMessage {

    DBConnectInfo dbConnectInfo;

    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Connection conn = null;

    private String Error_Message = "";

    public UserInfoAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.conn = dbConnectInfo.getConnection();
    }


    //userType을 받아 학생 또는 교수의 목록을 보여준다.
    public ArrayList<UserInfo> SelectUserAll(String userType) {

        ArrayList<UserInfo> userList = new ArrayList<>();
        try {
            //학생 또는 교수의 전체 목록을 보여줄 쿼리
            ResultSet rs = stmt.executeQuery("select * from " + userType);

            while (rs.next()) {
                UserInfo userinfo = new UserInfo(); // 1개의 레코드를 받을 벡터

                String id = rs.getString(1); //DB에서 id값 가지고 와서 id변수에 저장
                String name = rs.getString(3); //DB에서 이름값 가지고 와서 name변수에 저장
                int dept = rs.getInt(5); //DB에서 학과값 가지고 와서 dept변수에 저장
                String resnum = rs.getString(2); //DB에서 주민번호값 가지고 와서 resnum변수에 저장

                userinfo.setId(id);
                userinfo.setName(name);
                userinfo.setDept(dept);
                userinfo.setResnum(resnum);

                //전체 데이터를 저장하는 벡터에 userinfo(1명의 데이터 저장) 벡터 추가
                userList.add(userinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList; // 전체 데이터 저장하는 data 벡터 리턴
    }

    //사용자 정보 삽입
    public boolean UserInfoInsert(UserInfo uInfo) {
        System.out.println("insert Agent");
        try {
            if (uInfo.getPosition() == "student") { //학생정보 삽입
                pstmt = conn.prepareStatement("INSERT INTO " + uInfo.getPosition() + " VALUES (?, ?, ?, ?, ?, 0, false)");
            } else { //교수정보 삽입
                pstmt = conn.prepareStatement("INSERT INTO " + uInfo.getPosition() + " VALUES (?, ?, ?, ?, ?)");
            }

            pstmt.setString(1, uInfo.getId());
            pstmt.setString(2, uInfo.getResnum());
            pstmt.setString(3, uInfo.getName());
            pstmt.setString(4, uInfo.getPassword());
            pstmt.setString(5, "" + uInfo.getIntDept());
            pstmt.executeUpdate(); //DB에 반영

        } catch (Exception e) {
            //e.printStackTrace();
            Error_Message = e.getMessage();
            return false;

        }

        return true;
    }

    //사용자 정보 삭제
    public boolean UserInfoDelete(ArrayList<String> deleteUsers, String userType) {
        System.out.println("Delete Agent");
        for (int i = 0; i < deleteUsers.size(); i++) {
            try {
                if (userType == "student") {
                    pstmt = conn.prepareStatement("DELETE FROM " + userType + " WHERE (stu_id = ?)");
                } else {
                    pstmt = conn.prepareStatement("DELETE FROM " + userType + " WHERE (prof_id = ?)");
                }
                pstmt.setString(1, deleteUsers.get(i).toString());
                pstmt.executeUpdate(); //DB에 반영

            } catch (Exception e) {
                //e.printStackTrace();
                Error_Message = e.getMessage();
                if(Error_Message.contains("foreign key")){
                    Error_Message = "수강중인 학생은 삭제할 수 없습니다.";
                }
                return false;
            }
        }
        return true;
    }

    //사용자 정보 수정
    public boolean UserInfoUpdate(ArrayList<UserInfo> updateUsers, String userType) {
        System.out.println("Update Agent");
        for (int i = 0; i < updateUsers.size(); i++) {
            try {
                if (userType == "student") {
                    pstmt = conn.prepareStatement("UPDATE " + userType
                            + " SET name = ?, dept_id = ?, resident_id = ? "
                            + " WHERE (stu_id = ?)");
                } else {
                    pstmt = conn.prepareStatement("UPDATE " + userType
                            + " SET name = ?, dept_id = ?, resident_id = ? "
                            + " WHERE (prof_id = ?)");
                }
                pstmt.setString(1, updateUsers.get(i).getName());
                pstmt.setString(2, "" + updateUsers.get(i).getIntDept());
                pstmt.setString(3, updateUsers.get(i).getResnum());
                pstmt.setString(4, updateUsers.get(i).getId());
                pstmt.executeUpdate(); //DB에 반영

            } catch (Exception e) {
                //e.printStackTrace();
                Error_Message = e.getMessage();
                return false;
            }
        }
        return true;
    }

    //사용자 정보 조회
    public ArrayList<UserInfo> UserInfoSelect(UserInfo userInfo, String userType) {
        System.out.println("Select Agent");
        ArrayList<UserInfo> userList = new ArrayList<UserInfo>();

        //사용자 목록을 보여줄 쿼리
        try {
            ResultSet rs;
            String sql;

            if (userType == "student") {
                sql = "select * from " + userType + " WHERE stu_id like ? AND name like ?";
            } else {
                sql = "select * from " + userType + " WHERE prof_id like ? AND name like ?";
            }

            if (userInfo.getIntDept() != -1) {
                sql += " AND dept_id = " + userInfo.getIntDept();
            }

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + userInfo.getId() + "%");
            pstmt.setString(2, "%" + userInfo.getName() + "%");
            //pstmt.setString(3,userInfo.getDept());

            System.out.println("_ : " + userInfo.getIntDept());

            System.out.println("sql : " + sql);//////////////

            rs = pstmt.executeQuery();

            while (rs.next()) {
                UserInfo userinfo = new UserInfo(); // 1개의 레코드를 받을 벡터

                String id = rs.getString(1); //DB에서 id값 가지고 와서 id변수에 저장
                String name = rs.getString(3); //DB에서 이름값 가지고 와서 name변수에 저장
                int dept = rs.getInt(5); //DB에서 학과값 가지고 와서 dept변수에 저장
                String resnum = rs.getString(2); //DB에서 주민번호값 가지고 와서 resnum변수에 저장

                userinfo.setId(id);
                userinfo.setName(name);
                userinfo.setDept(dept);
                userinfo.setResnum(resnum);

                //전체 데이터를 저장하는 벡터에 userinfo(1명의 데이터 저장) 벡터 추가
                userList.add(userinfo);

                System.out.println(id + " " + name + " " + dept + " " + resnum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList; // 전체 데이터 저장하는 data 벡터 리턴   
    }

    @Override
    public String getError_Message() {
        return this.Error_Message;
    }

}
