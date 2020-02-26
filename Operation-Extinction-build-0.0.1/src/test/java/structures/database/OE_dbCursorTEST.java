package structures.database;

import structures.database.OE_dbCursor;
import java.sql.*;
import org.junit.Test;

public class OE_dbCursorTEST {
    private OE_dbCursor _link;
    private OEuserData _data;
    public OE_dbCursorTEST() {
    }
    @Test
    public void dbReaderTEST() {
        _link = new OE_dbCursor("read", "user");
        assert(_link.setInput("DEV"));
        
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