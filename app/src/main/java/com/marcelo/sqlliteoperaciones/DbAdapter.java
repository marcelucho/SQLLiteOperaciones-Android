package com.marcelo.sqlliteoperaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {

    dbHelper helper;
    public DbAdapter(Context context){
        helper = new dbHelper(context);
    }
    public long insertarDatos(String usuario, String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contenidoValores = new ContentValues();
        contenidoValores.put(dbHelper.NAME, usuario);
        contenidoValores.put(dbHelper.MyPASSWORD, password);
        long id = db.insert(dbHelper.TABLE_NAME, null, contenidoValores);
        return id;
    }
    static class dbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "registrosdb"; // Nombre de base de datos
        private static final String TABLE_NAME = "usuario"; // Nombre de tabla
        private  static  final int DATABASE_version = 1; // version de base de datos
        private static final String UID ="_id"; // Columna 1 para la clave primaria
        private static final String NAME = "Nombre"; // Columna 2 para nombre
        private static final String MyPASSWORD = "Password"; // Columna 3 para contraseña

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "("+UID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+ NAME+" VARCHAR(255),"+MyPASSWORD+" VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public dbHelper (Context contextRicibido){
            super(contextRicibido, DATABASE_NAME,null, DATABASE_version );
            this.context= contextRicibido;

        }

        // Se crea la base de datos
        public  void onCreate(SQLiteDatabase db){
            try {
                db.execSQL(CREATE_TABLE);
            }catch(Exception e){
              Mensaje.aviso(context, "Marcelo tu error--> "+ e );
            }
        }
        // Se actualiza la base de datos
        @Override
        public void onUpgrade(SQLiteDatabase db, int viejaVersion, int nuevaVersion){
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e){
                Mensaje.aviso(context, "marcelo tu error -->"+e);
            }
        }
    }
}
