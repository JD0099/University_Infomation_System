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
public class DeptInfoGetterAgent {

    DBConnectInfo dbConnectInfo;

    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private Connection conn = null;

    public DeptInfoGetterAgent() {
        dbConnectInfo = DBConnectInfo.getInstance();
        this.stmt = dbConnectInfo.getStatement();
        this.conn = dbConnectInfo.getConnection();
    }

    public ArrayList<String> getDeptList() {
        ArrayList<String> deptList = new ArrayList<String>();
        deptList.add("학과를 선택하세요.");

        try {
            //학과 전체 목록을 보여줄 쿼리
            ResultSet rs = stmt.executeQuery("select * from department");

            while (rs.next()) {

                String deptName = rs.getString("dept_name"); //

                deptList.add(deptName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptList; // 전체 데이터 저장하는 data 벡터 리턴
    }
}
