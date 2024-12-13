import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventory.data.Item
import android.content.Context
import com.example.inventory.data.ItemDao
import androidx.room.Room

/*
 *   Puedes usar este código como plantilla para tus proyectos futuros. La forma en que creas la
 *   instancia RoomDatabase es similar al proceso en los pasos anteriores. Es posible que debas
 *   reemplazar las entidades y los DAO específicos de tu app.
 */

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}