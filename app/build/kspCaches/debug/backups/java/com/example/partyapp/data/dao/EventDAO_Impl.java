package com.example.partyapp.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.partyapp.data.LocationDetails;
import com.example.partyapp.data.entity.Event;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDAO_Impl implements EventDAO {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfUpdateParticipants;

  public EventDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfUpdateParticipants = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE event SET participants=? WHERE eventId=?";
        return _query;
      }
    };
  }

  @Override
  public Object updateParticipants(final int newPref, final int eventId,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateParticipants.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, newPref);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, eventId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateParticipants.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<Event>> getEvents() {
    final String _sql = "SELECT * FROM event";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"event"}, new Callable<List<Event>>() {
      @Override
      public List<Event> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
            final int _cursorIndexOfStarts = CursorUtil.getColumnIndexOrThrow(_cursor, "starting_time");
            final int _cursorIndexOfEnds = CursorUtil.getColumnIndexOrThrow(_cursor, "ending_time");
            final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
            final int _cursorIndexOfSlots = CursorUtil.getColumnIndexOrThrow(_cursor, "slots");
            final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
            final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
            final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
            final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
            final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
            final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
            final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final Event _item;
              final int _tmpEventId;
              _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpImage;
              if (_cursor.isNull(_cursorIndexOfImage)) {
                _tmpImage = null;
              } else {
                _tmpImage = _cursor.getString(_cursorIndexOfImage);
              }
              final String _tmpStarts;
              if (_cursor.isNull(_cursorIndexOfStarts)) {
                _tmpStarts = null;
              } else {
                _tmpStarts = _cursor.getString(_cursorIndexOfStarts);
              }
              final String _tmpEnds;
              if (_cursor.isNull(_cursorIndexOfEnds)) {
                _tmpEnds = null;
              } else {
                _tmpEnds = _cursor.getString(_cursorIndexOfEnds);
              }
              final String _tmpDescription;
              if (_cursor.isNull(_cursorIndexOfDescription)) {
                _tmpDescription = null;
              } else {
                _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
              }
              final long _tmpSlots;
              _tmpSlots = _cursor.getLong(_cursorIndexOfSlots);
              final long _tmpParticipants;
              _tmpParticipants = _cursor.getLong(_cursorIndexOfParticipants);
              final LocationDetails _tmpLocation;
              final double _tmpLatitude;
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
              final double _tmpLongitude;
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
              final String _tmpState;
              if (_cursor.isNull(_cursorIndexOfState)) {
                _tmpState = null;
              } else {
                _tmpState = _cursor.getString(_cursorIndexOfState);
              }
              final String _tmpCity;
              if (_cursor.isNull(_cursorIndexOfCity)) {
                _tmpCity = null;
              } else {
                _tmpCity = _cursor.getString(_cursorIndexOfCity);
              }
              final String _tmpStreet;
              if (_cursor.isNull(_cursorIndexOfStreet)) {
                _tmpStreet = null;
              } else {
                _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
              }
              _tmpLocation = new LocationDetails(_tmpLatitude,_tmpLongitude,_tmpState,_tmpCity,_tmpStreet);
              _item = new Event(_tmpEventId,_tmpName,_tmpImage,_tmpLocation,_tmpStarts,_tmpEnds,_tmpDescription,_tmpSlots,_tmpParticipants);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Event>> getEventByEventId(final int eventId) {
    final String _sql = "SELECT * FROM event WHERE eventId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"event"}, new Callable<List<Event>>() {
      @Override
      public List<Event> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfStarts = CursorUtil.getColumnIndexOrThrow(_cursor, "starting_time");
          final int _cursorIndexOfEnds = CursorUtil.getColumnIndexOrThrow(_cursor, "ending_time");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSlots = CursorUtil.getColumnIndexOrThrow(_cursor, "slots");
          final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
          final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Event _item;
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            final String _tmpStarts;
            if (_cursor.isNull(_cursorIndexOfStarts)) {
              _tmpStarts = null;
            } else {
              _tmpStarts = _cursor.getString(_cursorIndexOfStarts);
            }
            final String _tmpEnds;
            if (_cursor.isNull(_cursorIndexOfEnds)) {
              _tmpEnds = null;
            } else {
              _tmpEnds = _cursor.getString(_cursorIndexOfEnds);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpSlots;
            _tmpSlots = _cursor.getLong(_cursorIndexOfSlots);
            final long _tmpParticipants;
            _tmpParticipants = _cursor.getLong(_cursorIndexOfParticipants);
            final LocationDetails _tmpLocation;
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpStreet;
            if (_cursor.isNull(_cursorIndexOfStreet)) {
              _tmpStreet = null;
            } else {
              _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
            }
            _tmpLocation = new LocationDetails(_tmpLatitude,_tmpLongitude,_tmpState,_tmpCity,_tmpStreet);
            _item = new Event(_tmpEventId,_tmpName,_tmpImage,_tmpLocation,_tmpStarts,_tmpEnds,_tmpDescription,_tmpSlots,_tmpParticipants);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Event>> getEventsByCity(final String filter) {
    final String _sql = "SELECT * FROM event WHERE city=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (filter == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, filter);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"event"}, new Callable<List<Event>>() {
      @Override
      public List<Event> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
          final int _cursorIndexOfStarts = CursorUtil.getColumnIndexOrThrow(_cursor, "starting_time");
          final int _cursorIndexOfEnds = CursorUtil.getColumnIndexOrThrow(_cursor, "ending_time");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSlots = CursorUtil.getColumnIndexOrThrow(_cursor, "slots");
          final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfStreet = CursorUtil.getColumnIndexOrThrow(_cursor, "street");
          final List<Event> _result = new ArrayList<Event>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Event _item;
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpImage;
            if (_cursor.isNull(_cursorIndexOfImage)) {
              _tmpImage = null;
            } else {
              _tmpImage = _cursor.getString(_cursorIndexOfImage);
            }
            final String _tmpStarts;
            if (_cursor.isNull(_cursorIndexOfStarts)) {
              _tmpStarts = null;
            } else {
              _tmpStarts = _cursor.getString(_cursorIndexOfStarts);
            }
            final String _tmpEnds;
            if (_cursor.isNull(_cursorIndexOfEnds)) {
              _tmpEnds = null;
            } else {
              _tmpEnds = _cursor.getString(_cursorIndexOfEnds);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpSlots;
            _tmpSlots = _cursor.getLong(_cursorIndexOfSlots);
            final long _tmpParticipants;
            _tmpParticipants = _cursor.getLong(_cursorIndexOfParticipants);
            final LocationDetails _tmpLocation;
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpState;
            if (_cursor.isNull(_cursorIndexOfState)) {
              _tmpState = null;
            } else {
              _tmpState = _cursor.getString(_cursorIndexOfState);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpStreet;
            if (_cursor.isNull(_cursorIndexOfStreet)) {
              _tmpStreet = null;
            } else {
              _tmpStreet = _cursor.getString(_cursorIndexOfStreet);
            }
            _tmpLocation = new LocationDetails(_tmpLatitude,_tmpLongitude,_tmpState,_tmpCity,_tmpStreet);
            _item = new Event(_tmpEventId,_tmpName,_tmpImage,_tmpLocation,_tmpStarts,_tmpEnds,_tmpDescription,_tmpSlots,_tmpParticipants);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
