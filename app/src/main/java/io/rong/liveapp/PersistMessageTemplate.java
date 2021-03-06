package io.rong.liveapp;

import com.xiaoying.imapi.BaseMessageTemplate;
import com.xiaoying.imapi.XYIMUserInfo;
import com.xiaoying.imapi.api.TemplateTag;
import com.xiaoying.imapi.message.UIMessage;
import com.xiaoying.imapi.message.XYMessage;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@TemplateTag(messageContent = PersistMessage.class)
public class PersistMessageTemplate implements BaseMessageTemplate {
    private final static String TAG = "PersistMessageTemplate";

    @Override
    public View getView(View convertView, int position, ViewGroup parent, UIMessage data) {
        Log.e(TAG, "getView " + position + " " + convertView);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_customize_message, null);
            holder.content = (TextView) convertView.findViewById(R.id.rc_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        XYMessage msg = data.getMessage();
        XYIMUserInfo info = msg.getContent().getUserInfo();
        String username;
        if (info != null) {
            username = info.getName();
        } else {
            username = msg.getSenderUserId();
        }

        PersistMessage persistMsg = (PersistMessage) msg.getContent();
        holder.content.setText(username + ": 发送一条存库不计数消息, content = " + persistMsg.getContent());
        return convertView;
    }

    @Override
    public void onItemClick(View view, int position, UIMessage data) {

    }

    @Override
    public void onItemLongClick(View view, int position, UIMessage data) {

    }

    @Override
    public void destroyItem(ViewGroup group, Object template) {

    }

    private class ViewHolder {
        TextView content;
    }
}
