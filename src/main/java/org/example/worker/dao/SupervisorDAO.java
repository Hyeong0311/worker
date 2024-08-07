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
import java.util.Optional;

@Log4j2
public enum SupervisorDAO {
    INSTANCE;

    public Optional<SupervisorVO> get(String sid, String spw, String dept) throws Exception {
        String sql = """
                select * from supervisor
                where
                    sid = ?
                and
                    spw = ?
                and
                    dept = ?
                and
                    sdelflag = 0
                ;
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        ps.setString(2, spw);
        ps.setString(3, dept);
        @Cleanup ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }

        SupervisorVO vo = SupervisorVO.builder()
                .sid(rs.getString("sid"))
                .spw(rs.getString("spw"))
                .dept(rs.getString("dept"))
                .sdelflag(rs.getBoolean("sdelflag"))
                .build();

        return Optional.of(vo);
    }

    // AdminController 연결
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
}
