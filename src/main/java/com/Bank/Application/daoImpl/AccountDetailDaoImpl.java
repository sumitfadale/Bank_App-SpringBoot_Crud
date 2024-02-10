package com.Bank.Application.daoImpl;

import java.sql.Types;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.Bank.Application.dao.AccountDetailDao;
import com.Bank.Application.pojo.AccountDetails;
import com.Bank.Application.utility.DBConstants;
@Repository
public class AccountDetailDaoImpl implements AccountDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// get callable stmt
	public SimpleJdbcCall getSimpleJdbcCall(String databaseUser, String functionName) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		// set db user name
		simpleJdbcCall.withCatalogName(databaseUser);
		// set function name
		simpleJdbcCall.withFunctionName(functionName);
		return simpleJdbcCall;
	}

	@Override
	public String saveAccount(AccountDetails account) {
		// TODO Auto-generated method stub
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(DBConstants.DATABASE_USER,
				DBConstants.USP_ACCOUNT_CONFIG_ADD);

		simpleJdbcCall.declareParameters(new SqlOutParameter("o_error_code", Types.INTEGER),
				new SqlParameter("i_account_number", Types.VARCHAR),
				new SqlParameter("i_bank_holder_name", Types.VARCHAR),
				new SqlParameter("i_gender", Types.VARCHAR),
				new SqlParameter("i_pancard_number", Types.VARCHAR),
				new SqlParameter("i_mobile_number", Types.VARCHAR),
				new SqlParameter("i_branch", Types.VARCHAR),
				new SqlParameter("i_balance", Types.INTEGER));

		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("i_account_number", account.getAccountNumber())
				.addValue("i_bank_holder_name", account.getBankHolderName())
				.addValue("i_gender",account.getGender())
				.addValue("i_pancard_number", account.getPanCardNumber())
				.addValue("i_mobile_number", account.getMobileNumber())
				.addValue("i_branch", account.getBranch())
				.addValue("i_balance", account.getBalance());

		// turn off processing parameter metadata information
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();

		// excute function
		Map<String, Object> out = simpleJdbcCall.execute(paramSource);

		// fetch cursor data if exist
		if (out != null) {
			int error_code = (int) out.get("o_error_code");
			if (error_code == 0)
				return "Added Sucessfully";
		}
		return "fail";
	}

	@Override
	public AccountDetails updateAccount(AccountDetails account) {
		// TODO Auto-generated method stub
		SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(DBConstants.DATABASE_USER,
				DBConstants.USP_ACCOUNT_CONFIG_UPADATE);

		simpleJdbcCall.declareParameters(new SqlOutParameter("o_error_code", Types.INTEGER),
				new SqlParameter("i_account_id", Types.INTEGER),
				new SqlParameter("i_account_number", Types.VARCHAR),
				new SqlParameter("i_bank_holder_name", Types.VARCHAR),
				new SqlParameter("i_gender", Types.VARCHAR),
				new SqlParameter("i_pancard_number", Types.VARCHAR),
				new SqlParameter("i_mobile_number", Types.VARCHAR),
				new SqlParameter("i_branch", Types.VARCHAR),
				new SqlParameter("i_balance", Types.INTEGER));

		SqlParameterSource paramSource = new MapSqlParameterSource()
				.addValue("i_account_id", account.getId())
				.addValue("i_account_number", account.getAccountNumber())
				.addValue("i_bank_holder_name", account.getBankHolderName())
				.addValue("i_gender",account.getGender())
				.addValue("i_pancard_number", account.getPanCardNumber())
				.addValue("i_mobile_number", account.getMobileNumber())
				.addValue("i_branch", account.getBranch())
				.addValue("i_balance", account.getBalance());

		// turn off processing parameter metadata information
		simpleJdbcCall.withoutProcedureColumnMetaDataAccess();

		// excute function
		Map<String, Object> out = simpleJdbcCall.execute(paramSource);
		return account;
	}

	@Override
	public String deleteAccount(Long id) {
		// TODO Auto-generated method stub
		
			AccountDetails account = new AccountDetails();
			account.setId(id);
			SimpleJdbcCall simpleJdbcCall = getSimpleJdbcCall(DBConstants.DATABASE_USER,
					DBConstants.USP_ACCOUNT_CONFIG_DELETE);

			 simpleJdbcCall.declareParameters(
				        new SqlOutParameter("o_error_code", Types.INTEGER),  // OUT parameter
				        new SqlParameter("i_account_id", Types.INTEGER)      // IN parameter
				    );

			SqlParameterSource paramSource = new MapSqlParameterSource()
					.addValue("i_account_id", account.getId());
			Map<String, Object> out = simpleJdbcCall.execute(paramSource);
			
		
			if (out != null) {
				int error_code = (int) out.get("o_error_code");
				if (error_code == 5)
					return "Deleted Sucessfully";
			}
			return "fail";
	}
}
