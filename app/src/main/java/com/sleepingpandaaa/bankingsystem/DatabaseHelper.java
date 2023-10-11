package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(10412624256,'Tony Stark',246715000.00,'tonystark@gmail.com','1234567890','ABC098765400')");
        db.execSQL("insert into user_table values(9111234563567,'Bruce Wayne',554810582.67,'brucewayne@gmail.com','1234567891','ABC098765400')");
        db.execSQL("insert into user_table values(9111234562524,'Dr.Strange',1359.56,'strange@gmail.com','1234567892','ABC098765400')");
        db.execSQL("insert into user_table values(9111234562685,'Steve Rogers',1500.01,'steverogers@gmail.com','1234567893','ABC098765400')");
        db.execSQL("insert into user_table values(9111234563473,'Peter Parker',2603.48,'spiderman@gmail.com','1234567894','ABC098765400')");
        db.execSQL("insert into user_table values(9111234562567,'Wanda Maximoff',945.16,'wanda@gmail.com','1234567895','ABC098765400')");
        db.execSQL("insert into user_table values(9111234562763,'Natasha Romanoff',5936.00,'blackwidow@gmail.com','1234567896','ABC098765400')");
        db.execSQL("insert into user_table values(9111234563773,'Thor Odinson',857.22,'godofthunder@gmail.com','1234567897','ABC098765400')");
        db.execSQL("insert into user_table values(9111234563672,'Loki',4398.46,'loki@gmail.com','1234567898','ABC098765400')");
        db.execSQL("insert into user_table values(9111234563664,'Shang chi',273.90,'tenrings@gmail.com','1234567899','ABC098765400')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
