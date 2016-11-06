package br.eti.cvm.miniprojeto3.dbutil;

import br.eti.cvm.miniprojeto3.entity.Cargo;

public class DbConstants {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DbConstants.db";

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_CARGO =
            "CREATE TABLE " + Cargo.TABLE + " (" +
                    Cargo._ID + " LONG PRIMARY KEY," +
                    Cargo.COLUMN_NOME + TEXT_TYPE + " )";

    public static final String SQL_DELETE_CARGO =
            "DROP TABLE IF EXISTS " + Cargo.TABLE;
}
