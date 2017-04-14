package familyapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familyapp.R;

import java.util.List;

import result.EventResult;
import result.PersonResult;

/**
 * Created by jacob on 4/13/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        android.content.Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //inflate the custom layout
        View contactView = inflater.inflate(R.layout.person_children_layout, parent, false);

        //Return a new holder instance
        EventsAdapter.ViewHolder viewHolder = new EventsAdapter.ViewHolder(contactView, context);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, int position) {
        //get the data model based on position
        ModelContainer eventsInfo = ModelContainer.getModelInstance();
        EventResult p = eventsInfo.getEvents().get(position);

        TextView textView = holder.personInfo;
        textView.setText(p.getCountry() + " " + p.getEventType());

    }

    @Override
    public int getItemCount() {
        return ModelContainer.getModelInstance().getEvents().size();
    }

    //Proved a direct reference to each of the
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Holder should contain a member variable
        //for any view that will be set as you render a row
        public TextView personInfo;

        //We also create a constructor that accepts the entire tiem row
        //and does the view lookups to find each subview
        public ViewHolder(View itemView, final android.content.Context c){
            super(itemView);

            personInfo = (TextView)itemView.findViewById(R.id.child_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c ,"clicked="+ getPosition(),Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(c, PersonActivity.class);
                    //myIntent.putExtra("key", value); //Optional parameters
                    c.startActivity(myIntent);
                }
            });
        }


    }
    //The Children to include
    private List<EventResult> events;
    private Context mContext;

    //pass in the contact array into the constructor
    public EventsAdapter(Context context, List<EventResult> events){
        mContext = context;
        this.events = events;
    }

    private Context getmContext(){
        return mContext;
    }




}
