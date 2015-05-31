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

public class BillDetailList extends Activity {
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billdetaillist);
		//ShowDetails();

		Button btnBack = (Button) findViewById(R.id.btnBack);
		lv = (ListView) findViewById(R.id.listView1);

		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// setResult(Activity.RESULT_CANCELED, intent);
				finish();
			}
		});

		/*
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, Object> data = ((HashMap<String, Object>) lv
						.getItemAtPosition(arg2));
				ChangeState(data);
			}
		});
		*/
	}

	private void ShowDetails() {
		BillDetailDAO dao = new BillDetailDAO(this);
		 StringBuilder builder = new  StringBuilder();
		 builder.append(String.format(" d.ReceivableDay >='%1$s'",getFirstDayOfMonth()));
		 builder.append(String.format(" and d.ReceivableDay <='%1$s'", getLastDayOfMonth()));
		List<BillDetailModel> details = dao.getDetails(builder.toString());

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (BillDetailModel detail : details) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("ReceivedPeriod", detail.ReceivedPeriod);
			item.put("BillDetailID", detail.BillDetailID);
			item.put("ReceivablePeriod", detail.ReceivablePeriod);
			item.put("BillID", detail.BillID);
			item.put("state", detail.Flag == 0 ? "����" : "����");
			item.put("totalmoney", detail.TotalMoney);
			item.put("sitename", detail.SiteName);
			item.put("siteusername", detail.SiteUserName);
			item.put("remark", detail.Remark);
			item.put("periods", "����:" + detail.Periods);
			item.put("recdate", detail.ReceivableDay);
			item.put("recall", detail.ReceivablePrincipalAndInterest);
			item.put("reclixi", "Ӧ����Ϣ:" + detail.ReceivableInterest);
			item.put("recbenjin", String.format("Ӧ�ձ���:%1$s", WayOfRepayment
					.GetRound(detail.ReceivablePrincipalAndInterest
							- detail.ReceivableInterest)));
			data.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.billdetaillistitem,
				new String[] { "state", "totalmoney", "sitename",
						"siteusername", "remark", "periods", "recdate",
						"recall", "reclixi", "recbenjin" }, new int[] {
						R.id.tvState, R.id.tvTotalMoney, R.id.tvSiteName,
						R.id.tvSiteUserName, R.id.tvRemark, R.id.tvPeriods,
						R.id.tvRecDate, R.id.tvRecAll, R.id.tvRecLiXi,
						R.id.tvRecBenJin });

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

		String showInfo = state == "����" ? "�տ�" : "��Ϊ����";
		final String[] items = new String[] { showInfo };
		android.app.AlertDialog.Builder builder = new AlertDialog.Builder(
				BillDetailList.this);
		builder.setTitle("���Ļ���״̬"); // ���öԻ���ı���
		// �����б���
		builder.setItems(items, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (state == "����") {
					ShouKuan(ReceivedPeriod, ReceivablePeriod, BillDetailID,
							BillID);
					obj.put("state", "����");
				} else {
					QuXiaoShouKuan(ReceivedPeriod, ReceivablePeriod,
							BillDetailID, BillID);
					obj.put("state", "����");
				}

				SimpleAdapter sAdapter = (SimpleAdapter) lv.getAdapter();
				sAdapter.notifyDataSetChanged();
				// Toast.makeText(BillDetailList.this, "��ѡ����" +
				// items[which],Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show(); // �����Ի�����ʾ
	}

	private void QuXiaoShouKuan(int ReceivedPeriod, int ReceivablePeriod,
			String BillDetailID, String BillID) {
		int flag = 0;
		ReceivedPeriod = ReceivedPeriod - 1; // ����������һ

		if (ReceivedPeriod < 0)
			ReceivedPeriod = 0;

		if (ReceivedPeriod == ReceivablePeriod) {
			// ���һ���տ�
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

		ReceivedPeriod = ReceivedPeriod + 1; // ����������һ
		if (ReceivedPeriod > ReceivablePeriod)
			ReceivedPeriod = ReceivablePeriod;

		if (ReceivedPeriod == ReceivablePeriod) {
			// ���һ���տ�
			flag = 1;
		}

		BillInfoDAO dao = new BillInfoDAO(this);
		dao.ExecSQL(ReceivedPeriod, flag, BillID);

		BillDetailDAO detailDAO = new BillDetailDAO(this);
		detailDAO.ExecSQL(1, BillDetailID);

	}

	/**
	 * �õ����µ�һ�������
	 * 
	 * @Methods Name getFirstDayOfMonth
	 * @return Date
	 */
	public String getFirstDayOfMonth() {
		Calendar cDay = Calendar.getInstance();
		cDay.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(cDay.getTime());
	}

	/**
	 * �õ��������һ�������
	 * 
	 * @Methods Name getLastDayOfMonth
	 * @return Date
	 */
	public String getLastDayOfMonth() {
		Calendar cDay = Calendar.getInstance();
		cDay.set(Calendar.DAY_OF_MONTH,
				cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(cDay.getTime());
	}

	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");
}