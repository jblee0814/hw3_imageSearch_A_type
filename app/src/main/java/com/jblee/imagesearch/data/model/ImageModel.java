
package com.jblee.imagesearch.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * 이미지 검색 응답을 위한 모델 클래스.
 */
public class ImageModel {

    @SerializedName("documents")
    private ArrayList<Documents> documents;

    @SerializedName("meta")
    private Meta meta;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Documents> documents) {
        this.documents = documents;
    }

    /**
     * 이미지 검색 응답에서 단일 문서 혹은 결과를 나타내는 클래스.
     */
    public class Documents {

        @SerializedName("collection")
        private String collection;

        @SerializedName("thumbnail_url")
        private String thumbnail_url;

        @SerializedName("image_url")
        private String image_url;

        @SerializedName("width")
        private int width;

        @SerializedName("height")
        private int height;

        @SerializedName("display_sitename")
        private String display_sitename;

        @SerializedName("doc_url")
        private String doc_url;

        @SerializedName("datetime")
        private String datetime;

        // 위의 속성들에 대한 getter와 setter...
        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getThumbnail_url() {
            return thumbnail_url;
        }

        public void setThumbnail_url(String thumbnail_url) {
            this.thumbnail_url = thumbnail_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getDisplay_sitename() {
            return display_sitename;
        }

        public void setDisplay_sitename(String display_sitename) {
            this.display_sitename = display_sitename;
        }

        public String getDoc_url() {
            return doc_url;
        }

        public void setDoc_url(String doc_url) {
            this.doc_url = doc_url;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }
    }

    /**
     * 이미지 검색 응답에 대한 메타 정보를 나타내는 클래스.
     */
    public class Meta {

        @SerializedName("is_end")
        private boolean is_end;

        @SerializedName("pageable_count")
        private int pageable_count;

        @SerializedName("total_count")
        private int total_count;

        // 위의 속성들에 대한 getter와 setter...
        public boolean isIs_end() {
            return is_end;
        }

        public void setIs_end(boolean is_end) {
            this.is_end = is_end;
        }

        public int getPageable_count() {
            return pageable_count;
        }

        public void setPageable_count(int pageable_count) {
            this.pageable_count = pageable_count;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }
    }
}

/**
 * 코드는 ImageModel이라는 주 클래스로 구성되어 있으며, 이는 이미지 검색 응답을 나타냅니다.
 * 이 모델에는 두 개의 내부 클래스가 포함되어 있습니다: Documents와 Meta.
 * Documents는 검색 결과의 각 항목을 나타내며, Meta는 검색 응답의 메타 정보를 나타냅니다.
 * @SerializedName 애너테이션은 JSON 응답과 자바 객체 사이의 매핑을 지정합니다.
 */
