package com.marcelo.sqlliteoperaciones;

import android.content.Context;
import android.widget.Toast;

public class Mensaje {

    public static void aviso(Context context, String aviso){
        Toast.makeText(context, aviso, Toast.LENGTH_LONG).show();

    }
}
