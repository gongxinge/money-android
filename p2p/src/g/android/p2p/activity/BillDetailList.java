package g.android.p2p.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import g.android.p2p.dao.BillDetailDAO;
import g.android.p2p.dao.BillInfoDAO;
import g.android.p2p.model.BillDetailModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BillDetailList extends Activity {
	ListView lv;
	int AddMonth = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billdetaillist);
		ShowDetails();

		lv = (ListView) findViewById(R.id.listView1);
		Button btnPrev = (Button) findViewById(R.id.btnPrev);
		Button btnNext = (Button) findViewById(R.id.btnNext);

		btnPrev.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddMonth--;
				ShowDetails();
			}
		});

		btnNext.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				AddMonth++;
				ShowDetails();
			}
		});

		/*
		 * lv.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 * arg2, long arg3) { HashMap<String, Object> data = ((HashMap<String,
		 * Object>) lv .getItemAtPosition(arg2)); ChangeState(data); } });
		 */
	}

	private void ShowDetails() {
		BillDetailDAO dao = new BillDetailDAO(this);
		StringBuilder builder = new StringBuilder();
		builder.append(String.format(" d.ReceivableDay >='%1$s'",
				getFirstDayOfMonth()));
		builder.append(String.format(" and d.ReceivableDay <='%1$s'",
				getLastDayOfMonth()));
		List<BillDetailModel> details = dao.getDetails(builder.toString());

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		float benxi = 0;
		float lixi = 0;
		for (BillDetailModel detail : details) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("ReceivedPeriod", detail.ReceivedPeriod);
			item.put("BillDetailID", detail.BillDetailID);
			item.put("ReceivablePeriod", detail.ReceivablePeriod);
			item.put("BillID", detail.BillID);
			item.put("state", detail.Flag == 0 ? "待收" : "已收");
			item.put("totalmoney", detail.TotalMoney);
			item.put("sitename", detail.SiteName);
			item.put("siteusername", detail.SiteUserName);
			item.put("remark", detail.Remark);
			item.put("periods", "期数:" + detail.Periods);
			item.put("recdate", detail.ReceivableDay);
			item.put("recall", detail.ReceivablePrincipalAndInterest);
			item.put("reclixi", "应收利息:" + detail.ReceivableInterest);
			item.put("recbenjin", String.format("应收本金:%1$s", WayOfRepayment
					.GetRound(detail.ReceivablePrincipalAndInterest
							- detail.ReceivableInterest)));
			benxi += detail.ReceivablePrincipalAndInterest;
			lixi += detail.ReceivableInterest;
			data.add(item);
		}
		TextView txtMonthInfo = (TextView) findViewById(R.id.txtMonthInfo);

		((TextView) findViewById(R.id.txtMonthStr)).setText(String.format(
				"%s收益汇总", getCurDayStr()));
		txtMonthInfo.setText(String.format("本息：%s\t\t利息：%s", (int) benxi,
				(int) lixi));

		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.billdetaillistitem, new String[] { "state",
						"sitename", "siteusername", "remark", "periods",
						"recdate", "recall", "reclixi", "recbenjin" },
				new int[] { R.id.tvState, R.id.tvSiteName, R.id.tvSiteUserName,
						R.id.tvRemark, R.id.tvPeriods, R.id.tvRecDate,
						R.id.tvRecAll, R.id.tvRecLiXi, R.id.tvRecBenJin });

		ListView listview = (ListView) findViewById(R.id.listView1);
		listview.setAdapter(adapter);
	}

	String state;
	int ReceivedPeriod;
	int ReceivablePeriod;
	String BillDetailID;
	String BillID;

	private void ChangeState(final HashMap<String, Object> obj) {
		state = obj.get("state").toString();
		ReceivedPeriod = Integer.parseInt(obj.get("ReceivedPeriod").toString());
		ReceivablePeriod = Integer.parseInt(obj.get("ReceivablePeriod")
				.toString());
		BillDetailID = obj.get("BillDetailID").toString();
		BillID = obj.get("BillID").toString();

		String showInfo = state == "待收" ? "收款" : "设为待收";
		final String[] items = new String[] { showInfo };
		android.app.AlertDialog.Builder builder = new AlertDialog.Builder(
				BillDetailList.this);
		builder.setTitle("更改还款状态"); // 设置对话框的标题
		// 添加列表项
		builder.setItems(items, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if (state == "待收") {
					ShouKuan(ReceivedPeriod, ReceivablePeriod, BillDetailID,
							BillID);
					obj.put("state", "已收");
				} else {
					QuXiaoShouKuan(ReceivedPeriod, ReceivablePeriod,
							BillDetailID, BillID);
					obj.put("state", "待收");
				}

				SimpleAdapter sAdapter = (SimpleAdapter) lv.getAdapter();
				sAdapter.notifyDataSetChanged();
				// Toast.makeText(BillDetailList.this, "您选择了" +
				// items[which],Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show(); // 创建对话框并显示
	}

	private void QuXiaoShouKuan(int ReceivedPeriod, int ReceivablePeriod,
			String BillDetailID, String BillID) {
		int flag = 0;
		ReceivedPeriod = ReceivedPeriod - 1; // 已收期数减一

		if (ReceivedPeriod < 0)
			ReceivedPeriod = 0;

		if (ReceivedPeriod == ReceivablePeriod) {
			// 最后一期收款
			flag = 1;
		}

		BillInfoDAO dao = new BillInfoDAO(this);
		dao.ExecSQL(ReceivedPeriod, flag, BillID);

		BillDetailDAO detailDAO = new BillDetailDAO(this);
		detailDAO.ExecSQL(0, BillDetailID);
	}

	private void ShouKuan(int ReceivedPeriod, int ReceivablePeriod,
			String BillDetailID, String BillID) {
		int flag = 0;

		ReceivedPeriod = ReceivedPeriod + 1; // 已收期数加一
		if (ReceivedPeriod > ReceivablePeriod)
			ReceivedPeriod = ReceivablePeriod;

		if (ReceivedPeriod == ReceivablePeriod) {
			// 最后一期收款
			flag = 1;
		}

		BillInfoDAO dao = new BillInfoDAO(this);
		dao.ExecSQL(ReceivedPeriod, flag, BillID);

		BillDetailDAO detailDAO = new BillDetailDAO(this);
		detailDAO.ExecSQL(1, BillDetailID);

	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月");

	/**
	 * 得到本月第一天的日期
	 * 
	 * @Methods Name getFirstDayOfMonth
	 * @return Date
	 */
	public String getFirstDayOfMonth() {
		Calendar cDay = Calendar.getInstance();
		cDay.set(Calendar.MONTH, cDay.get(Calendar.MONTH) + AddMonth);
		cDay.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cDay.getTime());
	}

	/**
	 * 得到本月最后一天的日期
	 * 
	 * @Methods Name getLastDayOfMonth
	 * @return Date
	 */
	public String getLastDayOfMonth() {
		Calendar cDay = Calendar.getInstance();
		cDay.set(Calendar.MONTH, cDay.get(Calendar.MONTH) + AddMonth);
		cDay.set(Calendar.DAY_OF_MONTH,
				cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(cDay.getTime());
	}

	public String getCurDayStr() {
		Calendar cDay = Calendar.getInstance();
		cDay.set(Calendar.MONTH, cDay.get(Calendar.MONTH) + AddMonth);
		return sdf2.format(cDay.getTime());
	}

}
