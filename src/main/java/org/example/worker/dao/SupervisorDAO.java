package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.SupervisorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
public enum SupervisorDAO {
    INSTANCE;

    SupervisorDAO() {
    }

    public Integer mInsert(SupervisorVO supervisorVO) throws Exception {

        String sql = "insert into supervisor (sid, spw, dept) values (?, ?, ?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, supervisorVO.getSid());
        ps.setString(2, supervisorVO.getSpw());
        ps.setString(3, supervisorVO.getDept());

        int count = ps.executeUpdate();

        if (count != 1) {
            throw new Exception();
        }

        return supervisorVO.getSno();
    }

    public SupervisorVO loginSupervisor(String sid, String dept) throws Exception {
        SupervisorVO supervisorVO = null;

        String sql = "SELECT * FROM supervisor WHERE sid = ? AND dept = ? AND sdelflag = false";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sid);
        ps.setString(2, dept);

        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            supervisorVO = SupervisorVO.builder()
                    .sno(rs.getInt("sno"))
                    .sid(rs.getString("sid"))
                    .spw(rs.getString("spw"))
                    .dept(rs.getString("dept"))
                    .sdelflag(rs.getBoolean("sdelflag"))
                    .build();
        }

        return supervisorVO;
    }
}
