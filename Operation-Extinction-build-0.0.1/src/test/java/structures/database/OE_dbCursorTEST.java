package structures.database;

import structures.database.OE_dbCursor;
import java.sql.*;
import org.junit.Test;

public class OE_dbCursorTEST {
    private OE_dbCursor _link;
    private OEuserData _data;
    public OE_dbCursorTEST() {
    }
//    @Test
//    public void userReadTEST() {
//        _link = new OE_dbCursor(OE_dbCursor.Mode.READUSER);
//        assert(_link.setInput("DEV"));
//        
//        try{
//            _data = _link.userRead();
//        } catch(SQLException e){
//            System.err.println(e.getClass().getName() + ":" + e.getMessage());
//            System.exit(0);
//        }
//        
//        assert(_data.getString("id") == "DEV");
//        assert(_data.getString("password") == "sunshine");
//    }
    
    @Test
    public void userCreateTEST() {
    	_link = new OE_dbCursor(OE_dbCursor.Mode.READUSER);
    	assert(_link.setInput("dev2"));
    	// This user should not be in the database
    	assert(!_link.userExists());				// <-- Assertion Error
    	// Create the new user
    	_link.setMode(OE_dbCursor.Mode.WRITEUSER);
    	assert(_link.userCreate("password"));
    }
}