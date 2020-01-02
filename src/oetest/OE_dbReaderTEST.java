

package oetest;

import oestruc.db.OE_dbReader;
import oestruc.db.OEuserData;
import java.sql.*;

public class OE_dbReaderTEST {
    private OE_dbReader _link;
    private OEuserData _data;
    private static int cardmode = OE_dbReader.CARDMODE;
    private static int usermode = OE_dbReader.USERMODE;
    public OE_dbReaderTEST() {
        _link = new OE_dbReader();

        assert(_link.setMode(cardmode));
        assert(!_link.setMode(9));
        assert(_link.setMode(usermode));
        assert(_link.setKeyInput("DEV"));
        
        try{
            _data = _link.userRead();
        } catch(SQLException e){
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
        
        assert(_data.getString("id") == "DEV");
        assert(_data.getString("password") == "sunshine");

    }
}