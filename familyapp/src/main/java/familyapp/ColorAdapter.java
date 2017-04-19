package familyapp;

import android.content.*;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import java.util.List;

/**
 * Created by jacob on 4/18/2017.
 */

public class ColorAdapter extends ArrayAdapter<String> {
    public ColorAdapter(@NonNull android.content.Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }
}
