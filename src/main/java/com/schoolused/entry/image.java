package com.schoolused.entry;



public class image {//imge的url
    private int commodity_id;
    private String com_imge_url;
    private int id;

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getCom_imge_url() {
        return com_imge_url;
    }

    public void setCom_imge_url(String com_imge_url) {
        this.com_imge_url = com_imge_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "image{" +
                "commodity_id=" + commodity_id +
                ", com_imge_url='" + com_imge_url + '\'' +
                ", id=" + id +
                '}';
    }
}
