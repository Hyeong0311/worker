package org.example.worker.dao;


import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.worker.common.ConnectionUtil;
import org.example.worker.vo.AdminVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Log4j2
public enum AdminDAO {
    INSTANCE;

    AdminDAO() {
    }

    public Integer aInsert(AdminVO adminVO) throws Exception {

        String sql = "insert into admin (aid, apw) values (?, ?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, adminVO.getAid());
        ps.setString(2, adminVO.getApw());

        int count = ps.executeUpdate();

        if (count != 1) {
            throw new Exception();
        }

        return adminVO.getAno();
    }


    }


}
