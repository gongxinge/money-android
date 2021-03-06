package g.android.p2p.model;

import java.io.Serializable;
import java.util.Date;

public class BillModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7964868459473942953L;

	public BillModel()// 默认构造函数
	{
		super();
	}

	// / <summary>
	// / 借款编号
	// / </summary>
	public String BillID;

	public String getBillID() {
		return BillID;
	}

	public void setBillID(String BillID) {
		this.BillID = BillID;
	}

	// / <summary>
	// / 平台名称
	// / </summary>
	public String SiteName;

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String SiteName) {
		this.SiteName = SiteName;
	}

	// / <summary>
	// / 平台用户名
	// / </summary>
	public String SiteUserName;

	public String getSiteUserName() {
		return SiteUserName;
	}

	public void setSiteUserName(String SiteUserName) {
		this.SiteUserName = SiteUserName;
	}

	// / <summary>
	// / 备注
	// / </summary>
	public String Remark;

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Remark) {
		this.Remark = Remark;
	}

	// / <summary>
	// / 借款总额
	// / </summary>
	public double TotalMoney;

	public double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(double TotalMoney) {
		this.TotalMoney = TotalMoney;
	}

	// / <summary>
	// / 年利率
	// / </summary>
	public double YearRete;

	public double getYearRete() {
		return YearRete;
	}

	public void setYearRete(double YearRete) {
		this.YearRete = YearRete;
	}

	// / <summary>
	// / 期限
	// / </summary>
	public int Deadline;

	public int getDeadline() {
		return Deadline;
	}

	public void setDeadline(int Deadline) {
		this.Deadline = Deadline;
	}

	// / <summary>
	// / 期限类型
	// / 1：天
	// / 2: 月
	// / </summary>
	public int DeadlineType;

	public int getDeadlineType() {
		return DeadlineType;
	}

	public void setDeadlineType(int DeadlineType) {
		this.DeadlineType = DeadlineType;
	}

	// / <summary>
	// / 已收期数
	// / </summary>
	public int ReceivedPeriod;

	public int getReceivedPeriod() {
		return ReceivedPeriod;
	}

	public void setReceivedPeriod(int Periods) {
		this.ReceivedPeriod = Periods;
	}
	// / <summary>
	// / 已收期数
	// / </summary>
	public int ReceivablePeriod;

	public int getReceivablePeriod() {
		return ReceivablePeriod;
	}

	public void setReceivablePeriod(int Periods) {
		this.ReceivablePeriod = Periods;
	}

	// / <summary>
	// / 应收本息
	// / </summary>
	public double ReceivablePrincipalAndInterest;

	public double getReceivablePrincipalAndInterest() {
		return ReceivablePrincipalAndInterest;
	}

	public void setReceivablePrincipalAndInterest(
			double ReceivablePrincipalAndInterest) {
		this.ReceivablePrincipalAndInterest = ReceivablePrincipalAndInterest;
	}

	// / <summary>
	// / 已收本息
	// / </summary>
	public double ReceivedPrincipalAndInterest;

	public double getReceivedPrincipalAndInterest() {
		return ReceivedPrincipalAndInterest;
	}

	public void setReceivedPrincipalAndInterest(
			double ReceivedPrincipalAndInterest) {
		this.ReceivedPrincipalAndInterest = ReceivedPrincipalAndInterest;
	}

	// / <summary>
	// / 还款方式
	// / </summary>
	public E_WayOfRepayment WayOfRepayment;

	public E_WayOfRepayment getWayOfRepayment() {
		return WayOfRepayment;
	}

	public void setWayOfRepayment(E_WayOfRepayment WayOfRepayment) {
		this.WayOfRepayment = WayOfRepayment;
	}

	// / <summary>
	// / 奖励金额
	// / </summary>
	public double Reward;

	public double getReward() {
		return Reward;
	}

	public void setReward(double Reward) {
		this.Reward = Reward;
	}

	// / <summary>
	// / 出借日期
	// / </summary>
	public String BeginDay;

	public String getBeginDay() {
		return BeginDay;
	}

	public void setBeginDay(String BeginDay) {
		this.BeginDay = BeginDay;
	}

	// / <summary>
	// / 到期日期
	// / </summary>
	public String EndDay;

	public String getEndDay() {
		return EndDay;
	}

	public void setEndDay(String EndDay) {
		this.EndDay = EndDay;
	}

	// / <summary>
	// / 逾期次数
	// / </summary>
	public int YuQiCount;

	public int getYuQiCount() {
		return YuQiCount;
	}

	public void setYuQiCount(int YuQiCount) {
		this.YuQiCount = YuQiCount;
	}

	// / <summary>
	// / 删除标志 0:未删除 1:已删除
	// / </summary>
	public int Deleted;

	public int getDeleted() {
		return Deleted;
	}

	public void setDeleted(int Deleted) {
		this.Deleted = Deleted;
	}

	// / <summary>
	// /
	// / </summary>
	public int Flag;

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int Flag) {
		this.Flag = Flag;
	}

	// / <summary>
	// / 更新时间
	// / </summary>
	public Date UpdateTime;

	public Date getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(Date UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	// / <summary>
	// / 创建时间
	// / </summary>
	public Date CreateTime;

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	// / <summary>
	// / 还款方式
	// / 1:等额本息
	// / 2:等额本金
	// / 3:月付息到期付本
	// / 4:月付息按季付本
	// / </summary>
	public enum E_WayOfRepayment {
		等额本息,
		等额本金,
		月付息到期付本,
		月付息按季付本,
		一次性本息
	}
}
