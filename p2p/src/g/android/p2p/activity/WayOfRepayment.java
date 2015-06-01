package g.android.p2p.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import g.android.p2p.model.*;
import g.android.p2p.model.BillModel.E_WayOfRepayment;

public class WayOfRepayment {
	/**
	 * ����Ͷ���������㻹��ƻ�
	 * 
	 * @param model
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByBillInfo(
			BillModel argvalue) {
		java.util.ArrayList<BillDetailModel> list = null;
		if (argvalue.WayOfRepayment == E_WayOfRepayment.�ȶϢ) {
			list = GetDetailsByEveragePrincipalAndInterest(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.�ȶ��) {
			list = GetDetailsByEveragePrincipal(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.�¸�Ϣ���ڸ���) {
			list = GetDetailsByMonthInterestEndPrincipal(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.�¸�Ϣ��������) {
			list = GetDetailsByMonthInterestSeasonCapital(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.һ���Ա�Ϣ) {
			list = GetDetailsByToEnd(argvalue);
		}

		if (list != null && list.size() > 0) {
			argvalue.BillID = java.util.UUID.randomUUID().toString();
			// Periods = string.Format("0/{0}", list[0].Periods.Split('/')[1]);
			argvalue.ReceivablePeriod = list.size();
			argvalue.EndDay = list.get(list.size() - 1).ReceivableDay;
			argvalue.YearRete = GetRound(argvalue.YearRete);
			for (int i = 0; i < list.size(); i++) {
				argvalue.ReceivablePrincipalAndInterest += list.get(i).ReceivablePrincipalAndInterest;
				list.get(i).BillID = argvalue.BillID;
				list.get(i).BillDetailID = java.util.UUID.randomUUID()
						.toString();
				list.get(i).UpdateTime = new java.util.Date();
				list.get(i).CreateTime = new java.util.Date();
			}
			argvalue.ReceivablePrincipalAndInterest = GetRound(argvalue.ReceivablePrincipalAndInterest);
		}

		return list;
	}

	/**
	 * �ȶ�𻹿�
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByEveragePrincipal(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		BillDetailModel detail = null;
		double montyMoney = billModel.TotalMoney / billModel.Deadline; // �»����
		double SurplusPayment = 0;
		for (int i = 0; i < billModel.Deadline; i++) {
			detail = new BillDetailModel();
			if (i == 0) {
				SurplusPayment = billModel.TotalMoney - montyMoney; // ʣ�౾��
				detail.ReceivableInterest = GetRound(billModel.TotalMoney
						* (billModel.YearRete / 12));
			} else {
				detail.ReceivableInterest = GetRound(SurplusPayment
						* (billModel.YearRete / 12));
				SurplusPayment = SurplusPayment - montyMoney; // ʣ�౾��
			}

			detail.ReceivablePrincipalAndInterest = new BigDecimal(montyMoney
					+ detail.ReceivableInterest).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			detail.ReceivableDay = GetDateAddMoths(billModel.BeginDay, i + 1,1);
			detail.Periods = String.format("%1$s/%2$s", i + 1,
					billModel.Deadline);

			details.add(detail);
		}
		return details;
	}

	/**
	 * �ȶϢ����
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByEveragePrincipalAndInterest(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		double tlnAcct = 0;
		tlnAcct = billModel.TotalMoney;

		BillDetailModel detail = null;
		for (int i = 0; i < billModel.Deadline; i++) {
			detail = new BillDetailModel();
			double paybaseAcct = (Math
					.pow((1 + billModel.YearRete / 12), i + 1) - Math.pow(
					(1 + billModel.YearRete / 12), i))
					/ (Math.pow((1 + billModel.YearRete / 12),
							billModel.Deadline) - 1) * billModel.TotalMoney;
			detail.ReceivableInterest = tlnAcct * billModel.YearRete / 12; // ��Ϣ
			detail.ReceivablePrincipalAndInterest = GetRound(paybaseAcct
					+ detail.ReceivableInterest);
			detail.ReceivableInterest = GetRound(detail.ReceivableInterest);
			tlnAcct = tlnAcct - paybaseAcct;

			detail.ReceivableDay = GetDateAddMoths(billModel.BeginDay, i + 1,1);
			detail.Periods = String.format("%1$s/%2$s", i + 1,
					billModel.Deadline);
			details.add(detail);
		}

		return details;
	}

	/**
	 * �»�Ϣ���ڻ���
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByMonthInterestEndPrincipal(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		//double[] depAcctbal = new double[billModel.Deadline]; // �ܻ���
		// double[] payrateAcct = new double[bill.Deadline]; /*ÿ��Ӧ����Ϣ*/

