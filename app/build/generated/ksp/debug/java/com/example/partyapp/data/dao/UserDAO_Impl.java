package com.example.partyapp.data.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.partyapp.data.entity.User;
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
public final class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfChangeUsernameFromId;

  private final SharedSQLiteStatement __preparedStmtOfChangePfpFromId;

  private final SharedSQLiteStatement __preparedStmtOfUpdateExpFromId;

  public UserDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `user` (`id`,`name`,`surname`,`username`,`password`,`email`,`pfp`,`exp`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getSurname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSurname());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPassword());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getPfp() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPfp());
        }
        stmt.bindLong(8, value.getExp());
      }
    };
    this.__preparedStmtOfChangeUsernameFromId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user SET username=? WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfChangePfpFromId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user SET pfp=? WHERE id=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateExpFromId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user SET exp=? WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final User[] user, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object changeUsernameFromId(final int userId, final String newUsername,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfChangeUsernameFromId.acquire();
        int _argIndex = 1;
        if (newUsername == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, newUsername);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, userId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfChangeUsernameFromId.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object changePfpFromId(final int userId, final String newPfp,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfChangePfpFromId.acquire();
        int _argIndex = 1;
        if (newPfp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, newPfp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, userId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfChangePfpFromId.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object updateExpFromId(final int userId, final String newExp,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateExpFromId.acquire();
        int _argIndex = 1;
        if (newExp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, newExp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, userId);
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateExpFromId.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Flow<List<User>> getUsers() {
    final String _sql = "SELECT * FROM user";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, true, new String[]{"user"}, new Callable<List<User>>() {
      @Override
      public List<User> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
            final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
            final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
            final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
            final int _cursorIndexOfPfp = CursorUtil.getColumnIndexOrThrow(_cursor, "pfp");
            final int _cursorIndexOfExp = CursorUtil.getColumnIndexOrThrow(_cursor, "exp");
            final List<User> _result = new ArrayList<User>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final User _item;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpName;
              if (_cursor.isNull(_cursorIndexOfName)) {
                _tmpName = null;
              } else {
                _tmpName = _cursor.getString(_cursorIndexOfName);
              }
              final String _tmpSurname;
              if (_cursor.isNull(_cursorIndexOfSurname)) {
                _tmpSurname = null;
              } else {
                _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
              }
              final String _tmpUsername;
              if (_cursor.isNull(_cursorIndexOfUsername)) {
                _tmpUsername = null;
              } else {
                _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
              }
              final String _tmpPassword;
              if (_cursor.isNull(_cursorIndexOfPassword)) {
                _tmpPassword = null;
              } else {
                _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
              }
              final String _tmpEmail;
              if (_cursor.isNull(_cursorIndexOfEmail)) {
                _tmpEmail = null;
              } else {
                _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
              }
              final String _tmpPfp;
              if (_cursor.isNull(_cursorIndexOfPfp)) {
                _tmpPfp = null;
              } else {
                _tmpPfp = _cursor.getString(_cursorIndexOfPfp);
              }
              final long _tmpExp;
              _tmpExp = _cursor.getLong(_cursorIndexOfExp);
              _item = new User(_tmpId,_tmpName,_tmpSurname,_tmpUsername,_tmpPassword,_tmpEmail,_tmpPfp,_tmpExp);
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
  public Flow<User> checkLoginCredentials(final String username, final String password) {
    final String _sql = "SELECT * FROM user WHERE username=? AND password=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[]{"user"}, new Callable<User>() {
      @Override
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPfp = CursorUtil.getColumnIndexOrThrow(_cursor, "pfp");
          final int _cursorIndexOfExp = CursorUtil.getColumnIndexOrThrow(_cursor, "exp");
          final User _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSurname;
            if (_cursor.isNull(_cursorIndexOfSurname)) {
              _tmpSurname = null;
            } else {
              _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            }
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPfp;
            if (_cursor.isNull(_cursorIndexOfPfp)) {
              _tmpPfp = null;
            } else {
              _tmpPfp = _cursor.getString(_cursorIndexOfPfp);
            }
            final long _tmpExp;
            _tmpExp = _cursor.getLong(_cursorIndexOfExp);
            _result = new User(_tmpId,_tmpName,_tmpSurname,_tmpUsername,_tmpPassword,_tmpEmail,_tmpPfp,_tmpExp);
          } else {
            _result = null;
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
  public User getUserFromUsername(final String username) {
    final String _sql = "SELECT * FROM user WHERE username=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPfp = CursorUtil.getColumnIndexOrThrow(_cursor, "pfp");
      final int _cursorIndexOfExp = CursorUtil.getColumnIndexOrThrow(_cursor, "exp");
      final User _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpSurname;
        if (_cursor.isNull(_cursorIndexOfSurname)) {
          _tmpSurname = null;
        } else {
          _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
        }
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        final String _tmpPfp;
        if (_cursor.isNull(_cursorIndexOfPfp)) {
          _tmpPfp = null;
        } else {
          _tmpPfp = _cursor.getString(_cursorIndexOfPfp);
        }
        final long _tmpExp;
        _tmpExp = _cursor.getLong(_cursorIndexOfExp);
        _result = new User(_tmpId,_tmpName,_tmpSurname,_tmpUsername,_tmpPassword,_tmpEmail,_tmpPfp,_tmpExp);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
