package com.chargr.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chargr.dto.request.CreateUpdateUser;
import com.chargr.model.GetBankDetailsByVendorId;
import com.chargr.model.GetUserBookingByUserId;
import com.chargr.model.GetUserByEmail;
import com.chargr.util.query.VendorQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chargr.dto.request.CreateUpdateUserBooking;
import com.chargr.model.GetUserBookingByVendorId;
import com.chargr.util.UserUtil;
import com.chargr.util.VendorConstants;
import com.chargr.util.query.UserQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserUtil userUtil;

    public int insertUserBooking(CreateUpdateUserBooking createUserBooking){
        KeyHolder holder = new GeneratedKeyHolder();
        int row  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(UserQuery.CreateUserBooking, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(++count,createUserBooking.getUserId());
                    ps.setInt(++count,createUserBooking.getVendorId());
                    ps.setInt(++count,createUserBooking.getSlotDuration());
                    ps.setInt(++count,createUserBooking.getAmount());
                    ps.setString(++count,createUserBooking.getStartTime());
                    ps.setString(++count,createUserBooking.getEndTime());
                    ps.setString(++count,createUserBooking.getStatus());
                    return ps;
                }
            };
            jdbcTemplate.update(psc,holder);
            row = holder.getKey().intValue();
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }
        return row;
    }

    public int updateUserBooking(CreateUpdateUserBooking updateUserBooking){
        int updateCount  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(UserQuery.UpdateUserBooking);
                    ps.setInt(++count,updateUserBooking.getSlotDuration());
                    ps.setInt(++count,updateUserBooking.getAmount());
                    ps.setString(++count,updateUserBooking.getStartTime());
                    ps.setString(++count,updateUserBooking.getEndTime());
                    ps.setString(++count,updateUserBooking.getStatus());
                    ps.setInt(++count,updateUserBooking.getId());
                    return ps;
                }
            };
            updateCount = jdbcTemplate.update(psc);
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }
        return updateCount;
    }

    public List<GetUserBookingByVendorId> getUserBookingByVendorId(int vendorId){
        List<GetUserBookingByVendorId> data = null;
        try {
            RowMapper<GetUserBookingByVendorId> rowMapper = new RowMapper<GetUserBookingByVendorId>() {
                @Override
                public GetUserBookingByVendorId mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return userUtil.prepareUserBookingByVendorIdObject(rs);
                }
            };
            data = jdbcTemplate.query(UserQuery.GetUserBookingByVendorId,rowMapper,vendorId);

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public List<GetUserBookingByUserId> getUserBookingByUserId(int id){
        List<GetUserBookingByUserId> data = new ArrayList<>();
        try {
            RowMapper<GetUserBookingByUserId> rowMapper = new RowMapper<GetUserBookingByUserId>() {
                @Override
                public GetUserBookingByUserId mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return userUtil.prepareUserBookingByUserIdObject(rs);
                }
            };
                data = jdbcTemplate.query(UserQuery.GetUserBookingByUserId,rowMapper,id);


        } catch (DataAccessException e) {
            log.error("Unable to access data " + e);
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public GetUserByEmail getUserByEmail(String email){
        GetUserByEmail data = null;
        try {
            RowMapper<GetUserByEmail> rowMapper = new RowMapper<GetUserByEmail>() {
                @Override
                public GetUserByEmail mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return userUtil.prepareUserByEmailObject(rs);
                }
            };
                data = jdbcTemplate.queryForObject(UserQuery.GetUserByEmail,rowMapper,email);

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public int updateUserBookingStatus(String status, int id){
        int count =0;
        try {
            count = jdbcTemplate.update(UserQuery.UpdateUserBookingStatus ,status,id );

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return count;
    }

    public int updateUserStatusWithEmail(int status, String email){
        int count =0;
        try {
            count = jdbcTemplate.update(UserQuery.UpdateUserStatus ,status,email );

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return count;
    }

    public boolean validateUserEmail(String email){
        boolean ispresent = false;
        try {
            ispresent = jdbcTemplate.query(UserQuery.validateUserEmail, userUtil.getBooleanResultSetExtractor(),email);
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }

        return ispresent;
    }

    public int createUser(CreateUpdateUser createUpdateUser){
        KeyHolder holder = new GeneratedKeyHolder();
        int row  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(UserQuery.CreateUser, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(++count,2);
                    ps.setString(++count,createUpdateUser.getName());
                    ps.setString(++count,createUpdateUser.getMobile() == null || createUpdateUser.getMobile().isBlank()
                                        || createUpdateUser.getMobile().isEmpty() ? "" : createUpdateUser.getMobile());
                    ps.setString(++count,createUpdateUser.getEmail());
                    ps.setString(++count,createUpdateUser.getGoogleToken());
                    ps.setInt(++count,1);

                    return ps;
                }
            };
            jdbcTemplate.update(psc,holder);
            row = holder.getKey().intValue();
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }
        return row;
    }

}
