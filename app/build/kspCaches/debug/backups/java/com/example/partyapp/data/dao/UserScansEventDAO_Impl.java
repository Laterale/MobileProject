package com.example.partyapp.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.partyapp.data.relation.UserScansEventCrossRef;
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
public final class UserScansEventDAO_Impl implements UserScansEventDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserScansEventCrossRef> __insertionAdapterOfUserScansEventCrossRef;

  public UserScansEventDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserScansEventCrossRef = new EntityInsertionAdapter<UserScansEventCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `UserScansEventCrossRef` (`id`,`eventId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserScansEventCrossRef value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getEventId());
      }
    };
  }

  @Override
  public Object addScan(final UserScansEventCrossRef[] userScansEventCrossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserScansEventCrossRef.insert(userScansEventCrossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<UserScansEventCrossRef>> getAllScans() {
    final String _sql = "SELECT * FROM UserScansEventCrossRef";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"UserScansEventCrossRef"}, new Callable<List<UserScansEventCrossRef>>() {
      @Override
      public List<UserScansEventCrossRef> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
            final List<UserScansEventCrossRef> _result = new ArrayList<UserScansEventCrossRef>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final UserScansEventCrossRef _item;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final int _tmpEventId;
              _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
              _item = new UserScansEventCrossRef(_tmpId,_tmpEventId);
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
  public Flow<List<UserScansEventCrossRef>> getScansFromUserId(final int userId) {
    final String _sql = "SELECT * FROM UserScansEventCrossRef WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"UserScansEventCrossRef"}, new Callable<List<UserScansEventCrossRef>>() {
      @Override
      public List<UserScansEventCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final List<UserScansEventCrossRef> _result = new ArrayList<UserScansEventCrossRef>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserScansEventCrossRef _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            _item = new UserScansEventCrossRef(_tmpId,_tmpEventId);
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
  public Flow<List<UserScansEventCrossRef>> getScansFromEventId(final int eventId) {
    final String _sql = "SELECT * FROM UserScansEventCrossRef WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"UserScansEventCrossRef"}, new Callable<List<UserScansEventCrossRef>>() {
      @Override
      public List<UserScansEventCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final List<UserScansEventCrossRef> _result = new ArrayList<UserScansEventCrossRef>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserScansEventCrossRef _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            _item = new UserScansEventCrossRef(_tmpId,_tmpEventId);
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
