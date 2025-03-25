package com.chargr.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chargr.model.GetAllVendors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chargr.dto.request.CreateUpdateBankDetails;
import com.chargr.dto.request.CreateUpdateVendor;
import com.chargr.dto.request.CreateUpdateVendorSlot;
import com.chargr.dto.request.UpdateVendorStartEndTime;
import com.chargr.model.GetBankDetailsByVendorId;
import com.chargr.model.GetVendorById;
import com.chargr.model.GetVendorSlotByVendorId;
import com.chargr.util.VendorConstants;
import com.chargr.util.VendorUtil;
import com.chargr.util.query.VendorQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class VendorRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    VendorUtil vendorUtil;

    public int vendorLogin(String email, String password){
        int ispresent = 0;
        try {
            ispresent = jdbcTemplate.query(VendorQuery.LoginVendor, vendorUtil.getIntegerResultSetExtractor(),email,password);
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }

        return ispresent;
    }

    public int updateVendorStartEndTime(UpdateVendorStartEndTime updateVendorStartEndTime){
        int updateCount  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.UpdateVendorStartEndTime);
                    ps.setString(++count,updateVendorStartEndTime.getStartTime());
                    ps.setString(++count,updateVendorStartEndTime.getEndTime());
                    ps.setInt(++count,updateVendorStartEndTime.getId());
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
    
    
    public boolean validateVendorEmail(String email){
        boolean ispresent = false;
        try {
            ispresent = jdbcTemplate.query(VendorQuery.validateVendorEmail, vendorUtil.getBooleanResultSetExtractor(),email);
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }

        return ispresent;

    }

    public boolean validateVendorMobile(String mobile){
        boolean ispresent = false;
        try {
            ispresent = jdbcTemplate.query(VendorQuery.validateVendorMobile, vendorUtil.getBooleanResultSetExtractor(),mobile);
        } catch (DataAccessException dataAccessException) {
            log.error("Unable to access data " + dataAccessException);
        } catch (Exception exc) {
            log.error("Unexpected error occurred " + exc);
        }

        return ispresent;

    }

    public int insertVendor(CreateUpdateVendor createVendor){
        KeyHolder holder = new GeneratedKeyHolder();
        int row  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.CreateVendor, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(++count,VendorConstants.INTEGER_ONE_CONSTANT);
                    ps.setInt(++count,createVendor.getBussinessId());
                    ps.setString(++count,createVendor.getName());
                    ps.setString(++count,createVendor.getShopName());
                    ps.setString(++count,createVendor.getMobile());
                    ps.setString(++count,createVendor.getEmail());
                    ps.setString(++count,createVendor.getPassword());
                    ps.setString(++count,createVendor.getLocation());
                    ps.setString(++count,createVendor.getStartTime());
                    ps.setString(++count,createVendor.getEndTime());
                    ps.setInt(++count,createVendor.getStatus());
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

    public int updateVendor(CreateUpdateVendor updateVendor){
        int updateCount  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.UpdateVendor);
                    ps.setInt(++count,updateVendor.getBussinessId());
                    ps.setString(++count,updateVendor.getName());
                    ps.setString(++count,updateVendor.getShopName());
                    ps.setString(++count,updateVendor.getMobile());
                    ps.setString(++count,updateVendor.getEmail());
                    ps.setString(++count,updateVendor.getPassword());
                    ps.setString(++count,updateVendor.getLocation());
                    ps.setString(++count,updateVendor.getStartTime());
                    ps.setString(++count,updateVendor.getEndTime());
                    ps.setInt(++count,updateVendor.getStatus());
                    ps.setInt(++count,updateVendor.getId());
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

    public int deleteVendor(int vendorId){
        int count =0;
        System.out.println(vendorId);
        try {
            count = jdbcTemplate.update(VendorQuery.DeleteVendor ,vendorId );

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return count;
    }

    public GetVendorById getVendorById(int vendorId){
        GetVendorById data = null;
        try {
            RowMapper<GetVendorById> rowMapper = new RowMapper<GetVendorById>() {
                @Override
                public GetVendorById mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return vendorUtil.prepareVendorByIdObject(rs);
                }
            };
            data = jdbcTemplate.queryForObject(VendorQuery.GetVendorById,rowMapper,vendorId);
        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public GetVendorById getVendorByEmail(String email){
        GetVendorById data = null;
        try {
            RowMapper<GetVendorById> rowMapper = new RowMapper<GetVendorById>() {
                @Override
                public GetVendorById mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return vendorUtil.prepareVendorByIdObject(rs);
                }
            };
            data = jdbcTemplate.queryForObject(VendorQuery.GetVendorByEmail,rowMapper,email);
        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public List<GetAllVendors> getAllVendors(){
        List<GetAllVendors> data =  new ArrayList<>();
        try {
            RowMapper<GetAllVendors> rowMapper = new RowMapper<GetAllVendors>() {
                @Override
                public GetAllVendors mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return vendorUtil.prepareGetAllVendorObject(rs);
                }
            };
            data = jdbcTemplate.query(VendorQuery.GetAllVendors,rowMapper);
        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public int insertBankDetails(CreateUpdateBankDetails createUpdateBankDetails){
        KeyHolder holder = new GeneratedKeyHolder();
        int row  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.CreateBankDetails, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(++count,createUpdateBankDetails.getVendorId());
                    ps.setString(++count,createUpdateBankDetails.getAccountNumber());
                    ps.setString(++count,createUpdateBankDetails.getAccountHolderName());
                    ps.setString(++count,createUpdateBankDetails.getBranchName());
                    ps.setString(++count,createUpdateBankDetails.getIfscCode());
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

    public int updateBankDetails(CreateUpdateBankDetails createUpdateBankDetails){
        int updateCount  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.UpdateBankDetails);
                    ps.setString(++count,createUpdateBankDetails.getAccountNumber());
                    ps.setString(++count,createUpdateBankDetails.getAccountHolderName());
                    ps.setString(++count,createUpdateBankDetails.getBranchName());
                    ps.setString(++count,createUpdateBankDetails.getIfscCode());
                    ps.setInt(++count,createUpdateBankDetails.getId());
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

    public List<GetBankDetailsByVendorId> getBankDetailsByVendorId(int vendorId){
        List<GetBankDetailsByVendorId> data = null;
        try {
            RowMapper<GetBankDetailsByVendorId> rowMapper = new RowMapper<GetBankDetailsByVendorId>() {
                @Override
                public GetBankDetailsByVendorId mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return vendorUtil.prepareBankDetailsByIdObject(rs);
                }
            };
            data = jdbcTemplate.query(VendorQuery.GetBankDetailsByVendorId,rowMapper,vendorId);

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

    public int deleteVendorBank(int bankId){
        int count =0;
        try {
            count = jdbcTemplate.update(VendorQuery.DeleteVendorBank ,bankId );

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return count;
    }

    public int insertVendorSlot(CreateUpdateVendorSlot createUpdateVendorSlot){
        KeyHolder holder = new GeneratedKeyHolder();
        int row  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.CreateVendorSlot, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(++count,createUpdateVendorSlot.getVendorId());
                    ps.setString(++count,createUpdateVendorSlot.getAmount());
                    ps.setInt(++count,createUpdateVendorSlot.getStatus());
                    ps.setString(++count,createUpdateVendorSlot.getDate());
                    ps.setString(++count,createUpdateVendorSlot.getStartTime());
                    ps.setString(++count,createUpdateVendorSlot.getEndTime());
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

    public int UpdateVendorSlot(CreateUpdateVendorSlot createUpdateVendorSlot){
        int updateCount  = VendorConstants.INTEGER_ZERO_CONSTANT;
        try{
            PreparedStatementCreator psc = new PreparedStatementCreator() {
                int count = VendorConstants.INTEGER_ZERO_CONSTANT;
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(VendorQuery.UpdateVendorSlot);
                    ps.setString(++count,createUpdateVendorSlot.getAmount());
                    ps.setInt(++count,createUpdateVendorSlot.getStatus());
                    ps.setString(++count,createUpdateVendorSlot.getDate());
                    ps.setString(++count,createUpdateVendorSlot.getStartTime());
                    ps.setString(++count,createUpdateVendorSlot.getEndTime());
                    ps.setInt(++count,createUpdateVendorSlot.getId());
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

    public List<GetVendorSlotByVendorId> GetVendorSlotByVendorId(int vendorId){
        List<GetVendorSlotByVendorId> data = null;
        try {
            RowMapper<GetVendorSlotByVendorId> rowMapper = new RowMapper<GetVendorSlotByVendorId>() {
                @Override
                public GetVendorSlotByVendorId mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return vendorUtil.prepareVendorSlotByIdObject(rs);
                }
            };
            data = jdbcTemplate.query(VendorQuery.GetVendorSlotByVendorId,rowMapper,vendorId);

        } catch (DataAccessException e) {
            log.error("Unable to access data " + e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error occurred"+ e.getMessage());
        }
        return data;
    }

}

