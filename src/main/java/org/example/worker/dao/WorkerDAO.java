package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.WorkerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;


@Log4j2
public enum WorkerDAO {
    INSTANCE;

    WorkerDAO() {

    }

    public void wInsert(WorkerVO worker) throws Exception {

        String sql = "insert into worker (wid, wname) values(?, ?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, worker.getWid());
        ps.setString(2, worker.getWname());

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

        int count = ps.executeUpdate();
        if(count != 1) {
            throw new Exception();
        }
    }

}
