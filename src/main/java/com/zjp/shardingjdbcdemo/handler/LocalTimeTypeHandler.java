package com.zjp.shardingjdbcdemo.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

/**
 * ClassName: LocalTimeTypeHandler
 * Package: com.zjp.shadingjdbcdemo.handler
 * Description:
 * LocalTime是为了解决shardingResultSet转换LocalTime的问题
 * @author zjp
 * @version 1.0
 * @data: 2024/11/10 14:48
 */
@Component
@MappedTypes(LocalTime.class)
@MappedJdbcTypes(value = JdbcType.DATE, includeNullJdbcType = true)
public class LocalTimeTypeHandler extends BaseTypeHandler<LocalTime> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalTime parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter);
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return Convert.convert(new TypeReference<LocalTime>() {
            @Override
            public String getTypeName() {
                return super.getTypeName();
            }
        },rs.getObject(columnName));
    }

    @Override
    public LocalTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Convert.convert(new TypeReference<LocalTime>() {
            @Override
            public String getTypeName() {
                return super.getTypeName();
            }
        },rs.getObject(columnIndex));
    }

    @Override
    public LocalTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Convert.convert(new TypeReference<LocalTime>() {
            @Override
            public String getTypeName() {
                return super.getTypeName();
            }
        },cs.getObject(columnIndex));
    }
}
