package com.example.ekadarmaputra.sqlitetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ekadarmaputra on 3/26/17.
 */

public class DisplayAdapter extends BaseAdapter {
    public Context mContext;
    public ArrayList<String> id;
    public ArrayList<String> question;
    public ArrayList<String> answer;
    public ArrayList<String> optA;
    public ArrayList<String> optB;
    public ArrayList<String> optC;

    public DisplayAdapter(Context c, ArrayList<String> id,ArrayList<String> question, ArrayList<String> answer, ArrayList<String> optA,
                          ArrayList<String> optB, ArrayList<String> optC) {
        this.mContext = c;

        this.id = id;
        this.question = question;
        this.answer = answer;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return id.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listcell, null);
            mHolder = new Holder();
            mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
            mHolder.txt_question = (TextView) child.findViewById(R.id.txt_question);
            mHolder.txt_answer = (TextView) child.findViewById(R.id.txt_answer);
            mHolder.txt_optA = (TextView) child.findViewById(R.id.txt_optA);
            mHolder.txt_optB = (TextView) child.findViewById(R.id.txt_optB);
            mHolder.txt_optC = (TextView) child.findViewById(R.id.txt_optC);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }
        mHolder.txt_id.setText(id.get(pos));
        mHolder.txt_question.setText(question.get(pos));
        mHolder.txt_answer.setText(answer.get(pos));
        mHolder.txt_optA.setText(optA.get(pos));
        mHolder.txt_optB.setText(optB.get(pos));
        mHolder.txt_optC.setText(optC.get(pos));

        return child;
    }

    public class Holder {
        TextView txt_id;
        TextView txt_question;
        TextView txt_answer;
        TextView txt_optA;
        TextView txt_optB;
        TextView txt_optC;
    }
}
