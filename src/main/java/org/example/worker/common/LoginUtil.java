package org.example.worker.common;

import lombok.Cleanup;
import org.example.worker.vo.AdminVO;
import org.example.worker.vo.SupervisorVO;
import org.example.worker.vo.WorkerVO;

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

    public Optional<AdminVO> getAdmin(String aid, String apw) throws Exception {
        String sql = """
                select * from admin
                where
                    aid = ?
                and
                    apw = ?
                ;
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, aid);
        ps.setString(2, apw);
        @Cleanup ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return Optional.empty();
        }

        AdminVO vo = AdminVO.builder()
                .aid(rs.getString("aid"))
                .apw(rs.getString("apw"))
                .build();

        return Optional.of(vo);
    }

    public Optional<WorkerVO> getworker(String wid) throws Exception {
        String sql = """
                select *
                from worker
                where wid=?
                """;
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, wid);
        @Cleanup ResultSet rs = ps.executeQuery();

        if(!rs.next()) {
            return Optional.empty();
        }

        WorkerVO workervo = WorkerVO.builder()
                .wno(rs.getInt("wno"))
                .wid(rs.getInt("wid"))
                .wname(rs.getString("wname"))
                .wdelflag(rs.getBoolean("wdelflag"))
                .sid(rs.getString("sid"))
                .build();

        return Optional.of(workervo);
    }

}
