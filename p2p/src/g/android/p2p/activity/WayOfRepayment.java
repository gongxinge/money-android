package g.android.p2p.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import g.android.p2p.model.*;
import g.android.p2p.model.BillModel.E_WayOfRepayment;

public class WayOfRepayment {
	/**
	 * 根据投资条件计算还款计划
	 * 
	 * @param model
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByBillInfo(
			BillModel argvalue) {
		java.util.ArrayList<BillDetailModel> list = null;
		if (argvalue.WayOfRepayment == E_WayOfRepayment.等额本息) {
			list = GetDetailsByEveragePrincipalAndInterest(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.等额本金) {
			list = GetDetailsByEveragePrincipal(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.月付息到期付本) {
			list = GetDetailsByMonthInterestEndPrincipal(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.月付息按季付本) {
			list = GetDetailsByMonthInterestSeasonCapital(argvalue);
		} else if (argvalue.WayOfRepayment == E_WayOfRepayment.一次性本息) {
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
	 * 等额本金还款
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByEveragePrincipal(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		BillDetailModel detail = null;
		double montyMoney = billModel.TotalMoney / billModel.Deadline; // 月还款本金
		double SurplusPayment = 0;
		for (int i = 0; i < billModel.Deadline; i++) {
			detail = new BillDetailModel();
			if (i == 0) {
				SurplusPayment = billModel.TotalMoney - montyMoney; // 剩余本金
				detail.ReceivableInterest = GetRound(billModel.TotalMoney
						* (billModel.YearRete / 12));
			} else {
				detail.ReceivableInterest = GetRound(SurplusPayment
						* (billModel.YearRete / 12));
				SurplusPayment = SurplusPayment - montyMoney; // 剩余本金
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
	 * 等额本息还款
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
			detail.ReceivableInterest = tlnAcct * billModel.YearRete / 12; // 利息
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
	 * 月还息到期还本
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByMonthInterestEndPrincipal(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		//double[] depAcctbal = new double[billModel.Deadline]; // 总还款
		// double[] payrateAcct = new double[bill.Deadline]; /*每月应还利息*/

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
	 * 按月计息按季还款
	 * 
	 * @param billModel
	 * @return
	 */
	public static java.util.ArrayList<BillDetailModel> GetDetailsByMonthInterestSeasonCapital(
			BillModel billModel) {
		java.util.ArrayList<BillDetailModel> details = new java.util.ArrayList<BillDetailModel>();

		int curMonth; // 当前期数
		double curMoney; // 当前未还本金
		int seasonCount = billModel.Deadline % 3 > 0 ? billModel.Deadline / 3 + 1
				: billModel.Deadline / 3; // 分成几季
		double seasonCapital = Math.round(billModel.TotalMoney / seasonCount); // 每季应还的本金
		double interest; // 每月应还利息

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
	 * 按日计息按月还息到期还本
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
	 * 按天计息到期一次性还本息
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
			// 按天总利息
			interest = GetRound(billModel.YearRete / 365 * billModel.Deadline
					* billModel.TotalMoney);
		} else {
			// 按月总利息
			interest = GetRound(billModel.YearRete / 12 * billModel.Deadline
					* billModel.TotalMoney);// 总利息
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
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
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
