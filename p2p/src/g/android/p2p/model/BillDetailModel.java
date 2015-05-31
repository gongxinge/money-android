package g.android.p2p.model;

import java.io.Serializable;
import java.util.Date;

public class BillDetailModel  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7347086996626173753L;
	// / <summary>
	// / ��ϸ���
	// / </summary>
	public String BillDetailID;

	public String getBillDetailID() {
		return BillDetailID;
	}

	public void setBillDetailID(String BillDetailID) {
		this.BillDetailID = BillDetailID;
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
	// / ��ǰ����
	// / �磺1/12
	// / </summary>
	public String Periods;

	public String getPeriods() {
		return Periods;
	}

	public void setPeriods(String Periods) {
		this.Periods = Periods;
	}

	// / <summary>
	// / Ӧ������
	// / </summary>
	public String ReceivableDay;

	public String getReceivableDay() {
		return ReceivableDay;
	}

	public void setReceivableDay(String ReceivableDay) {
		this.ReceivableDay = ReceivableDay;
	}

	// / <summary>
	// / ʵ���տ�����
	// / </summary>
	public String ReceivedDay;

	public String getReceivedDay() {
		return ReceivedDay;
	}

	public void setReceivedDay(String ReceivedDay) {
		this.ReceivedDay = ReceivedDay;
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
	// / Ӧ����Ϣ
	// / </summary>
	public double ReceivableInterest;

	public double getReceivableInterest() {
		return ReceivableInterest;
	}

	public void setReceivableInterest(double ReceivableInterest) {
		this.ReceivableInterest = ReceivableInterest;
	}

	// / <summary>
	// / ʵ�ձ�Ϣ
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
	// / �Ƿ�����
	// / 0��δ���� 1������
	// / </summary>
	public int IsYuQi;

	public int getIsYuQi() {
		return IsYuQi;
	}

	public void setIsYuQi(int IsYuQi) {
		this.IsYuQi = IsYuQi;
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
	// / 0:δ��ȡ
	// / 2:����ȡ
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


    /// <summary>
    /// ƽ̨����
    /// </summary>
    public String SiteName;

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String Periods) {
		this.SiteName = Periods;
	}
    /// <summary>
    /// ƽ̨�û���
    /// </summary>
    public String SiteUserName;
    
	public String getSiteUserName() {
		return SiteUserName;
	}

	public void setSiteUserName(String Periods) {
		this.SiteUserName = Periods;
	}
    /// <summary>
    /// ����ܶ�
    /// </summary>
    public double TotalMoney;
	public double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(double Periods) {
		this.TotalMoney = Periods;
	}
    /// <summary>
    /// ��ע
    /// </summary>
    public String Remark;
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String Periods) {
		this.Remark = Periods;
	}

    /// <summary>
    /// Ӧ������
    /// </summary>
    public int ReceivablePeriod ;
	public int getReceivablePeriod() {
		return ReceivablePeriod;
	}

	public void setReceivablePeriod(int Periods) {
		this.ReceivablePeriod = Periods;
	}
    /// <summary>
    /// ��������
    /// </summary>
    public int ReceivedPeriod;
	public int getReceivedPeriod() {
		return ReceivedPeriod;
	}

	public void setReceivedPeriod(int Periods) {
		this.ReceivedPeriod = Periods;
	}
}
