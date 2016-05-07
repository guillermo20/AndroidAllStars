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
import com.belatrixsf.allstars.ui.common.RecyclerOnItemClickListener;
import com.belatrixsf.allstars.utils.media.ImageFactory;
import com.belatrixsf.allstars.utils.media.loaders.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by icerrate on 15/04/2016.
 */
public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    private List<Employee> contactsList;
    private RecyclerOnItemClickListener recyclerOnItemClickListener;

    public ContactsListAdapter(RecyclerOnItemClickListener recyclerOnItemClickListener) {
        this(recyclerOnItemClickListener, new ArrayList<Employee>());
    }

    public ContactsListAdapter(RecyclerOnItemClickListener recyclerOnItemClickListener, List<Employee> contactsList) {
        this.contactsList = contactsList;
        this.recyclerOnItemClickListener = recyclerOnItemClickListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(layoutView, recyclerOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        final Employee employee = contactsList.get(position);
        holder.itemView.setTag(employee);
        holder.contactFullNameTextView.setText(employee.getFullName());
        String levelLabel = String.format(holder.contactLevelTextView.getContext().getString(R.string.contact_list_level), String.valueOf(employee.getLevel()));
        holder.contactLevelTextView.setText(levelLabel);
        if (employee.getAvatar() != null) {
            ImageFactory.getLoader().loadFromUrl(employee.getAvatar(), holder.photoImageView, ImageLoader.ImageTransformation.BORDERED_CIRCLE);
        }
    }

    @Override
    public int getItemCount() {
        return this.contactsList.size();
    }

    public void updateData(List<Employee> employees) {
        contactsList.clear();
        contactsList.addAll(employees);
        notifyDataSetChanged();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.contact_photo)
        public ImageView photoImageView;
        @Bind(R.id.contact_full_name)
        public TextView contactFullNameTextView;
        @Bind(R.id.contact_level)
        public TextView contactLevelTextView;
        private RecyclerOnItemClickListener recyclerOnItemClickListener;

        public ContactViewHolder(View view, RecyclerOnItemClickListener recyclerOnItemClickListener) {
            super(view);
            ButterKnife.bind(this, view);
            this.recyclerOnItemClickListener = recyclerOnItemClickListener;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerOnItemClickListener != null) {
                recyclerOnItemClickListener.onClick(this.itemView);
            }
        }
    }
}