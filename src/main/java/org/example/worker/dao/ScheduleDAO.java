package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Log4j2
public enum ScheduleDAO  {

    INSTANCE;

    public void stime(Integer wid) throws Exception {

        String sql = "insert into schedule(wid,in_time) values(?,now())";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, wid);

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception();
        }
    }

    public void otime(Integer wid) throws Exception {

        String sql = "insert into schedule(wid,out_time) values(?,now())";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, wid);

        int count = ps.executeUpdate();

        if(count != 1) {
            throw new Exception();
        }


    }

}
