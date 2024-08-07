package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.SupervisorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum SupervisorDAO {
    INSTANCE;

    // Supervisor 목록을 가져오는 메서드
    public List<SupervisorVO> getSupervisorList() throws Exception {
        List<SupervisorVO> supervisorList = new ArrayList<>();
        String sql = "select * from supervisor where sdelflag = 0";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            SupervisorVO vo = SupervisorVO.builder()
                    .sid(rs.getString("sid"))
                    .spw(rs.getString("spw"))
                    .dept(rs.getString("dept"))
                    .sdelflag(rs.getBoolean("sdelflag"))
                    .build();
            supervisorList.add(vo);
        }

        return supervisorList;
    }

    // Supervisor를 데이터베이스에 삽입하는 메서드
    public void sInsert(SupervisorVO supervisor) throws Exception {
        String sql = "insert into supervisor (sid, spw, dept, sdelflag) values (?, ?, ?, ?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, supervisor.getSid());
        ps.setString(2, supervisor.getSpw());
        ps.setString(3, supervisor.getDept());
        ps.setBoolean(4, supervisor.isSdelflag());

        int count = ps.executeUpdate();
        if (count != 1) {
            throw new Exception("Failed to insert supervisor");
        }
        log.info("New Supervisor with ID {} added", supervisor.getSid());
    }

    // Supervisor를 데이터베이스에서 삭제하는 메서드
    public void deleteSupervisor(String sid) throws Exception {
        String sql = "update supervisor set sdelflag = 1 where sid = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, sid);

        int count = ps.executeUpdate();
        if (count != 1) {
            throw new Exception("Failed to delete supervisor");
        }
        log.info("Supervisor with ID {} deleted", sid);
    }

    // Supervisor ID가 존재하는지 확인하는 메서드
    public boolean isSupervisorIdExist(String sid) throws Exception {
        String sql = "SELECT COUNT(*) FROM supervisor WHERE sid = ?";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }
}
