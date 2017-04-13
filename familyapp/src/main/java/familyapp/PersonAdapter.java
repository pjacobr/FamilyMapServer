package familyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.familyapp.R;

import java.util.List;

import result.PersonResult;

/**
 * Created by jacob on 4/12/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        android.content.Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View contactView = inflater.inflate(R.layout.person_children_layout, parent, false);

        //Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the data model based on position
        ModelContainer personInfo = ModelContainer.getModelInstance();
        PersonResult p = personInfo.getPersons().get(position);

        TextView textView = holder.personInfo;
        textView.setText(p.getFirstname());

    }

    @Override
    public int getItemCount() {
        return ModelContainer.getModelInstance().getPersons().size();
    }

    //Proved a direct reference to each of the
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Holder should contain a member variable
        //for any view that will be set as you render a row
        public TextView personInfo;

        //We also create a constructor that accepts the entire tiem row
        //and does the view lookups to find each subview
        public ViewHolder(View itemView){
            super(itemView);

            personInfo = (TextView)itemView.findViewById(R.id.child_name);
        }
    }
    //The Children to include
    private List<PersonResult> mPersons;
    private Context mContext;

    //pass in the contact array into the constructor
    public PersonAdapter(Context context, List<PersonResult> persons){
        mContext = context;
        mPersons = persons;
    }

    private Context getmContext(){
        return mContext;
    }



}
