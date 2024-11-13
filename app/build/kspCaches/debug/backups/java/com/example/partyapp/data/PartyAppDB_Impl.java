package com.example.partyapp.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.partyapp.data.dao.EventDAO;
import com.example.partyapp.data.dao.EventDAO_Impl;
import com.example.partyapp.data.dao.UserAddEventDAO;
import com.example.partyapp.data.dao.UserAddEventDAO_Impl;
import com.example.partyapp.data.dao.UserCreateEventDAO;
import com.example.partyapp.data.dao.UserCreateEventDAO_Impl;
import com.example.partyapp.data.dao.UserDAO;
import com.example.partyapp.data.dao.UserDAO_Impl;
import com.example.partyapp.data.dao.UserScansEventDAO;
import com.example.partyapp.data.dao.UserScansEventDAO_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PartyAppDB_Impl extends PartyAppDB {
  private volatile EventDAO _eventDAO;

  private volatile UserDAO _userDAO;

  private volatile UserAddEventDAO _userAddEventDAO;

  private volatile UserCreateEventDAO _userCreateEventDAO;

  private volatile UserScansEventDAO _userScansEventDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `user` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `surname` TEXT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `email` TEXT NOT NULL, `pfp` TEXT NOT NULL, `exp` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_user_username_email` ON `user` (`username`, `email`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `event` (`eventId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `image` TEXT NOT NULL, `starting_time` TEXT NOT NULL, `ending_time` TEXT NOT NULL, `description` TEXT NOT NULL, `slots` INTEGER NOT NULL, `participants` INTEGER NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `state` TEXT NOT NULL, `city` TEXT NOT NULL, `street` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `UserAddEventCrossRef` (`id` INTEGER NOT NULL, `eventId` INTEGER NOT NULL, PRIMARY KEY(`id`, `eventId`), FOREIGN KEY(`id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`eventId`) REFERENCES `event`(`eventId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `UserCreateEventCrossRef` (`id` INTEGER NOT NULL, `eventId` INTEGER NOT NULL, PRIMARY KEY(`id`, `eventId`), FOREIGN KEY(`id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`eventId`) REFERENCES `event`(`eventId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `UserScansEventCrossRef` (`id` INTEGER NOT NULL, `eventId` INTEGER NOT NULL, PRIMARY KEY(`id`, `eventId`), FOREIGN KEY(`id`) REFERENCES `user`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`eventId`) REFERENCES `event`(`eventId`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '53f82dd8a24b8a01620906cc3d1a119b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `user`");
        _db.execSQL("DROP TABLE IF EXISTS `event`");
        _db.execSQL("DROP TABLE IF EXISTS `UserAddEventCrossRef`");
        _db.execSQL("DROP TABLE IF EXISTS `UserCreateEventCrossRef`");
        _db.execSQL("DROP TABLE IF EXISTS `UserScansEventCrossRef`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(8);
        _columnsUser.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("surname", new TableInfo.Column("surname", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("pfp", new TableInfo.Column("pfp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUser.put("exp", new TableInfo.Column("exp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(1);
        _indicesUser.add(new TableInfo.Index("index_user_username_email", true, Arrays.asList("username","email"), Arrays.asList("ASC","ASC")));
        final TableInfo _infoUser = new TableInfo("user", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "user");
        if (! _infoUser.equals(_existingUser)) {
          return new RoomOpenHelper.ValidationResult(false, "user(com.example.partyapp.data.entity.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsEvent = new HashMap<String, TableInfo.Column>(13);
        _columnsEvent.put("eventId", new TableInfo.Column("eventId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("image", new TableInfo.Column("image", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("starting_time", new TableInfo.Column("starting_time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("ending_time", new TableInfo.Column("ending_time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("slots", new TableInfo.Column("slots", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("participants", new TableInfo.Column("participants", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvent.put("street", new TableInfo.Column("street", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvent = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvent = new TableInfo("event", _columnsEvent, _foreignKeysEvent, _indicesEvent);
        final TableInfo _existingEvent = TableInfo.read(_db, "event");
        if (! _infoEvent.equals(_existingEvent)) {
          return new RoomOpenHelper.ValidationResult(false, "event(com.example.partyapp.data.entity.Event).\n"
                  + " Expected:\n" + _infoEvent + "\n"
                  + " Found:\n" + _existingEvent);
        }
        final HashMap<String, TableInfo.Column> _columnsUserAddEventCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsUserAddEventCrossRef.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAddEventCrossRef.put("eventId", new TableInfo.Column("eventId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserAddEventCrossRef = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysUserAddEventCrossRef.add(new TableInfo.ForeignKey("user", "NO ACTION", "NO ACTION",Arrays.asList("id"), Arrays.asList("id")));
        _foreignKeysUserAddEventCrossRef.add(new TableInfo.ForeignKey("event", "NO ACTION", "NO ACTION",Arrays.asList("eventId"), Arrays.asList("eventId")));
        final HashSet<TableInfo.Index> _indicesUserAddEventCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserAddEventCrossRef = new TableInfo("UserAddEventCrossRef", _columnsUserAddEventCrossRef, _foreignKeysUserAddEventCrossRef, _indicesUserAddEventCrossRef);
        final TableInfo _existingUserAddEventCrossRef = TableInfo.read(_db, "UserAddEventCrossRef");
        if (! _infoUserAddEventCrossRef.equals(_existingUserAddEventCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "UserAddEventCrossRef(com.example.partyapp.data.relation.UserAddEventCrossRef).\n"
                  + " Expected:\n" + _infoUserAddEventCrossRef + "\n"
                  + " Found:\n" + _existingUserAddEventCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsUserCreateEventCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsUserCreateEventCrossRef.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserCreateEventCrossRef.put("eventId", new TableInfo.Column("eventId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserCreateEventCrossRef = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysUserCreateEventCrossRef.add(new TableInfo.ForeignKey("user", "NO ACTION", "NO ACTION",Arrays.asList("id"), Arrays.asList("id")));
        _foreignKeysUserCreateEventCrossRef.add(new TableInfo.ForeignKey("event", "NO ACTION", "NO ACTION",Arrays.asList("eventId"), Arrays.asList("eventId")));
        final HashSet<TableInfo.Index> _indicesUserCreateEventCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserCreateEventCrossRef = new TableInfo("UserCreateEventCrossRef", _columnsUserCreateEventCrossRef, _foreignKeysUserCreateEventCrossRef, _indicesUserCreateEventCrossRef);
        final TableInfo _existingUserCreateEventCrossRef = TableInfo.read(_db, "UserCreateEventCrossRef");
        if (! _infoUserCreateEventCrossRef.equals(_existingUserCreateEventCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "UserCreateEventCrossRef(com.example.partyapp.data.relation.UserCreateEventCrossRef).\n"
                  + " Expected:\n" + _infoUserCreateEventCrossRef + "\n"
                  + " Found:\n" + _existingUserCreateEventCrossRef);
        }
        final HashMap<String, TableInfo.Column> _columnsUserScansEventCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsUserScansEventCrossRef.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserScansEventCrossRef.put("eventId", new TableInfo.Column("eventId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserScansEventCrossRef = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysUserScansEventCrossRef.add(new TableInfo.ForeignKey("user", "NO ACTION", "NO ACTION",Arrays.asList("id"), Arrays.asList("id")));
        _foreignKeysUserScansEventCrossRef.add(new TableInfo.ForeignKey("event", "NO ACTION", "NO ACTION",Arrays.asList("eventId"), Arrays.asList("eventId")));
        final HashSet<TableInfo.Index> _indicesUserScansEventCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserScansEventCrossRef = new TableInfo("UserScansEventCrossRef", _columnsUserScansEventCrossRef, _foreignKeysUserScansEventCrossRef, _indicesUserScansEventCrossRef);
        final TableInfo _existingUserScansEventCrossRef = TableInfo.read(_db, "UserScansEventCrossRef");
        if (! _infoUserScansEventCrossRef.equals(_existingUserScansEventCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "UserScansEventCrossRef(com.example.partyapp.data.relation.UserScansEventCrossRef).\n"
                  + " Expected:\n" + _infoUserScansEventCrossRef + "\n"
                  + " Found:\n" + _existingUserScansEventCrossRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "53f82dd8a24b8a01620906cc3d1a119b", "4c0bcc02676489bc599ad9f7b032561a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "user","event","UserAddEventCrossRef","UserCreateEventCrossRef","UserScansEventCrossRef");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `UserAddEventCrossRef`");
      _db.execSQL("DELETE FROM `UserCreateEventCrossRef`");
      _db.execSQL("DELETE FROM `UserScansEventCrossRef`");
      _db.execSQL("DELETE FROM `user`");
      _db.execSQL("DELETE FROM `event`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(EventDAO.class, EventDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDAO.class, UserDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserAddEventDAO.class, UserAddEventDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserCreateEventDAO.class, UserCreateEventDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserScansEventDAO.class, UserScansEventDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public EventDAO eventDAO() {
    if (_eventDAO != null) {
      return _eventDAO;
    } else {
      synchronized(this) {
        if(_eventDAO == null) {
          _eventDAO = new EventDAO_Impl(this);
        }
        return _eventDAO;
      }
    }
  }

  @Override
  public UserDAO userDAO() {
    if (_userDAO != null) {
      return _userDAO;
    } else {
      synchronized(this) {
        if(_userDAO == null) {
          _userDAO = new UserDAO_Impl(this);
        }
        return _userDAO;
      }
    }
  }

  @Override
  public UserAddEventDAO userAddEventDAO() {
    if (_userAddEventDAO != null) {
      return _userAddEventDAO;
    } else {
      synchronized(this) {
        if(_userAddEventDAO == null) {
          _userAddEventDAO = new UserAddEventDAO_Impl(this);
        }
        return _userAddEventDAO;
      }
    }
  }

  @Override
  public UserCreateEventDAO userCreateEventDAO() {
    if (_userCreateEventDAO != null) {
      return _userCreateEventDAO;
    } else {
      synchronized(this) {
        if(_userCreateEventDAO == null) {
          _userCreateEventDAO = new UserCreateEventDAO_Impl(this);
        }
        return _userCreateEventDAO;
      }
    }
  }

  @Override
  public UserScansEventDAO userScansEventDAO() {
    if (_userScansEventDAO != null) {
      return _userScansEventDAO;
    } else {
      synchronized(this) {
        if(_userScansEventDAO == null) {
          _userScansEventDAO = new UserScansEventDAO_Impl(this);
        }
        return _userScansEventDAO;
      }
    }
  }
}
