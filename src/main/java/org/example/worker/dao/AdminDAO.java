package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.AdminVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum AdminDAO {
    INSTANCE;

    AdminDAO() {
    }

    // 관리자 계정을 데이터베이스에 삽입하는 메서드
    public void aInsert(AdminVO adminVO) throws Exception {
        String sql = "insert into admin (aid, apw) values (?, ?)";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, adminVO.getAid());
        ps.setString(2, adminVO.getApw());

        int count = ps.executeUpdate();
        if (count != 1) {
            throw new Exception("Failed to insert admin");
        }
    }

    // 모든 관리자 계정을 가져오는 메서드
    public List<AdminVO> getAdminList() throws Exception {
        String sql = "select aid, apw from admin";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<AdminVO> adminList = new ArrayList<>();
        while (rs.next()) {
            AdminVO admin = AdminVO.builder()
                    .aid(rs.getString("aid"))
                    .apw(rs.getString("apw"))
                    .build();
            adminList.add(admin);
        }
        return adminList;
    }

    // 주어진 아이디와 비밀번호로 관리자 계정을 가져오는 메서드
    public AdminVO getAdminByIdAndPassword(String id, String password) throws Exception {
        String sql = "SELECT * FROM admin WHERE aid = ? AND apw = ?";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, password);
        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return AdminVO.builder()
                    .aid(rs.getString("aid"))
                    .apw(rs.getString("apw"))
                    .build();
        }
        return null;
    }

    // 주어진 아이디와 비밀번호로 관리자 계정의 존재 여부를 확인하는 메서드
    public Optional<AdminVO> getAdmin(String aid, String apw) throws Exception {
        String sql = "select * from admin where aid = ? and apw = ?";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, aid);
        ps.setString(2, apw);
        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            AdminVO vo = AdminVO.builder()
                    .aid(rs.getString("aid"))
                    .apw(rs.getString("apw"))
                    .build();
            return Optional.of(vo);
        } else {
            return Optional.empty();
        }
    }
}
