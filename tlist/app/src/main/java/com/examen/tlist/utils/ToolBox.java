package com.examen.tlist.utils;

import android.content.Context;
import android.widget.Toast;

public class ToolBox {

    public ToolBox() {
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
