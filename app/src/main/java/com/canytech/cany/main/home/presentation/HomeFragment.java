package com.canytech.cany.main.home.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canytech.cany.R;
import com.canytech.cany.items.DocumentationItem;
import com.canytech.cany.items.ProgrammingItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);

        List<ProgrammingItem> programmingItems = new ArrayList<>();
        programmingItems.add(new ProgrammingItem(1, R.string.app_name, R.drawable.perfilphoto));
        programmingItems.add(new ProgrammingItem(2, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(3, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(4, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(5, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(6, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(7, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(8, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(9, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(10, R.string.access, R.drawable.asdasd));
        programmingItems.add(new ProgrammingItem(11, R.string.access, R.drawable.asdasd));


        List<DocumentationItem> documentationItems = new ArrayList<>();
        documentationItems.add(new DocumentationItem(1, R.string.add_photo, R.drawable.bg_app));
        documentationItems.add(new DocumentationItem(2, R.string.search_gallery, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(3, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(4, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(5, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(6, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(7, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(8, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(9, R.string.access, R.drawable.asdasd));
        documentationItems.add(new DocumentationItem(10, R.string.access, R.drawable.asdasd));

        RecyclerView recyclerViewProgramming = view.findViewById(R.id.programming_recycler);
        recyclerViewProgramming.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ProgrammingAdapter programmingAdapter = new ProgrammingAdapter(programmingItems);
        recyclerViewProgramming.setAdapter(programmingAdapter);

        RecyclerView recyclerViewDocumentation = view.findViewById(R.id.documentation_recycler);
        recyclerViewDocumentation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        DocumentationAdapter documentationAdapter = new DocumentationAdapter(documentationItems);
        recyclerViewDocumentation.setAdapter(documentationAdapter);

        return view;
    }

    private class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingViewHolder> {

        private List<ProgrammingItem> programmingItems;

        public ProgrammingAdapter(List<ProgrammingItem> programmingItems) {
            this.programmingItems = programmingItems;
        }

        @NonNull
        @Override
        public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProgrammingViewHolder(getLayoutInflater().inflate(R.layout.rv_item_course, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int position) {
            ProgrammingItem programmingItemCurrent = programmingItems.get(position);
            holder.bind(programmingItemCurrent);
        }

        @Override
        public int getItemCount() {
            return programmingItems.size();
        }
    }

    private static class ProgrammingViewHolder extends RecyclerView.ViewHolder {

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ProgrammingItem item) {
            TextView textItem = itemView.findViewById(R.id.text_view_item_title);
            ImageView imageCover = itemView.findViewById(R.id.courseImage);

            textItem.setText(item.getTxtTitleItem());
            imageCover.setImageResource(item.getImgCoverItem());
        }

    }

    private class DocumentationAdapter extends RecyclerView.Adapter<DocumentationViewHolder> {

        private List<DocumentationItem> documentationItems;

        public DocumentationAdapter(List<DocumentationItem> documentationItems) {
            this.documentationItems = documentationItems;
        }

        @NonNull
        @Override
        public DocumentationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DocumentationViewHolder(getLayoutInflater().inflate(R.layout.rv_item_course, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DocumentationViewHolder holder, int position) {
            DocumentationItem documentationItemCurrent = documentationItems.get(position);
            holder.bind(documentationItemCurrent);
        }

        @Override
        public int getItemCount() {
            return documentationItems.size();
        }
    }

    private static class DocumentationViewHolder extends RecyclerView.ViewHolder {

        public DocumentationViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(DocumentationItem item) {
            TextView textItem = itemView.findViewById(R.id.text_view_item_title);
            ImageView imageCover = itemView.findViewById(R.id.courseImage);

            textItem.setText(item.getTxtTitleItem());
            imageCover.setImageResource(item.getImgCoverItem());
        }

    }
}
