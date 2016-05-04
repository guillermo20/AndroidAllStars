/* The MIT License (MIT)
* Copyright (c) 2016 BELATRIX
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/
package com.belatrixsf.allstars.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.belatrixsf.allstars.R;
import com.belatrixsf.allstars.entities.Employee;
import com.belatrixsf.allstars.utils.Constants;
import com.belatrixsf.allstars.utils.media.ImageLoader;
import com.belatrixsf.allstars.utils.media.ImageUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by icerrate on 28/04/2016.
 */
public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.EmployeeViewHolder> {

    private List<Employee> rankingList;
    private RankingListClickListener rankingListClickListener;

    public RankingListAdapter(RankingListClickListener rankingListClickListener) {
        this(rankingListClickListener, new ArrayList<Employee>());
    }

    public RankingListAdapter(RankingListClickListener rankingListClickListener, List<Employee> rankingList) {
        this.rankingList = rankingList;
        this.rankingListClickListener = rankingListClickListener;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new EmployeeViewHolder(layoutView, rankingListClickListener);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        final Employee employee = rankingList.get(position);
        int place = position + Constants.ONE_UNIT;
        int cupResourceId;
        switch (place) {
            case Constants.FIRST_POSITION:
                cupResourceId = R.drawable.ic_first_place_cup;
                break;
            case Constants.SECOND_POSITION:
                cupResourceId = R.drawable.ic_second_place_cup;
                break;
            case Constants.THIRD_POSITION:
                cupResourceId = R.drawable.ic_third_place_cup;
                break;
            default:
                cupResourceId = R.drawable.ic_cup;
                break;
        }
        holder.positionNumber.setText(String.valueOf(place));
        holder.positionNumber.setVisibility(View.VISIBLE);
        holder.scoreCup.setBackgroundResource(cupResourceId);
        holder.fullName.setText(employee.getFullName());
        String stringScore = String.valueOf(employee.getValue());
        holder.score.setText(stringScore);
        if (employee.getAvatar() != null) {
            ImageUtils.get().getLoader().loadFromUrl(employee.getAvatar(), holder.photo, ImageLoader.ImageTransformation.CIRCLE);
        }
    }

    @Override
    public int getItemCount() {
        return this.rankingList.size();
    }

    public void updateData(List<Employee> ranking) {
        rankingList.clear();
        rankingList.addAll(ranking);
        notifyDataSetChanged();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.position_number)
        public TextView positionNumber;
        @Bind(R.id.photo)
        public ImageView photo;
        @Bind(R.id.full_name)
        public TextView fullName;
        @Bind(R.id.score_cup)
        public ImageView scoreCup;
        @Bind(R.id.score_number)
        public TextView score;

        private RankingListClickListener rankingListClickListener;

        public EmployeeViewHolder(View view, RankingListClickListener rankingListClickListener) {
            super(view);
            this.rankingListClickListener = rankingListClickListener;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.layout_container)
        public void onClick() {
            if (rankingListClickListener != null) {
                rankingListClickListener.onEmployeeClicked(getLayoutPosition());
            }
        }

    }

    public interface RankingListClickListener {

        void onEmployeeClicked(int position);

    }
}
