package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table EXERCISE_SET.
*/
public class ExerciseSetDao extends AbstractDao<ExerciseSet, Long> {

    public static final String TABLENAME = "EXERCISE_SET";

    /**
     * Properties of entity ExerciseSet.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Duration = new Property(1, Integer.class, "duration", false, "DURATION");
        public final static Property Date = new Property(2, java.util.Date.class, "date", false, "DATE");
        public final static Property Exercise = new Property(3, String.class, "exercise", false, "EXERCISE");
        public final static Property Muscle = new Property(4, String.class, "muscle", false, "MUSCLE");
        public final static Property Weight = new Property(5, Double.class, "weight", false, "WEIGHT");
        public final static Property Reps = new Property(6, Integer.class, "reps", false, "REPS");
    };


    public ExerciseSetDao(DaoConfig config) {
        super(config);
    }
    
    public ExerciseSetDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'EXERCISE_SET' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'DURATION' INTEGER," + // 1: duration
                "'DATE' INTEGER," + // 2: date
                "'EXERCISE' TEXT," + // 3: exercise
                "'MUSCLE' TEXT," + // 4: muscle
                "'WEIGHT' REAL," + // 5: weight
                "'REPS' INTEGER);"); // 6: reps
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'EXERCISE_SET'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ExerciseSet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer duration = entity.getDuration();
        if (duration != null) {
            stmt.bindLong(2, duration);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(3, date.getTime());
        }
 
        String exercise = entity.getExercise();
        if (exercise != null) {
            stmt.bindString(4, exercise);
        }
 
        String muscle = entity.getMuscle();
        if (muscle != null) {
            stmt.bindString(5, muscle);
        }
 
        Double weight = entity.getWeight();
        if (weight != null) {
            stmt.bindDouble(6, weight);
        }
 
        Integer reps = entity.getReps();
        if (reps != null) {
            stmt.bindLong(7, reps);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ExerciseSet readEntity(Cursor cursor, int offset) {
        ExerciseSet entity = new ExerciseSet( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // duration
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // date
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // exercise
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // muscle
            cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5), // weight
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // reps
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ExerciseSet entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDuration(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setExercise(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMuscle(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setWeight(cursor.isNull(offset + 5) ? null : cursor.getDouble(offset + 5));
        entity.setReps(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ExerciseSet entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ExerciseSet entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
