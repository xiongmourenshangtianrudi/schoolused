package com.schoolused.entry;

public class uuidAndImg {
    private String uuid;
    private String imge;

    public uuidAndImg(String uuid, String imge) {//封装图片信息
        this.uuid = uuid;
        this.imge = imge;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
