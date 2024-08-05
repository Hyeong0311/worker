package org.example.worker.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.ManagerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Log4j2
public enum ManagerDAO {
    INSTANCE;

    ManagerDAO() {
    }

    public Integer mInsert(ManagerVO managerVO) throws Exception {

        String sql = """
                insert into manager (mid, mpw, dept) values (?, ?, ?)
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, managerVO.getMid());
        ps.setString(2, managerVO.getMpw());
        ps.setString(3, managerVO.getDept());

        int count = ps.executeUpdate();

        if (count != 1) {
            throw new Exception();
        }

        return managerVO.getMno();
    }

}
