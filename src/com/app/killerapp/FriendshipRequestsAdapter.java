package com.app.killerapp;

import java.util.ArrayList;
import java.util.List;

import core.connection.RESTSocialService;
import core.models.Friendship;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class FriendshipRequestsAdapter extends BaseAdapter {

	private static List<Friendship> searchArrayList;
	private LayoutInflater mInflater;
	private final Context context;
	private long id;
	private String auth;

	public FriendshipRequestsAdapter(Context context, long id, String auth) {

		this.id = id;
		this.auth = auth;
		this.mInflater = LayoutInflater.from(context);
		this.context = context;
		Log.d("FriendReqAdapter", "Constructing");
	}

	public void setList(List<Friendship> results) {
		searchArrayList = results;
		notifyDataSetChanged();
	}

	public int getCount() {
		if (searchArrayList != null) {
			return searchArrayList.size();
		} else {
			return 0;
		}
	}

	public Object getItem(int position) {
		if (searchArrayList != null) {
			return searchArrayList.get(position);
		}
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("FriendReqAdapter: ", "Starting Getview");
		ViewHolderStatus holder;
		if (convertView == null) {
			holder = new ViewHolderStatus();

			// Search if status is already changed
			Log.d("FriendReqAdapter: ",
					"Statug: " + searchArrayList.get(position).getStatus());
			Log.d("FriendReqAdapter: ", "position: " + position);
			Friendship frTemp = searchArrayList.get(position);
			Log.d("FriendReqAdapter: ",
					"Friendship summary: " + frTemp.toString());

			if (frTemp.getStatus().trim().contains("PENDING")
					|| frTemp.getStatus().trim().contains("SENT")) {
				Log.d("FriendReqAdapter: ", "Pending statement");
				convertView = mInflater.inflate(R.layout.company_request_row,
						null);

				// Approve
				holder.buttonApprove = (Button) convertView
						.findViewById(R.id.buttonAccept);

				holder.buttonApprove.setTag(frTemp);
				holder.buttonApprove.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						clickApproveListener(v);
					}
				});

				holder.buttonDecline = (Button) convertView
						.findViewById(R.id.buttonDecline);
				// Decline
				holder.buttonDecline.setTag(frTemp);
				holder.buttonDecline.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						clickDeclineListener(v);
					}
				});

				holder.txtName = (TextView) convertView
						.findViewById(R.id.username);
				holder.txtName.setText(frTemp.getInitiator().getUsername());

			}

			convertView.setTag(holder);

		} else {
			holder = (ViewHolderStatus) convertView.getTag();
		}
		return convertView;
	}

	private OnClickListener clickApproveListener(View v) {

		Friendship item = (Friendship) v.getTag();
		RESTSocialService socialService = new RESTSocialService();
		socialService.ApproveFriendship(item.getParticipant().getId(), auth,
				item.getId());
		Log.d("Approving auth: ", auth);
		Log.d("Approving: ", item.toString());
		notifyDataSetChanged();
		return null;
	}

	private OnClickListener clickDeclineListener(View v) {

		Friendship item = (Friendship) v.getTag();
		RESTSocialService socialService = new RESTSocialService();
		socialService.DeclineFriendship(item.getParticipant().getId(), auth, item.getId());
		Log.d("Trying to Deny: ", item.toString());
		notifyDataSetChanged();
		return null;
	}

	static class ViewHolder {
		TextView txtName;

	}

	static class ViewHolderStatus {
		TextView txtName;
		TextView txtStatus;
		Button buttonApprove;
		Button buttonDecline;
	}

}
