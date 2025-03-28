package com.liuxing.activitystalk;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：流星
 * DateTime：2025/3/28 9:21
 * Description：ActivityListAdapter
 */
public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ViewHolder> {

    private final List<String> packageList = new ArrayList<>();
    private final List<String> activityList = new ArrayList<>();

    public ActivityListAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activty_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(packageList.get(position));
        holder.tvActivity.setText(activityList.get(position));

        holder.itemView.setOnClickListener(v -> {
            String packageName = packageList.get(position);
            String activityName = activityList.get(position);

            copyTextToClipboard(holder.itemView.getContext(), packageName + "\n" + activityName);
            Toast.makeText(holder.itemView.getContext(), "复制到剪贴板: " + packageName + "\n" + activityName, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    /**
     * 更新数据
     *
     * @param packageName  包名
     * @param activityName 活动名
     */
    public void updateData(String packageName, String activityName) {
        if (packageName == null || activityName == null) {
            return;
        }
        packageList.add(packageName);
        activityList.add(activityName);
        notifyItemInserted(packageList.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvActivity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvActivity = itemView.findViewById(R.id.tv_activity);
        }
    }

    /**
     * 复制文本到剪贴板
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            ClipData clipData = ClipData.newPlainText("text", text);
            clipboardManager.setPrimaryClip(clipData);
        }
    }

}
