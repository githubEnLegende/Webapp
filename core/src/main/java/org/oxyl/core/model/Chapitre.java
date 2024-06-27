package org.oxyl.core.model;

public class Chapitre {
    private long id;
    private String name;
    private String parent;


    public Chapitre(ChapitreBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.parent = builder.parent;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public static class ChapitreBuilder {
        private long id;
        private String name;
        private String parent;

        public ChapitreBuilder(long id, String name, String parent) {
            this.id = id;
            this.name = name;
            this.parent = parent;
        }

        public ChapitreBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ChapitreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ChapitreBuilder parent(String parent) {
            this.parent = parent;
            return this;
        }

        public Chapitre build() {
            return new Chapitre(this);
        }


    }


}
