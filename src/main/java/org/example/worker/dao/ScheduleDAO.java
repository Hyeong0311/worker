package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.ScheduleVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

//    public List<ScheduleVO> wListSuper(int page) throws Exception {
//
//        int skip = (page - 1) * 10;
//
//        String sql = """
//                select * from schedule
//                            where
//                             category = 'in'
//                             order by scno desc
//                             limit ?,10
//                """;
//
//        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
//        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, skip);
//        @Cleanup ResultSet rs = ps.executeQuery();
//
//        List<ScheduleVO> list = new ArrayList<>();
//
//        while(rs.next()){
//
//            ScheduleVO vo = ScheduleVO.builder()
//                    .scno(rs.getInt("scno"))
//                    .wid(rs.getInt("wid"))
//                    .time(rs.getTimestamp("time"))
//                    .category(rs.getString("category"))
//                    .note(rs.getString("note"))
//                    .build();
//
//            list.add(vo);
//        }
//
//        return list;
//    }

    public Integer getTotalSchedule() throws Exception {
        String sql = "select count(DISTINCT wid) from schedule";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        int total = rs.getInt(1);
        return total;
    }
}
