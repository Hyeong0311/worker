package org.example.worker.common;

import lombok.Cleanup;
import org.example.worker.vo.SupervisorVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum LoginUtil {

    INSTANCE;

    public Optional<SupervisorVO> getSupervisor(String sid, String spw) throws Exception {
        String sql = """
                select * from supervisor
                where
                    sid = ?
                and
                    spw = ?
                and
                    sdelflag = 0
                and 
                    dept != 'HR'
                ;
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        ps.setString(2, spw);
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

    public Optional<SupervisorVO> getHR(String sid, String spw) throws Exception {
        String sql = """
                select * from supervisor
                where
                    sid = ?
                and
                    spw = ?
                and
                    sdelflag = 0
                and 
                    dept = 'HR'
                ;
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        ps.setString(2, spw);
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
}
