package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.SupervisorDTO;
import org.example.worker.vo.SupervisorVO;
import org.example.worker.vo.WorkerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
public enum WorkerDAO {
    INSTANCE;

    WorkerDAO() {

    }

    public void wInsert(WorkerVO worker) throws Exception {

        String sql = "insert into worker (wid, wname,sid) values(?, ?, ?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, worker.getWid());
        ps.setString(2, worker.getWname());
        ps.setString(3, worker.getSid());

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception();
        }

    }

    public void wRemove(Integer wno) throws Exception {

        String sql = "delete from worker where wno = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, wno);
        log.info("wno: " + wno);

        int count = ps.executeUpdate();
        if(count != 1) {
            throw new Exception();
        }
    }

    public int getTotal() throws Exception {

        String sql = "select count(wno) from worker where wno>0 and wdelflag = false";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        int total = rs.getInt(1);

        return total;
    }

    public List<WorkerVO> wListSuper(String sid , Integer page) throws Exception {

        int skip = (page - 1) *10;

        String sql = """
                select
                    wno, wname, wid, sid, wdelflag
                from
                    worker
                where
                    sid = ?
                LIMIT ?, 10
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, sid);
        ps.setInt(2, skip);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<WorkerVO> list = new ArrayList<>();

        while(rs.next()) {
            WorkerVO worker = WorkerVO.builder()
                    .wno(rs.getInt("wno"))
                    .wid(rs.getInt("wid"))
                    .wname(rs.getString("wname"))
                    .wdelflag(rs.getBoolean("wdelflag"))
                    .sid(rs.getString("sid"))

                    .build();

            log.info(worker);

            list.add(worker);
        }
        return list;
    }

    public Optional<WorkerVO> getworker(Integer wno) throws Exception {
        String sql = """
                select wno, wno, wid, wname, wdelflag, sid
                    from worker
                where wno = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, wno);
        @Cleanup ResultSet rs = ps.executeQuery();

        if(!rs.next()) {
            return Optional.empty();
        }
        WorkerVO worker = WorkerVO.builder()
                .wno(rs.getInt("wno"))
                .wid(rs.getInt("wid"))
                .wname(rs.getString("wname"))
                .wdelflag(rs.getBoolean("wdelflag"))
                .sid(rs.getString("sid"))
                .build();

        return Optional.of(worker);
    }

    public void allRemove(String sid) throws Exception {
        String sql = "DELETE FROM worker WHERE sid = ?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,sid);

    }

}
