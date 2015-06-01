package g.android.p2p.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import g.android.p2p.dao.BillDetailDAO;
import g.android.p2p.dao.BillInfoDAO;
import g.android.p2p.model.BillDetailModel;
import g.android.p2p.model.BillModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class BillDetailView extends Activity {
	BillModel billModel;
	List<BillDetailModel> details;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.billdetailview);

		final Intent intent = getIntent();
		Bundle bundel = intent.getExtras();
		DataBind(bundel);

		Button btnBack = (Button) findViewById(R.id.btnBack);
		Button btnSave = (Button) findViewById(R.id.btnSave);

		btnBack.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(Activity.RESULT_CANCELED, intent);
				finish();
			}
		});

		btnSave.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SaveData();
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});

	}

	private void SaveData() {
		BillInfoDAO billDAO = new BillInfoDAO(this);
		BillDetailDAO detailDAO = new BillDetailDAO(this);
		
		billDAO.add(billModel);
		detailDAO.add(details);
	}

	private void DataBind(Bundle bundel) {
		billModel = GetBillModel(bundel);
		details = WayOfRepayment.GetDetailsByBillInfo(billModel);

		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (BillDetailModel detail : details) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("periods", detail.Periods);
			item.put("recdate", detail.ReceivableDay);
			item.put("recall", detail.ReceivablePrincipalAndInterest);
			item.put("reclixi", detail.ReceivableInterest);
			item.put("recbenjin", detail.ReceivablePrincipalAndInterest
					- detail.ReceivableInterest);
			data.add(item);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.billdetailviewitem, new String[] { "periods",
						"recdate", "recall", "reclixi", "recbenjin" },
				new int[] { R.id.tvPeriods, R.id.tvRecDate, R.id.tvRecAll,
						R.id.tvRecLiXi, R.id.tvRecBenJin });

		ListView listview = (ListView) findViewById(R.id.listView1);
		listview.setAdapter(adapter);
	}

	private BillModel GetBillModel(Bundle bundle) {
		BillModel billModel = new BillModel();

		billModel.SiteName = bundle.getString("SiteName");
		billModel.SiteUserName = bundle.getString("SiteUserName");
		billModel.Remark = bundle.getString("Remark");
		billModel.BeginDay = bundle.getString("BeginDay");
		String tmp = bundle.getString("Deadline");
		billModel.Deadline = Integer.parseInt(tmp);
		billModel.DeadlineType = bundle.getInt("DeadlineType");
		billModel.TotalMoney = bundle.getDouble("TotalMoney");
		billModel.YearRete = bundle.getFloat("YearRete");
		billModel.WayOfRepayment = g.android.p2p.model.BillModel.E_WayOfRepayment
				.values()[bundle.getInt("WayOfRepayment")];

		if (billModel.DeadlineType == 1)
			billModel.EndDay = WayOfRepayment.GetDateAddMoths(
					billModel.BeginDay, billModel.Deadline, 0);
		else
			billModel.EndDay = WayOfRepayment.GetDateAddMoths(
					billModel.BeginDay, billModel.Deadline, 1);

		billModel.CreateTime = Calendar.getInstance().getTime();
		billModel.UpdateTime = billModel.CreateTime;

		return billModel;
	}
}
