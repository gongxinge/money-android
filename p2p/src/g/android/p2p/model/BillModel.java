package g.android.p2p.model;

import java.io.Serializable;
import java.util.Date;

public class BillModel  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7964868459473942953L;

	public BillModel()// Ĭ�Ϲ��캯��
	{
		super();
	}

	// / <summary>
	// / �����
	// / </summary>
	public String BillID;

	public String getBillID() {
		return BillID;
	}

	public void setBillID(String BillID) {
		this.BillID = BillID;
	}

	// / <summary>
	// / ƽ̨����
	// / </summary>
	public String SiteName;

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String SiteName) {
		this.SiteName = SiteName;
	}

	// / <summary>
	// / ƽ̨�û���
	// / </summary>
	public String SiteUserName;

	public String getSiteUserName() {
		return SiteUserName;
	}

	public void setSiteUserName(String SiteUserName) {
		this.SiteUserName = SiteUserName;
	}

	// / <summary>
	// / ��ע
	// / </summary>
	public String Remark;

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Remark) {
		this.Remark = Remark;
	}

	// / <summary>
	// / ����ܶ�
	// / </summary>
	public double TotalMoney;

	public double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(double TotalMoney) {
		this.TotalMoney = TotalMoney;
	}

	// / <summary>
	// / ������
	// / </summary>
	public double YearRete;

	public double getYearRete() {
		return YearRete;
	}

	public void setYearRete(double YearRete) {
		this.YearRete = YearRete;
	}

	// / <summary>
	// / ����
	// / </summary>
	public int Deadline;

	public int getDeadline() {
		return Deadline;
	}

	public void setDeadline(int Deadline) {
		this.Deadline = Deadline;
	}

	// / <summary>
	// / ��������
	// / 1����
	// / 2: ��
	// / </summary>
	public int DeadlineType;

	public int getDeadlineType() {
		return DeadlineType;
	}

	public void setDeadlineType(int DeadlineType) {
		this.DeadlineType = DeadlineType;
	}

	// / <summary>
	// / ��������
	// / </summary>
	public int ReceivedPeriod;

	public int getReceivedPeriod() {
		return ReceivedPeriod;
	}

	public void setReceivedPeriod(int Periods) {
		this.ReceivedPeriod = Periods;
	}
	// / <summary>
	// / ��������
	// / </summary>
	public int ReceivablePeriod;

	public int getReceivablePeriod() {
		return ReceivablePeriod;
	}

	public void setReceivablePeriod(int Periods) {
		this.ReceivablePeriod = Periods;
	}

	// / <summary>
	// / Ӧ�ձ�Ϣ
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
	// / ���ձ�Ϣ
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
	// / ���ʽ
	// / </summary>
	public E_WayOfRepayment WayOfRepayment;

	public E_WayOfRepayment getWayOfRepayment() {
		return WayOfRepayment;
	}

	public void setWayOfRepayment(E_WayOfRepayment WayOfRepayment) {
		this.WayOfRepayment = WayOfRepayment;
	}

	// / <summary>
	// / �������
	// / </summary>
	public double Reward;

	public double getReward() {
		return Reward;
	}

	public void setReward(double Reward) {
		this.Reward = Reward;
	}

	// / <summary>
	// / ��������
	// / </summary>
	public String BeginDay;

	public String getBeginDay() {
		return BeginDay;
	}

	public void setBeginDay(String BeginDay) {
		this.BeginDay = BeginDay;
	}

	// / <summary>
	// / ��������
	// / </summary>
	public String EndDay;

	public String getEndDay() {
		return EndDay;
	}

	public void setEndDay(String EndDay) {
		this.EndDay = EndDay;
	}

	// / <summary>
	// / ���ڴ���
	// / </summary>
	public int YuQiCount;

	public int getYuQiCount() {
		return YuQiCount;
	}

	public void setYuQiCount(int YuQiCount) {
		this.YuQiCount = YuQiCount;
	}

	// / <summary>
	// / ɾ����־ 0:δɾ�� 1:��ɾ��
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
	// / ����ʱ��
	// / </summary>
	public Date UpdateTime;

	public Date getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(Date UpdateTime) {
		this.UpdateTime = UpdateTime;
	}

	// / <summary>
	// / ����ʱ��
	// / </summary>
	public Date CreateTime;

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date CreateTime) {
		this.CreateTime = CreateTime;
	}

	// / <summary>
	// / ���ʽ
	// / 1:�ȶϢ
	// / 2:�ȶ��
	// / 3:�¸�Ϣ���ڸ���
	// / 4:�¸�Ϣ��������
	// / </summary>
	public enum E_WayOfRepayment {
		�ȶϢ,
		�ȶ��,
		�¸�Ϣ���ڸ���,
		�¸�Ϣ��������,
		һ���Ա�Ϣ
	}
}