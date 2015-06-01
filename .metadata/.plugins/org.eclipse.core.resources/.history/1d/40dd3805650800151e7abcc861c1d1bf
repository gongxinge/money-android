package g.android.p2p.activity;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class AddBillInfo extends Activity {

	EditText etSiteName;
	EditText etSiteUserName;
	EditText etTotalMoney;
	EditText etYearRate;
	EditText etDeadline;
	RadioButton rbMonth;
	EditText etBeginDay;
	Spinner spWayOfPay;
	EditText etRemark;
	Button btnBack;
	Button btnDetailView;
	Calendar c = null;
	Intent intent ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbillinfo);

		btnBack = (Button) findViewById(R.id.btnBack);
		btnDetailView = (Button) findViewById(R.id.btnDetailView);
		spWayOfPay = (Spinner) findViewById(R.id.spinner1);
		etSiteName = (EditText) findViewById(R.id.etSiteName);
		etSiteUserName = (EditText) findViewById(R.id.etSiteUsername);
		etTotalMoney = (EditText) findViewById(R.id.etTotalMoney);
		etYearRate = (EditText) findViewById(R.id.etYearRate);
		etDeadline = (EditText) findViewById(R.id.etDeadline);
		rbMonth = (RadioButton) findViewById(R.id.rbMonth);
		etBeginDay = (EditText) findViewById(R.id.etBeginDay);
		etRemark = (EditText) findViewById(R.id.etRemark);

		String[] ctype = new String[] { "等额本息", "等额本金", "月付息到期付本", "月付息按季付本",
				"一次性本息" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, ctype);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 为适配器设置列表框下拉时的选项样式
		spWayOfPay.setAdapter(adapter); // 将适配器与选择列表框关联

		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 返回
				setResult(Activity.RESULT_CANCELED, intent);
				finish();
			}
		});

		btnDetailView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// /查看收款明细
				intent = new Intent();
				intent.setClass(AddBillInfo.this, BillDetailView.class);
				Bundle bundel = new Bundle();
				bundel.putString("SiteName", etSiteName.getText().toString());
				bundel.putString("SiteUserName", etSiteUserName.getText()
						.toString());
				bundel.putString("Remark", etRemark.getText().toString());
				bundel.putString("BeginDay", etBeginDay.getText().toString());
				bundel.putString("Deadline", etDeadline.getText().toString());
				bundel.putInt("DeadlineType", rbMonth.isChecked() ? 2 : 1);// 按月：2
																			// 按天:1

				bundel.putDouble("TotalMoney",
						Double.parseDouble(etTotalMoney.getText().toString()));
				bundel.putFloat("YearRete",
						Float.parseFloat(etYearRate.getText().toString()) / 100);
				bundel.putInt("WayOfRepayment",
						spWayOfPay.getSelectedItemPosition());
				intent.putExtras(bundel);
				startActivityForResult(intent, 0);
				//startActivity(intent);
			}
		});

		etBeginDay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(0);
			}
		});
	}

	/**
	 * 创建日期及时间选择对话框
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		c = Calendar.getInstance();
		dialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					public void onDateSet(DatePicker dp, int year, int month,
							int dayOfMonth) {
						etBeginDay.setText(year + "-" + (month + 1) + "-"
								+ dayOfMonth);
					}
				}, c.get(Calendar.YEAR), // 传入年份
				c.get(Calendar.MONTH), // 传入月份
				c.get(Calendar.DAY_OF_MONTH) // 传入天数
		);
		return dialog;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			setResult(Activity.RESULT_OK, intent);
			finish();
		}
	}
}
