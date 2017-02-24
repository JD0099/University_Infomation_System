/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DeptInfoGetterHandler;
import controller.UserInfoManageHandler;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.DeptNameChanger;
import model.UserInfo;

/**
 *
 * @author yun
 */
public class HaksaView extends JFrame implements ActionListener {

    final int SELECT_RADIO = 0;
    final int SELECT_TAB = 1;

    private UserInfoManageHandler uimHandler;
    private DeptInfoGetterHandler digHandler;
    private ModifyInfoView modifyInfo;

    private DefaultTableModel smodel, pmodel, cModel;

    String colNames[] = {"선택", "ID", "이름", "학과", "주민번호"};

    private JPanel haksaPn, sPn, pPn;
    private JTabbedPane tab;
    private JTable sTable, pTable, cTable;
    private JScrollPane sScrollPane, pScrollPane;
    private JLabel posL, curPosL, idL, nameL, deptL, resnumL;
    private TextField idTf, nameTf, resnumTf;
    private JComboBox deptCb;
    private Button insertBtn, deleteBtn, updateBtn, selectBtn, listReBtn, modifyPwBtn;

    private String userType = null;

    private ArrayList sEditRows = new ArrayList();
    private ArrayList pEditRows = new ArrayList();
    private ArrayList cEditRows = new ArrayList();

    private String resultMsg;
    private String haksaId = "";

    public HaksaView(String haksaId) {

        this.haksaId = haksaId;
        uimHandler = new UserInfoManageHandler();
        digHandler = new DeptInfoGetterHandler();
        //modifyInfo = new ModifyInfoView(haksaId);

        initView();
        BuildOthers();
        BuildTab();
        BuildStuPanel();
        BuildProfPanel();
    }

    //전체패널 초기화
    public void initView() {
        setTitle("학사담당자 화면"); //제목
        setSize(1200, 900); //크기
        setLocation(50, 50); //프레임의 위치
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true); //보이게
        setResizable(false); //프레임 크기 조정 x
        setLocationRelativeTo(null); //프레임을 중앙으로 배치.

