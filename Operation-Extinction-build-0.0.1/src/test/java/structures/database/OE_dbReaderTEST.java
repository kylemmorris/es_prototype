package structures.database;

import structures.database.OE_dbReader;
import java.sql.*;
import org.junit.Test;

public class OE_dbReaderTEST {
    private OE_dbReader _link;
    private OEuserData _data;
    public OE_dbReaderTEST() {
    }
    @Test
    public void dbReaderTEST() {
        _link = new OE_dbReader();
        _link.initUserMode();
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