package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.SupervisorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List; // 이 부분을 추가

@Log4j2
public enum SupervisorDAO {
    INSTANCE;

    SupervisorDAO() {
    }

    public void mInsert(SupervisorVO supervisorVO) throws Exception {
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
    }

    public SupervisorVO loginSupervisor(String sid) throws Exception {
        String sql = "SELECT sid, spw, dept FROM supervisor WHERE sid = ?";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return SupervisorVO.builder()
                    .sid(rs.getString("sid"))
                    .spw(rs.getString("spw"))
                    .dept(rs.getString("dept"))
                    .build();
        } else {
            return null;
        }
    }

    public List<SupervisorVO> getSupervisorList() throws Exception {
        String sql = "select sid, spw, dept from supervisor";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<SupervisorVO> supervisorList = new ArrayList<>();
        while (rs.next()) {
            SupervisorVO supervisor = SupervisorVO.builder()
                    .sid(rs.getString("sid"))
                    .spw(rs.getString("spw"))
                    .dept(rs.getString("dept"))
                    .build();
            supervisorList.add(supervisor);
        }
        return supervisorList;
    }
}