        haksaPn = new JPanel(); //전체 패널
        haksaPn.setLayout(null);
        this.add(haksaPn); // 패널을 프레임에 추가
    }

    //Tab 생성
    public void BuildTab() {
        tab = new JTabbedPane();
        tab.setBounds(30, 30, 900, 500);
        tab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {

                if (tab.getSelectedIndex() == 0) {
                    curPosL.setText("학생");
                } else {
                    curPosL.setText("교수");
                }
            }
        });
        haksaPn.add(tab); //전체패널 위에 JTabbedPane추가
    }

    //학생테이블
    public void BuildStuPanel() {

        sPn = new JPanel(); //학생패널
        sPn.setLayout(null);

        ArrayList<UserInfo> stuList = uimHandler.getUserList("student");//학생 전체 리스트 요청
        smodel = new DefaultTableModel(colNames, 0) {
            public boolean isCellEditable(int row, int col) {
                if (col == 1 || col == 4 || col == 3) {
                    return false;
                }
                return true;
            }
        };//학생 테이블 모델

        //현재 DB에 있는 학생리스트를 보여준다.
        for (int i = 0; i < stuList.size(); i++) {
            UserInfo ui = stuList.get(i);
            Object[] sData = {new Boolean(false), ui.getId(), ui.getName(), ui.getStringDept(), ui.getResnum()};
            smodel.addRow(sData);
        }
        smodel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                System.out.println("tableChanged");//////////////
                if (tme.getType() == TableModelEvent.UPDATE) {
                    System.out.println("" + tme.getLastRow());
                    sEditRows.add(tme.getLastRow());
                }
                sEditRows.add(sTable);
            }
        });

        tab.add("학생 목록", sPn); //tab추가
        sTable = new JTable(smodel) { //smodel로 학생 테이블 생성
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;//체크박스 구현을 위해 column을 Boolean타입으로 리턴
                    default:
                        return String.class;//나머지는 String 타입 사용
                }
            }
        };

        //학생테이블 컬럼별 넓이 설정
        sTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        sTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        sTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        sTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        sTable.getColumnModel().getColumn(3).setPreferredWidth(280);
        sTable.getColumnModel().getColumn(4).setPreferredWidth(280);

        sScrollPane = new JScrollPane(sTable); //스크롤
        sScrollPane.setSize(900, 500);
        sPn.add(sScrollPane); //학생패널 위에 학생JScrollPane추가
    }

    //교수테이블
    public void BuildProfPanel() {

        pPn = new JPanel(); //교수 패널
        pPn.setLayout(null);

        ArrayList<UserInfo> profList = uimHandler.getUserList("professor");//교수 전체 리스트 요청
        pmodel = new DefaultTableModel(colNames, 0) {
            public boolean isCellEditable(int row, int col) {
                if (col == 1 || col == 4 || col == 3) {
                    return false;
                }
                return true;
            }
        };//교수 테이블 모델

        //현재 DB에 있는 교수리스트를 보여준다.
        for (int i = 0; i < profList.size(); i++) {
            UserInfo ui = profList.get(i);
            Object[] pData = {new Boolean(false), ui.getId(), ui.getName(), ui.getStringDept(), ui.getResnum()};
            pmodel.addRow(pData);
        }

        pmodel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                System.out.println("p_tableChanged");
                if (tme.getType() == TableModelEvent.UPDATE) {
                    System.out.println("" + tme.getLastRow());

                    pEditRows.add(tme.getLastRow());
                }
                pEditRows.add(pTable);
            }
        });

        tab.add("교수 목록", pPn); //tab추가

        pTable = new JTable(pmodel) { //pmodel로 교수 테이블 생성
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;//체크박스 구현을 위해 column을 Boolean타입으로 리턴
                    default:
                        return String.class;//나머지는 String 타입 사용
                }
            }
        };

        //교수테이블 컬럼별 넓이 설정
        pTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        pTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        pTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        pTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        pTable.getColumnModel().getColumn(3).setPreferredWidth(280);
        pTable.getColumnModel().getColumn(4).setPreferredWidth(280);

        pScrollPane = new JScrollPane(pTable); //스크롤
        pScrollPane.setSize(900, 500);
        pPn.add(pScrollPane); //교수패널 위에 교수JScrollPane추가
    }

    //나머지 컴포넌트
    public void BuildOthers() {

        posL = new JLabel("직책 : ");
        haksaPn.add(posL);
        posL.setBounds(100, 580, 100, 50);

        curPosL = new JLabel("학생");
        haksaPn.add(curPosL);
        curPosL.setBounds(200, 580, 100, 50);

        idL = new JLabel("ID");
        nameL = new JLabel("이름");
        deptL = new JLabel("학과");
        resnumL = new JLabel("주민번호");
        haksaPn.add(idL);
        haksaPn.add(nameL);
        haksaPn.add(deptL);
        haksaPn.add(resnumL);
        idL.setBounds(100, 650, 100, 30);
        nameL.setBounds(250, 650, 100, 30);
        deptL.setBounds(450, 650, 100, 30);
        resnumL.setBounds(700, 650, 100, 30);

        ArrayList<String> deptlist = digHandler.getDeptList();
        //TextField
        idTf = new TextField(5);
        nameTf = new TextField(10);
        deptCb = new JComboBox();

        for (int i = 0; i < deptlist.size(); i++) {
            deptCb.addItem(deptlist.get(i));
        }

        resnumTf = new TextField(14);
        haksaPn.add(idTf);
        haksaPn.add(nameTf);
        haksaPn.add(deptCb);
        haksaPn.add(resnumTf);
        idTf.setBounds(100, 700, 100, 30);
        nameTf.setBounds(250, 700, 150, 30);
        deptCb.setBounds(450, 700, 210, 30);
        resnumTf.setBounds(700, 700, 210, 30);

        //Button
        insertBtn = new Button("Insert");
        deleteBtn = new Button("Delete");
        updateBtn = new Button("Update");
        selectBtn = new Button("Select");
        listReBtn = new Button("Reset");
        modifyPwBtn = new Button("PW change");
        haksaPn.add(insertBtn);
        haksaPn.add(deleteBtn);
        haksaPn.add(updateBtn);
        haksaPn.add(selectBtn);
        haksaPn.add(listReBtn);
        haksaPn.add(modifyPwBtn);
        insertBtn.setBounds(1000, 100, 100, 50);
        deleteBtn.setBounds(1000, 250, 100, 50);
        updateBtn.setBounds(1000, 400, 100, 50);
        selectBtn.setBounds(1000, 550, 100, 50);
        listReBtn.setBounds(1000, 700, 100, 50);
        modifyPwBtn.setBounds(750, 600, 130, 40);
        //버튼에 대한 리스너
        insertBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        selectBtn.addActionListener(this);
        listReBtn.addActionListener(this);
        modifyPwBtn.addActionListener(this);
    }

    @Override //버튼에 대한 리스너
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == insertBtn) {
            System.out.println("삽입버튼 , deptnum");
            InsertUserInfo();
        } else if (event.getSource() == deleteBtn) {
            System.out.println("삭제");
            DeleteUserInfo();
        } else if (event.getSource() == updateBtn) {
            System.out.println("수정");
            UpdateUserInfo();
        } else if (event.getSource() == selectBtn) {
            System.out.println("조회");
            SelectUserInfo();
        } else if (event.getSource() == listReBtn) {
            System.out.println("리스트 초기화");
            initTableModel();
        } else if (event.getSource() == modifyPwBtn) {
            System.out.println("비밀번호 변경");
            modifyInfo = new ModifyInfoView(haksaId);
            /////////////////////////////
        }

    }

    //사용자 타입 설정
    public void setCurrentUser() {

        if (tab.getSelectedIndex() == 0) {
            cModel = smodel;
            userType = "student";
            cTable = sTable;
            cEditRows = sEditRows;
        } else {
            cModel = pmodel;
            userType = "professor";
            cTable = pTable;
            cEditRows = pEditRows;
        }

    }

    //사용자 삽입
    public void InsertUserInfo() {

        setCurrentUser();

        String id = idTf.getText();
        String name = nameTf.getText();
        int cb_index = deptCb.getSelectedIndex();
        int dept = DeptNameChanger.StringtoNum((String) deptCb.getItemAt(cb_index));
        String resnum = resnumTf.getText();

        resultMsg = uimHandler.InsertUserInfo(new UserInfo(userType, id, name, dept, resnum));

        if (resultMsg == "성공") {
            Object[] sData = {new Boolean(false), id, name, DeptNameChanger.NumtoString(dept), resnum};
            cModel.addRow(sData); //추가된 정보를 JTable에 보이게 함
        } else {
            JOptionPane.showMessageDialog(null, resultMsg, "유효성을 확인하세요.", JOptionPane.PLAIN_MESSAGE);
        }

    }

    //사용자 삭제
    public void DeleteUserInfo(){
        
        ArrayList<String> deleteUsers = new ArrayList<String>(); //삭제할 정보를 담을 ArrayList

        setCurrentUser();
        int count = cModel.getRowCount(); //model에 있는 row의 갯수       
        for(int i = count-1 ; i>=0 ; i--){
            boolean cb = (boolean)cModel.getValueAt(i, 0); //i번째행의 0번째열(체크박스)
            
            if(cb == true){ //체크박스가 선택되어 있다면
                System.out.println(i+" : "+ cModel.getValueAt(i, 1));//ture인 행의 id 출력해보기
                deleteUsers.add(cModel.getValueAt(i, 1).toString());//vertor에 선택된 id들 담기                
            }
        }
        
        resultMsg = uimHandler.DeleteUserInfo(deleteUsers, userType);
        if(resultMsg == "성공"){
            for(int i = count-1 ; i>=0 ; i--){
                boolean cb = (boolean)cModel.getValueAt(i, 0); //i번째행의 0번째열(체크박스)
                if(cb == true){ //체크박스가 선택되어 있다면           
                    cModel.removeRow(i);
                }
            }   
        }
        else {
            JOptionPane.showMessageDialog(null, resultMsg , "유효성을 확인하세요.", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //사용자 수정
    public void UpdateUserInfo() {

        cEditRows.clear();
        ArrayList<UserInfo> updateUsers = new ArrayList<UserInfo>();//수정할 정보를 담을 ArrayList
        setCurrentUser();

        cTable.getCellEditor().stopCellEditing();//수정 멈춤

        int count = cEditRows.size();
        for (int i = 0; i < count; i++) {

            String newId = (String) cModel.getValueAt(i, 1);
            String newName = (String) cModel.getValueAt(i, 2);
            int newDept = DeptNameChanger.StringtoNum((String) cModel.getValueAt(i, 3));
            String newresnum = (String) cModel.getValueAt(i, 4);

            updateUsers.add(new UserInfo(userType, newId, newName, newDept, newresnum));

        }
        resultMsg = uimHandler.UpdateUserInfo(updateUsers, userType);
        if (resultMsg != "성공") {
            JOptionPane.showMessageDialog(null, resultMsg, "유효성을 확인하세요.", JOptionPane.PLAIN_MESSAGE);
        }

    }

    //사용자 조회
    public void SelectUserInfo() {

        ArrayList<UserInfo> selectList = new ArrayList<UserInfo>();

        setCurrentUser();

        cModel.setNumRows(0);

        String id = idTf.getText();
        String name = nameTf.getText();
        int cb_index = deptCb.getSelectedIndex();
        int dept = DeptNameChanger.StringtoNum((String) deptCb.getItemAt(cb_index));

        selectList = uimHandler.SelectUserInfo(new UserInfo(userType, id, name, dept), userType);

        //사용자 리스트를 보여준다.
        for (int i = 0; i < selectList.size(); i++) {
            UserInfo ui = selectList.get(i);
            Object[] data = {new Boolean(false), ui.getId(), ui.getName(), ui.getStringDept(), ui.getResnum()};
            cModel.addRow(data);
        }

    }

    //조회 이전으로 사용자 리스트 출력
    public void initTableModel() {

        setCurrentUser();

        cModel.setNumRows(0);//테이블 모델을 비움

        ArrayList<UserInfo> cUserList = uimHandler.getUserList(userType);//현재 유저 전체 리스트 요청
        for (int i = 0; i < cUserList.size(); i++) {
            UserInfo ui = cUserList.get(i);
            Object[] data = {new Boolean(false), ui.getId(), ui.getName(), ui.getStringDept(), ui.getResnum()};
            cModel.addRow(data);
            System.out.println(ui.getId());///////
        }
    }

}
