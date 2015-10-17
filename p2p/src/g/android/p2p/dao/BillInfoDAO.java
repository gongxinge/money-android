package g.android.p2p.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import g.android.p2p.model.BillModel;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BillInfoDAO {
	private DBOpenHelper helper;// 创建DBOpenHelper对象
	private SQLiteDatabase db;// 创建SQLiteDatabase对象

	public BillInfoDAO(Context context)// 定义构造函数
	{
		helper = new DBOpenHelper(context);// 初始化DBOpenHelper对象
	}

	public void add(BillModel billModel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化对象
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		// 执行添加密码操作
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
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		}catch(Exception e)
		{
			int a= 0;
			a++;
		}
		//db = SQLiteDatabase.openDatabase("/data/data/g.android.p2p.activity/databases/data.dat", null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READWRITE);// 初始化SQLiteDatabase对象
		List<Double> list = new ArrayList<Double>();

		// 查找密码并存储到Cursor类中
		Cursor cursor = db
				.rawQuery(
						"select sum(receivableprincipalandinterest) receivableprincipalandinterest,sum(receivableinterest) receivableinterest from billdetail where flag=0 and deleted=0",
						null);
		if (cursor.moveToNext())// 遍历查找到的密码信息
		{
			list.add(cursor.getDouble(cursor
					.getColumnIndex("receivableprincipalandinterest")));
			list.add(cursor.getDouble(cursor
					.getColumnIndex("receivableinterest")));

		}
		return list;
	}

	public void ExecSQL(int ReceivedPeriod, int flag, String BillID) {
		db = helper.getWritableDatabase();// 初始化SQLiteDatabase对象
		db.execSQL(
				"update billinfo set ReceivedPeriod=? ,Flag=? where billid=?",
				new Object[] { ReceivedPeriod, flag, BillID });
	}
}
