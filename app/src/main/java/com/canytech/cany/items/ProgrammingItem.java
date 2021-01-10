package com.canytech.cany.items;

public class ProgrammingItem {

    private int itemId;
    private int txtTitleItem;
    private int imgCoverItem;

    public ProgrammingItem(int itemId, int txtTitleItem, int imgCoverItem) {
        this.txtTitleItem = txtTitleItem;
        this.imgCoverItem = imgCoverItem;
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getTxtTitleItem() {
        return txtTitleItem;
    }

    public void setTxtTitleItem(int txtTitleItem) {
        this.txtTitleItem = txtTitleItem;
    }

    public int getImgCoverItem() {
        return imgCoverItem;
    }

    public void setImgCoverItem(int imgCoverItem) {
        this.imgCoverItem = imgCoverItem;
    }
}
