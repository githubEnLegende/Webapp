package org.oxyl.model;

public class Promotion {
    private long id;
    private String name;


    public Promotion(PromotionBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "PromotionID : " + id + " " + name;
    }

    public static class PromotionBuilder {
        private long id;
        private String name;

        public PromotionBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public PromotionBuilder id(long id) {
            this.id = id;
            return this;
        }

        public PromotionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Promotion build() {
            return new Promotion(this);
        }
    }

}
