// MongoDB Playground
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.

// The current database to use.
use('jewelry_shop');

// Create a new document in the collection.
db.getCollection('items').insertOne(
    {
        "Item_id": 1,
        "Item_name": "Diamond Ring",
        "Item_type": "Ring",
        "Item_description": "A beautiful diamond ring",
        "Item_price": 1500.0,
        "Gem_Count": 1,
        "Gem_Type": "Diamond"
      }
      
);
