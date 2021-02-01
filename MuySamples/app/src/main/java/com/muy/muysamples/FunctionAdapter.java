package com.muy.muysamples;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.muy.muysamples.animation.AnimatedVectorActivity;
import com.muy.muysamples.event.EventHandleActivity;
import com.muy.muysamples.http.HttpSampleActivity;
import com.muy.muysamples.lottieanimtion.LottieAnimActivity;
import com.muy.muysamples.notification.NotificationActivity;
import com.muy.muysamples.permission.PermissionActivity;
import com.muy.muysamples.permission.PermissionSampleActivity;
import com.muy.muysamples.popupwindow.PopupActivity;
import com.muy.muysamples.recyclerview.RecyclerViewSampleActivity;
import com.muy.muysamples.storage.ScopedStorageActivity;
import com.muy.muysamples.systemui.SystemUiActivity;
import com.muy.muysamples.wallpaper.LiveWallpaperActivity;
import com.muy.muysamples.widget.WidgetSampleActivity;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.TextViewHolder> {

    private final static String SAMPLE_RECYCLER_VIEW = "RecyclerView Sample";
    private final static String SAMPLE_HTTP = "Http Sample";
    private final static String SAMPLE_POPUP = "Popup window Sample";
    private final static String SAMPLE_NOTIFICATION = "Notification Sample";
    private final static String SAMPLE_SYSTEM_UI = "System UI Sample";
    private final static String SAMPLE_LOTTIE_ANIM = "Lottie Animation Sample";
    private final static String SAMPLE_WALLPAPER = "Wallpaper Sample";
    private final static String SAMPLE_VECTOR_ANIM = "Vector Animation Sample";
    private final static String SAMPLE_WIDGET = "Widgets Sample";
    private final static String SAMPLE_EVENT_HANDLE = "Event Handle Sample";
    private final static String SAMPLE_PERMISSION = "Permission Sample";
    private final static String SAMPLE_SCOPED_STORAGE = "SCOPED STORAGE Sample";



    private String[] functions = {SAMPLE_RECYCLER_VIEW, SAMPLE_HTTP, SAMPLE_POPUP, SAMPLE_NOTIFICATION, SAMPLE_SYSTEM_UI,
            SAMPLE_LOTTIE_ANIM, SAMPLE_WALLPAPER, SAMPLE_VECTOR_ANIM, SAMPLE_WIDGET, SAMPLE_EVENT_HANDLE,
            SAMPLE_PERMISSION, SAMPLE_SCOPED_STORAGE, "TODO Sample"};

    private Activity context;

    FunctionAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_function, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder textViewHolder, int i) {
        textViewHolder.bind(functions[i]);
    }

    @Override
    public int getItemCount() {
        return functions.length;
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.tv_function_name);
        }

        public void bind(final String data) {
            nameTv.setText(data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data) {
                        case SAMPLE_RECYCLER_VIEW:
                            openNewPage(RecyclerViewSampleActivity.class);
                            break;
                        case SAMPLE_HTTP:
                            openNewPage(HttpSampleActivity.class);
                            break;
                        case SAMPLE_POPUP:
                            openNewPage(PopupActivity.class);
                            break;
                        case SAMPLE_NOTIFICATION:
                            openNewPage(NotificationActivity.class);
                            break;
                        case SAMPLE_SYSTEM_UI:
                            openNewPage(SystemUiActivity.class);
                            break;
                        case SAMPLE_LOTTIE_ANIM:
                            openNewPage(LottieAnimActivity.class);
                            break;
                        case SAMPLE_WALLPAPER:
                            openNewPage(LiveWallpaperActivity.class);
                            break;
                        case SAMPLE_VECTOR_ANIM:
                            openNewPage(AnimatedVectorActivity.class);
                            break;
                        case SAMPLE_WIDGET:
                            openNewPage(WidgetSampleActivity.class);
                            break;
                        case SAMPLE_EVENT_HANDLE:
                            openNewPage(EventHandleActivity.class);
                            break;
                        case SAMPLE_PERMISSION:
                            openNewPage(PermissionActivity.class);
                            break;
                        case SAMPLE_SCOPED_STORAGE:
                            openNewPage(ScopedStorageActivity.class);
                            break;
                        default:
                            Toast.makeText(itemView.getContext(), data, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }

        private void openNewPage(Class a) {
            context.startActivity(new Intent(context, a));
        }
    }
}
