
const database = 'jewelry_shop';
const collection = 'items';

// Create a new database.
use(database);

// Create a new collection.
db.createCollection(collection);

use (database);


db.collection.insertOne({
    Item_id: 1,
    Item_name: "Diamond Necklace",
    Item_type: "Necklace",
    Item_description: "Elegant 18K diamond necklace.",
    Item_price: 1500.00,
    Gem_Count: 12,
    Gem_Type: "Diamond"
})