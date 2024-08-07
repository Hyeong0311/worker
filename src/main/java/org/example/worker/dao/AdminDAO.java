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

@Log4j2
public enum AdminDAO {
    INSTANCE;

    AdminDAO() {
    }

    public void aInsert(AdminVO adminVO) throws Exception {
        String sql = "insert into admin (aid, apw) values (?, ?)";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, adminVO.getAid());
        ps.setString(2, adminVO.getApw());

        int count = ps.executeUpdate();
        if (count != 1) {
            throw new Exception();
        }
    }

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
}
