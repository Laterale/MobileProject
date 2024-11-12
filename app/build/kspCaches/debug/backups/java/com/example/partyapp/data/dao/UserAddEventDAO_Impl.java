package com.example.partyapp.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.partyapp.data.relation.UserAddEventCrossRef;
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
public final class UserAddEventDAO_Impl implements UserAddEventDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserAddEventCrossRef> __insertionAdapterOfUserAddEventCrossRef;

  private final EntityDeletionOrUpdateAdapter<UserAddEventCrossRef> __deletionAdapterOfUserAddEventCrossRef;

  public UserAddEventDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserAddEventCrossRef = new EntityInsertionAdapter<UserAddEventCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `UserAddEventCrossRef` (`id`,`eventId`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserAddEventCrossRef value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getEventId());
      }
    };
    this.__deletionAdapterOfUserAddEventCrossRef = new EntityDeletionOrUpdateAdapter<UserAddEventCrossRef>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `UserAddEventCrossRef` WHERE `id` = ? AND `eventId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, UserAddEventCrossRef value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getEventId());
      }
    };
  }

  @Override
  public Object addParticipant(final UserAddEventCrossRef[] userAddEventCrossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserAddEventCrossRef.insert(userAddEventCrossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object deleteParticipant(final UserAddEventCrossRef[] userAddEventCrossRef,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserAddEventCrossRef.handleMultiple(userAddEventCrossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<UserAddEventCrossRef>> getAllParticipants() {
    final String _sql = "SELECT * FROM UserAddEventCrossRef";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"UserAddEventCrossRef"}, new Callable<List<UserAddEventCrossRef>>() {
      @Override
      public List<UserAddEventCrossRef> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
            final List<UserAddEventCrossRef> _result = new ArrayList<UserAddEventCrossRef>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final UserAddEventCrossRef _item;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final int _tmpEventId;
              _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
              _item = new UserAddEventCrossRef(_tmpId,_tmpEventId);
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
  public Flow<List<UserAddEventCrossRef>> getParticipantsFromEventId(final int eventId) {
    final String _sql = "SELECT * FROM UserAddEventCrossRef WHERE eventId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventId);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"UserAddEventCrossRef"}, new Callable<List<UserAddEventCrossRef>>() {
      @Override
      public List<UserAddEventCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final List<UserAddEventCrossRef> _result = new ArrayList<UserAddEventCrossRef>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final UserAddEventCrossRef _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpEventId;
            _tmpEventId = _cursor.getInt(_cursorIndexOfEventId);
            _item = new UserAddEventCrossRef(_tmpId,_tmpEventId);
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
