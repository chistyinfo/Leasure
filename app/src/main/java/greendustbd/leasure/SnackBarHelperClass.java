package greendustbd.leasure;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Suuny on 11/4/2015.
 */
public class SnackBarHelperClass {

    public static Snackbar snackBarWhiteMethod(CoordinatorLayout coordinatorLayout, String messages) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messages, Snackbar.LENGTH_LONG);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);

        return snackbar;
    }
    public static Snackbar snackBarGreenMethod(CoordinatorLayout coordinatorLayout, String messages) {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, messages, Snackbar.LENGTH_LONG);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);

        return snackbar;
    }

}


