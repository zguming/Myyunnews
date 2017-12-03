package db;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/11/29.
 */

public class Test extends DataSupport{
    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String it;
}