		// Java's Math.round if just one argument is used:
		double interest = GetRound(billModel.TotalMoney * billModel.YearRete
				/ 12);
		BillDetailModel detail = null;
		for (int i = 0; i < billModel.Deadline; i++) {
			detail = new BillDetailModel();
			detail.ReceivableInterest = interest;
			if ((i + 1) == billModel.Deadline) {
				detail.ReceivablePrincipalAndInterest = interest
						+ billModel.TotalMoney;
			} else {
				detail.ReceivablePrincipalAndInterest = interest;
			}

			detail.ReceivableDay = GetDateAddMoths(billModel.BeginDay, i + 1,1);
			detail.Periods = String.format("%1$s/%2$s", i + 1,
					billModel.Deadline);
			details.add(detail);
		}

		return details;
	}

	/**
	 * ���¼�Ϣ��������
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByMonthInterestSeasonCapital(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		int curMonth; // ��ǰ����
		double curMoney; // ��ǰδ������
		int seasonCount = billModel.Deadline % 3 > 0 ? billModel.Deadline / 3 + 1
				: billModel.Deadline / 3; // �ֳɼ���
		double seasonCapital = Math.round(billModel.TotalMoney / seasonCount); // ÿ��Ӧ���ı���
		double interest; // ÿ��Ӧ����Ϣ

		for (int i = 0; i < seasonCount; i++) {
			curMoney = billModel.TotalMoney - seasonCapital * i;
			interest = GetRound(curMoney * billModel.YearRete / 12);

			BillDetailModel detail = null;
			for (int j = 1; j <= 3; j++) {
				detail = new BillDetailModel();
				curMonth = i * 3 + j;
				if (curMonth < billModel.Deadline && curMonth % 3 == 0
						&& curMonth != 0) {
					detail.ReceivablePrincipalAndInterest = interest
							+ seasonCapital;
					detail.ReceivableInterest = interest;
				} else if (curMonth < billModel.Deadline) {
					detail.ReceivablePrincipalAndInterest = interest;
					detail.ReceivableInterest = interest;
				} else if (curMonth == billModel.Deadline) {
					detail.ReceivablePrincipalAndInterest = interest
							+ seasonCapital;
					detail.ReceivableInterest = interest;
				}
				detail.ReceivableDay = GetDateAddMoths(billModel.BeginDay, i * 3 + j,1);
				detail.Periods = String.format("%1$s/%2$s", (i * 3) + j,
						billModel.Deadline);
				details.add(detail);
			}
		}

		return details;
	}

	/**
	 * ���ռ�Ϣ���»�Ϣ���ڻ���
	 * 
	 * @param bill
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByDayMonthInterestSeasonCapital(
			BillModel bill) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		return details;
	}

	/**
	 * �����Ϣ����һ���Ի���Ϣ
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByToEnd(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();
		BillDetailModel detail = new BillDetailModel();
		double interest = 0;
		if (billModel.DeadlineType == 1) {
			// ��������Ϣ
			interest = GetRound(billModel.YearRete / 365 * billModel.Deadline
					* billModel.TotalMoney);
		} else {
			// ��������Ϣ
			interest = GetRound(billModel.YearRete / 12 * billModel.Deadline
					* billModel.TotalMoney);// ����Ϣ
		}

		detail.ReceivableInterest = interest;
		detail.ReceivablePrincipalAndInterest = billModel.TotalMoney + interest;
		detail.ReceivableDay = billModel.EndDay;
		detail.Periods = "1/1";
		details.add(detail);

		return details;
	}

	public static String GetDateAddMoths(String datestr, int count, int dayOrMonth) {
		
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd")
					.parse(datestr);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// ��ʽ������
		Calendar calendar = Calendar.getInstance();// ��������
		calendar.setTime(date);// ���õ�ǰ����
		if (dayOrMonth == 1)
			calendar.add(Calendar.MONTH, count);
		else
			calendar.add(Calendar.DATE, count);

		return sdf.format(calendar.getTime());
	}

	public static double GetRound(double val) {
		BigDecimal bg = new BigDecimal(val);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
