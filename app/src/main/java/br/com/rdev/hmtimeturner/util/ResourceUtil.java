package br.com.rdev.hmtimeturner.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {

    public static final String DRAWABLE = "drawable";

    public static Drawable devolveDrawable (Context context, String drawableEmTexto) {
        Resources resource = context.getResources();
        int idDoDrawable = resource.getIdentifier(drawableEmTexto, DRAWABLE, context.getPackageName());
        return resource.getDrawable(idDoDrawable);
    }
}
