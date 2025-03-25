package com.chargr.util.query;

public class VendorQuery {
	
	public static final String LoginVendor = "select count(*) count from vendor where email = ? and password = ?;";

    public static final String UpdateVendorStartEndTime = "update vendor set start_time =? ,end_time =? where id = ?;";

    public static final String validateVendorEmail = "select email from vendor where email = ?";

    public static final String validateVendorMobile = "select mobile from vendor where mobile = ?";

    public static final String CreateVendor = "insert into vendor (role_id, business_id, name, shop_name, mobile, email, password, location, start_time, end_time, status) values (?,?,?,?,?,?,?,?,?,?,?);";

    public static final String UpdateVendor = "update vendor set business_id = ?, name = ? , shop_name = ?, mobile = ?, email = ?, password = ?, location = ?, start_time = ?, end_time = ?, status = ? where id = ? ;";

    public static final String DeleteVendor = "update vendor set status=0 where id = ?;";

    public static final String GetVendorById = "select id, role_id, business_id, name, shop_name, mobile, email, password, location, start_time, end_time, status from vendor where id = ? ;";

    public static final String GetVendorByEmail = "select id, role_id, business_id, name, shop_name, mobile, email, password, location, start_time, end_time, status from vendor where email = ? ;";

    public static final String GetAllVendors = "select v.id,v.role_id,v.business_id,v.name,v.email,v.mobile,v.location,v.start_time default_start_time,v.end_time default_end_time,v.status,\n" +
            "vs.start_time extra_start_time,vs.end_time extra_end_time from vendor v\n" +
            "left join vendor_slot vs on vs.vendor_id = v.id\n" +
            "where v.status = 1;";

    public static final String CreateBankDetails = "insert into bank_details (vendor_id, account_number, account_holder_name, branch_name, ifsc_code) values (?,?,?,?,?);";

    public static final String UpdateBankDetails = "update bank_details set account_number = ?, account_holder_name = ? , branch_name = ?, ifsc_code = ? where id = ? ;";

    public static final String GetBankDetailsByVendorId = "select id, vendor_id, account_number, account_holder_name, branch_name, ifsc_code from bank_details where vendor_id = ? ;";

    public static final String DeleteVendorBank = "delete from bank_details where id = ?;";

    public static final String CreateVendorSlot = "insert into vendor_slot (vendor_id, amount, status, date, start_time, end_time) values (?,?,?,?,?,?);";

    public static final String UpdateVendorSlot = "update vendor_slot set amount = ?, status = ? , date = ?, start_time = ?,end_time = ?  where id = ? ;";

    public static final String GetVendorSlotByVendorId = "select id, amount, status, vendor_id, date, start_time, end_time from vendor_slot where vendor_id = ? ;";

}
