package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.HRListDTO;
import org.example.worker.vo.SalaryCheckDTO;
import org.example.worker.vo.ScheduleVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public enum ScheduleDAO  {

    INSTANCE;

    public void stime(Integer wid) throws Exception {

        String sql = "insert into schedule (wid, time, category) values(?, now(),'in')";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, wid);

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception();
        }
    }

    public void otime(Integer wid) throws Exception {

        String sql = "insert into schedule (wid, time, category) values(?, now(),'out')";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, wid);

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception();
        }
    }

    public List<HRListDTO> HRList(int page) throws Exception {

        int skip = (page - 1) * 10;

        String query = """
                select
                    tmp.wname, schedule.category, schedule.time,tmp.dept, tmp.wid, schedule.note
                from
                    schedule inner join
                    (
                        select
                            worker.wname, worker.wid, supervisor.dept
                        from worker inner join supervisor on worker.sid = supervisor.sid
                    ) tmp on schedule.wid = tmp.wid
                where
                    tmp.wid > 0
                order by tmp.dept
                limit ?, 10
                """;


        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, skip);

        @Cleanup ResultSet rs = ps.executeQuery();

        ArrayList<HRListDTO> list = new ArrayList<>();

        while(rs.next()) {

            HRListDTO dto = HRListDTO.builder()
                    .wname(rs.getString("wname"))
                    .category(rs.getString("category"))
                    .time(rs.getTimestamp("time"))
                    .dept(rs.getString("dept"))
                    .wid(rs.getInt("wid"))
                    .note(rs.getString("note"))
                    .build();

            list.add(dto);
        }

        return list;
    }

    public int getTotal() throws Exception {

        String query = """
                select
                    count(scno)
                from
                    schedule
                where
                    scno > 0
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        return rs.getInt(1);
    }

    public Optional<HRListDTO> getIntime(Integer wid) throws Exception {

        String query = """
                select
                    tmp.wname, schedule.category, schedule.time,tmp.dept, tmp.wid, schedule.note
                from
                    schedule inner join
                    (
                        select
                            worker.wname, worker.wid, supervisor.dept
                        from worker inner join supervisor on worker.sid = supervisor.sid
                        where
                            worker.wid = ?
                    ) tmp on schedule.wid = tmp.wid
                where
                    tmp.wid > 0
                and
                    schedule.category = 'in'
                order by tmp.dept
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, wid);

        @Cleanup ResultSet rs = ps.executeQuery();
        if(!rs.next()) {
            return Optional.empty();
        }

        HRListDTO vo = HRListDTO.builder()
                .wname(rs.getString("wname"))
                .category(rs.getString("category"))
                .time(rs.getTimestamp("time"))
                .dept(rs.getString("dept"))
                .wid(rs.getInt("wid"))
                .note(rs.getString("note"))
                .build();

        return Optional.of(vo);
    }

    public Optional<HRListDTO> getOuttime(Integer wid) throws Exception {

        String query = """
                select
                    tmp.wname, schedule.category, schedule.time,tmp.dept, tmp.wid, schedule.note
                from
                    schedule inner join
                    (
                        select
                            worker.wname, worker.wid, supervisor.dept
                        from worker inner join supervisor on worker.sid = supervisor.sid
                        where
                            worker.wid = ?
                    ) tmp on schedule.wid = tmp.wid
                where
                    tmp.wid > 0
                and
                    schedule.category = 'out'
                order by tmp.dept
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, wid);

        @Cleanup ResultSet rs = ps.executeQuery();
        if(!rs.next()) {
            return Optional.empty();
        }

        HRListDTO vo = HRListDTO.builder()
                .wname(rs.getString("wname"))
                .category(rs.getString("category"))
                .time(rs.getTimestamp("time"))
                .dept(rs.getString("dept"))
                .wid(rs.getInt("wid"))
                .note(rs.getString("note"))
                .build();

        return Optional.of(vo);
    }



}
