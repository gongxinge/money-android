package g.android.p2p.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import g.android.p2p.model.BillModel;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BillInfoDAO {
	private DBOpenHelper helper;// ����DBOpenHelper����
	private SQLiteDatabase db;// ����SQLiteDatabase����

	public BillInfoDAO(Context context)// ���幹�캯��
	{
		helper = new DBOpenHelper(context);// ��ʼ��DBOpenHelper����
	}

	public void add(BillModel billModel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// ��ʽ������
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		// ִ������������
		// db.execSQL("insert into tb_pwd (password) values (?)",
		// new Object[] { tb_pwd.getPassword() });
		db.execSQL(
				"Insert into billInfo (BillID ,SiteName,SiteUserName,TotalMoney,YearRete,Deadline,DeadlineType,ReceivedPeriod,ReceivablePeriod,ReceivablePrincipalAndInterest,ReceivedPrincipalAndInterest,WayOfRepayment,Reward,BeginDay,EndDay,YuQiCount,Deleted,Flag,Remark,UpdateTime,Createtime)Values(?,?,?,?,?,?,?,0,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
				new Object[] { billModel.BillID, billModel.SiteName,
						billModel.SiteUserName, billModel.TotalMoney,
						billModel.YearRete, billModel.Deadline,
						billModel.DeadlineType, billModel.ReceivablePeriod,
						billModel.ReceivablePrincipalAndInterest,
						billModel.ReceivedPrincipalAndInterest,
						billModel.WayOfRepayment.ordinal(), billModel.Reward,
						billModel.BeginDay, billModel.EndDay,
						billModel.YuQiCount, billModel.Deleted,
						billModel.Deleted, billModel.Remark,
						sdf.format(billModel.UpdateTime),
						sdf.format(billModel.CreateTime) });
	}

	public List<Double> GetMoneyInfo() {
		try{
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		}catch(Exception e)
		{
			int a= 0;
			a++;
		}
		//db = SQLiteDatabase.openDatabase("/data/data/g.android.p2p.activity/databases/data.dat", null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);// ��ʼ��SQLiteDatabase����
		List<Double> list = new ArrayList<Double>();

		// �������벢�洢��Cursor����
		Cursor cursor = db
				.rawQuery(
						"select sum(receivableprincipalandinterest) receivableprincipalandinterest,sum(receivableinterest) receivableinterest from billdetail where flag=0 and deleted=0",
						null);
		if (cursor.moveToNext())// �������ҵ���������Ϣ
		{
			list.add(cursor.getDouble(cursor
					.getColumnIndex("receivableprincipalandinterest")));
			list.add(cursor.getDouble(cursor
					.getColumnIndex("receivableinterest")));

		}
		return list;
	}

	public void ExecSQL(int ReceivedPeriod, int flag, String BillID) {
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
		db.execSQL(
				"update billinfo set ReceivedPeriod=? ,Flag=? where billid=?",
				new Object[] { ReceivedPeriod, flag, BillID });
	}
}
