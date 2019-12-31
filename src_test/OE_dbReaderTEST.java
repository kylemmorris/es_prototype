

package src_test;

import oestruc.db.OE_dbReader;
import oestruc.db.OEuserData;
import java.sql;

public class OE_dbReaderTEST {
    private OE_dbReader _link;
    private OEuserData _data;
    private int cardmode = OE_dbReader().CARDMODE;
    private int usermode = OE_dbReader().USERMODE;
    public OE_dbReaderTEST() {
        _link = new OE_dbReader();

        assert(_link.setMode(cardmode));
        assert(!_link.setMode(9));
        assert(_link.setMode(usermode));
        assert(_link.setKeyInput("DEV"));

        _data = _link.userRead();
        assert(_data.getString("id") == "DEV");
        assert(_data.getString("password") == "sunshine");

    }
}