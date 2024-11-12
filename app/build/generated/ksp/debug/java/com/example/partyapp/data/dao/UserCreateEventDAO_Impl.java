package com.example.partyapp.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.partyapp.data.relation.UserCreateEventCrossRef;
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
public final class UserCreateEventDAO_Impl implements UserCreateEventDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserCreateEventCrossRef> __insertionAdapterOfUserCreateEventCrossRef;

  public UserCreateEventDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserCreateEventCrossRef = new EntityInsertionAdapter<UserCreateEventCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `UserCreateEventCrossRef` (`id`,`eventId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserCreateEventCrossRef value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getEventId());
      }
    };
  }

  @Override
  public Object insertEvent(final UserCreateEventCrossRef[] userCreateEventCrossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserCreateEventCrossRef.insert(userCreateEventCrossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<UserCreateEventCrossRef>> getCreatorByEventId(final int eventId) {
    final String _sql = "SELECT * FROM UserCreateEventCrossRef WHERE eventId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"UserCreateEventCrossRef"}, new Callable<List<UserCreateEventCrossRef>>() {
      @Override
      public List<UserCreateEventCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final List<UserCreateEventCrossRef> _result = new ArrayList<UserCreateEventCrossRef>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserCreateEventCrossRef _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            _item = new UserCreateEventCrossRef(_tmpId,_tmpEventId);
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
  public Flow<List<UserCreateEventCrossRef>> getEventsByCreatorId(final int userId) {
    final String _sql = "SELECT * FROM UserCreateEventCrossRef WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"UserCreateEventCrossRef"}, new Callable<List<UserCreateEventCrossRef>>() {
      @Override
      public List<UserCreateEventCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final List<UserCreateEventCrossRef> _result = new ArrayList<UserCreateEventCrossRef>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserCreateEventCrossRef _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            _item = new UserCreateEventCrossRef(_tmpId,_tmpEventId);
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
