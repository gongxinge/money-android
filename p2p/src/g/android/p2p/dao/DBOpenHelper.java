package g.android.p2p.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final int VERSION = 1;// �������ݿ�汾��
	private static final String DBNAME = "data.dat";// �������ݿ���

	public DBOpenHelper(Context context){// ���幹�캯��
	
		super(context, DBNAME, null, VERSION);// ��д����Ĺ��캯��
	}

	@Override
	public void onCreate(SQLiteDatabase db){// �������ݿ�

		//db.execSQL("CREATE TABLE billInfo (BillID varchar(50) PRIMARY KEY,SiteName varchar(50),SiteUserName varchar(50),TotalMoney decimal(5,2),YearRete decimal(5,2),Deadline interger ,DeadlineType interger,ReceivedPeriod interger,ReceivablePeriod interger,ReceivablePrincipalAndInterest decimal(5,2),ReceivedPrincipalAndInterest decimal(5,2),WayOfRepayment interger,Reward decimal(5,2),BeginDay varchar(20),EndDay varchar(20),YuQiCount interger,Deleted interger,Flag interger,Remark varchar(200),UpdateTime datetime,CreateTime datetime)");// ����֧����Ϣ��
		//db.execSQL("Create Table billdetail(BillDetailID varchar(50) PRIMARY KEY,BillID varchar(50),Periods varchar(50),ReceivableDay varchar(20),ReceivedDay varchar(20),ReceivablePrincipalAndInterest decimal(5,2),ReceivableInterest decimal(5,2),ReceivedPrincipalAndInterest decimal(5,2),IsYuQi interger ,Deleted interger,Flag interger,UpdateTime datetime,CreateTime datetime)");// ����������Ϣ��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)// ��д�����onUpgrade�������Ա����ݿ�汾����
	{
	}
}
